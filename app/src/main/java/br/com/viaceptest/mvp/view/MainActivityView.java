package br.com.viaceptest.mvp.view;

import com.arellomobile.mvp.MvpView;

import br.com.viaceptest.domain.Endereco;

/**
 * Interface com os métodos disponíveis para o Presenter
 * @author Isaías Santana
 * @since 09/05/17.
 */

public interface MainActivityView extends MvpView
{

    /** Bloqueia os campos de entrada para não edição */
    void lockFields(boolean isToLook);

    /** Configura os campos do formulário pelo objeto endereço passado */
    void setAddress(Endereco endereco);

}
