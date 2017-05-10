package br.com.viaceptest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import br.com.viaceptest.R;
import br.com.viaceptest.adapters.AddressAdapter;
import br.com.viaceptest.domain.Endereco;
import br.com.viaceptest.mvp.presenter.ZipCodeSearchActivityPresenter.ZipCodeSearchPresenter;
import br.com.viaceptest.mvp.view.ZipCodeSearchView;
import br.com.viaceptest.util.DividerItemDecoration;
import br.com.viaceptest.util.InternetConnection;
import br.com.viaceptest.util.ItemClickSupport;

public class ZipCodeSearchActivity extends BaseActivity implements ZipCodeSearchView
{

    private Spinner spStates;
    private RecyclerView recyclerViewAddresses;
    private List<Endereco> addresses;

    @InjectPresenter
    ZipCodeSearchPresenter presenter;

    public ZipCodeSearchActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code_search);
        addresses = new ArrayList<>(); // vem primeiro.
        super.setIds(R.id.et_street, R.id.et_city, R.id.sp_state);
        initViews();
    }


    @Override
    public void lockFields(boolean isToLock)
    {
        super.lockFields(isToLock);
    }

    @Override
    public void updateListView()
    {
        (recyclerViewAddresses.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void addAddress(Endereco address)
    {
        addresses.add(address);
        Log.d("Buc",address.getBairro());
    }

    @Override
    public void clearAddresses()
    {
        addresses.clear();
    }

    public void searchAddress( View view )
    {
        String state = ((String) spStates.getSelectedItem());
        String city = ((EditText) findViewById(R.id.et_city)).getText().toString();
        String street =  ((EditText) findViewById(R.id.et_street)).getText().toString();

        if(InternetConnection.hasConnection(this)) presenter.searchAddress(state,city,street);
        else {
            Toast.makeText(getApplicationContext(),"Não há conexão com a internet.",Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews()
    {
        initToolbar();
        initRecyclerView();
        spStates = (Spinner) findViewById(R.id.sp_state);
        spStates.setAdapter( ArrayAdapter.createFromResource(this, R.array.states, android.R.layout.simple_spinner_item) );
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerViewAddresses = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerViewAddresses.setLayoutManager(linearLayoutManager);
        recyclerViewAddresses.setAdapter( new AddressAdapter( addresses ));
        recyclerViewAddresses.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerViewAddresses.setHasFixedSize(false);
        ItemClickSupport.addTo(recyclerViewAddresses).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v)
            {
                String zipCode = presenter.getSelectedZipCode(addresses.get(position));

                Intent intent = new Intent();
                intent.putExtra( Endereco.ZIP_CODE_KEY, zipCode );
                setResult( RESULT_OK, intent );
                finish();
            }
        });
    }

}
