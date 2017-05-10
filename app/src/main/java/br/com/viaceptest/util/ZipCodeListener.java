package br.com.viaceptest.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import br.com.viaceptest.mvp.presenter.MainActivityPresenter;

/**
 * @author isaias Santana
 * @since 03/04/17.
 * Quando o usuários preencher o campo de cep (8 digitos), o listener iniciará uma atividade
 * que buscará as informações relacionadas a esse cep (como rua, bairro, cidade...) e preencherá os
 * campos da view MainActivity.
 */

public class ZipCodeListener implements TextWatcher
{
    private MainActivityPresenter presenter;
    private Context context;

    public  ZipCodeListener(MainActivityPresenter presenter, Context context)
    {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable)
    {
        String zipCode = editable.toString();

        if(editable.length() == 8)
        {
           if(InternetConnection.hasConnection(this.context)) presenter.searchAddress(zipCode);
        }
    }
}
