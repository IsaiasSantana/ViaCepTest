package br.com.viaceptest.mvp.model;

import android.os.AsyncTask;

import com.google.gson.Gson;

import br.com.viaceptest.domain.Endereco;
import br.com.viaceptest.mvp.presenter.MainActivityPresenterInterface;
import br.com.viaceptest.util.JsonRequest;

/**
 * @author isaias
 * @since 09/05/17.
 */

public class MainActivityModel
{
    private MainActivityPresenterInterface.PresenterToModel presenter;

    public MainActivityModel(MainActivityPresenterInterface.PresenterToModel presenter)
    {
        this.presenter = presenter;
    }


    public void searchAddress(String zipCode)
    {
        final String url =  "https://viacep.com.br/ws/"+zipCode+"/json/";


        new AsyncTask<Void,Void,Endereco>()
        {
            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                presenter.lockFields(true);
            }

            @Override
            protected Endereco doInBackground(Void... voids)
            {
                try {
                    String jsonString = JsonRequest.request(url);
                    Gson gson = new Gson();

                    return gson.fromJson(jsonString,Endereco.class);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Endereco address)
            {
                super.onPostExecute(address);
                presenter.lockFields(false);
                presenter.setAddress(address);
            }
        }.execute();
    }
}
