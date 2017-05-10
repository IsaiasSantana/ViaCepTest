package br.com.viaceptest.mvp.model.ZipCodeSearchModel;

import android.os.AsyncTask;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.viaceptest.domain.Endereco;
import br.com.viaceptest.mvp.presenter.ZipCodeSearchActivityPresenter.ZipCodeSearchInterface;
import br.com.viaceptest.util.JsonRequest;


/**
 * @author Isaías Santana.
 * @since 04/04/17.
 */

public class ZipCodeSearchModel implements ZipCodeSearchModelInterface
{
    private ZipCodeSearchInterface.ZipCodeInterfaceToModel presenter;

    public ZipCodeSearchModel(ZipCodeSearchInterface.ZipCodeInterfaceToModel presenter)
    {
        this.presenter = presenter;
    }

    @Override
    public void zipCodeRequest(final String state, final String city, final String street)
    {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPreExecute()
            {
                super.onPreExecute();
                presenter.lockFields(true);

            }

            @Override
            protected Void doInBackground(Void... voids)
            {
                try
                {
                    String jsonString = JsonRequest.request(getUriZipCode(state,city,street));

                    Gson gson = new Gson();
                    JSONArray jsonArray = new JSONArray(jsonString);

                    presenter.clearAddresses();

                    for( int i = 0; i < jsonArray.length(); i++ )
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Endereco a = gson.fromJson( jsonObject.toString(), Endereco.class );
                        presenter.addAddress(a);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void svoid)
            {
                super.onPostExecute(svoid);
                presenter.lockFields(false);
                presenter.updateListView();

            }
        }.execute();
    }

    private  String getUriZipCode(String state, String city, String street)
    {
        return "https://viacep.com.br/ws/"+state.trim()+"/"+city.trim()+"/"+street.trim()+"/json/";
    }
}
