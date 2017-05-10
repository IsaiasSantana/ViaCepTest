package br.com.viaceptest.mvp.presenter.ZipCodeSearchActivityPresenter;

import br.com.viaceptest.domain.Endereco;

/**
 * @author isaias
 * @since 04/04/17.
 */

public interface ZipCodeSearchInterface
{
    interface ZipCodeInterfaceToModel
    {
        void lockFields(boolean isToLock);
        void updateListView();
        void clearAddresses();
        void addAddress(Endereco address);
    }

    interface ZipCodeInterfaceToView
    {
        void searchAddress(String state, String city, String street);
        String getSelectedZipCode(Endereco address);
    }
}
