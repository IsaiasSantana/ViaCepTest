package br.com.viaceptest.adapters.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.viaceptest.R;
import br.com.viaceptest.domain.Endereco;

/**
 * @author isaias
 * @since 04/04/17.
 */

public class AddressViewHolder extends RecyclerView.ViewHolder
{
    private TextView tvZipCode;
    private TextView tvStreet;
    private TextView tvNeighbor;

    public AddressViewHolder(View itemView)
    {
        super(itemView);
        setViews(itemView);
    }

    private void setViews(View view)
    {
        tvZipCode  = (TextView) view.findViewById(R.id.tv_zip_code);
        tvStreet  = (TextView) view.findViewById(R.id.tv_street);
        tvNeighbor  = (TextView) view.findViewById(R.id.tv_neighbor);
    }

    public void setData( Endereco address )
    {
        tvZipCode.setText( "CEP: "+address.getCep() );
        tvStreet.setText( "Rua: "+address.getLogradouro() );
        tvNeighbor.setText( "Bairro: "+address.getBairro() );
    }
}
