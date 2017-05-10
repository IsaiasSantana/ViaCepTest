package br.com.viaceptest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import br.com.viaceptest.domain.Endereco;
import br.com.viaceptest.mvp.model.MainActivityModel;
import br.com.viaceptest.mvp.view.MainActivityView;

/**
 * @author isaias
 * @since 09/05/17.
 */

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> implements
                                                                           MainActivityPresenterInterface.PresenterToView,
                                                                           MainActivityPresenterInterface.PresenterToModel
{

    private MainActivityModel  model;

    public MainActivityPresenter()
    {
        model = new MainActivityModel(this);
    }

    @Override
    public void searchAddress(String zipCode)
    {
        model.searchAddress(zipCode);

    }


    @Override
    public void lockFields(boolean isLock)
    {
        getViewState().lockFields(isLock);
    }

    @Override
    public void setAddress(Endereco endereco)
    {
        getViewState().setAddress(endereco);
    }
}
