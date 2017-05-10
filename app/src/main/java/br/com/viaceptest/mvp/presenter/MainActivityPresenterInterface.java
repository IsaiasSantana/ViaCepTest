package br.com.viaceptest.mvp.presenter;

import br.com.viaceptest.domain.Endereco;

/**
 * @author isaias
 * @since 10/05/17.
 */

public interface MainActivityPresenterInterface
{

    /**
     * Interface com os métodos disponíveis para a view.
     */
    interface PresenterToView
    {
        /** Busca pelo cep informado*/
        void searchAddress(String zipCode);
    }


    /**
     * Interface com os métodos disponíveis que o model pode acessar.
     */
    interface PresenterToModel
    {
        void lockFields(boolean isLock);
        void setAddress(Endereco endereco);
    }

}
