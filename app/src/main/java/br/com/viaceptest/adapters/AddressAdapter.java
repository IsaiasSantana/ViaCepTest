package br.com.viaceptest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import br.com.viaceptest.R;
import br.com.viaceptest.adapters.viewHolders.AddressViewHolder;
import br.com.viaceptest.domain.Endereco;


/**
 * @author Isaías Santana.
 * @since 04/04/17.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressViewHolder>
{
    private List<Endereco> addresses;

    public AddressAdapter(List<Endereco> addresses)
    {
        if(addresses == null) throw new NullPointerException("A lista de endereços não pode ser nula.");
        this.addresses = addresses;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item,parent,false);
        return new AddressViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position)
    {
        holder.setData(addresses.get(position));
    }

    @Override
    public int getItemCount() {
        return addresses!= null ? addresses.size() : 0;
    }
}
