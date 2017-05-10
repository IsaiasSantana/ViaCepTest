package br.com.viaceptest.mvp.presenter.ZipCodeSearchActivityPresenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.text.Normalizer;

import br.com.viaceptest.domain.Endereco;
import br.com.viaceptest.mvp.model.ZipCodeSearchModel.ZipCodeSearchModel;
import br.com.viaceptest.mvp.view.ZipCodeSearchView;

/**
 * @author Isaías Santana.
 * @since 04/04/17.
 */

@InjectViewState
public class ZipCodeSearchPresenter extends MvpPresenter<ZipCodeSearchView> implements ZipCodeSearchInterface.ZipCodeInterfaceToModel,
                                                                                       ZipCodeSearchInterface.ZipCodeInterfaceToView
{

    private ZipCodeSearchModel model;

    public ZipCodeSearchPresenter()
    {
        model = new ZipCodeSearchModel(this);
    }


    /*Métodos para o Model */

    @Override
    public void lockFields(boolean isToLock)
    {
        getViewState().lockFields(isToLock);
    }

    @Override
    public void updateListView() {
        getViewState().updateListView();
    }

    @Override
    public void clearAddresses()
    {
        getViewState().clearAddresses();
    }


    @Override
    public void addAddress(Endereco address)
    {
        getViewState().addAddress(address);
    }

    /*Métodos para a View */

    @Override
    public void searchAddress(String state, String city, String street)
    {
        model.zipCodeRequest(getState(state),removerAcentos(city),removerAcentos(street));
    }

    @Override
    public String getSelectedZipCode(Endereco address)
    {
        String[] zipCodeArray = address.getCep().split("-");
        return zipCodeArray[0]+zipCodeArray[1];
    }

    private  String getState(String state)
    {
        return state.substring(state.length()-3,state.length()-1);
    }

    private String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
