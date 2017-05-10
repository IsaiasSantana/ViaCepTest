package br.com.viaceptest.mvp.view;

import com.arellomobile.mvp.MvpView;

import br.com.viaceptest.domain.Endereco;

/**
 * @author Isaías Santana
 * @since 04/04/17.
 */

public interface ZipCodeSearchView extends MvpView
{
    void lockFields(boolean isToLock);
    void updateListView();
    void addAddress(Endereco address);
    void clearAddresses();
}
