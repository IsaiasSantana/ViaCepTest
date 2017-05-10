package br.com.viaceptest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.arellomobile.mvp.presenter.InjectPresenter;

import br.com.viaceptest.R;
import br.com.viaceptest.domain.Endereco;
import br.com.viaceptest.mvp.presenter.MainActivityPresenter;
import br.com.viaceptest.mvp.view.MainActivityView;
import br.com.viaceptest.util.ZipCodeListener;

public class MainActivity extends BaseActivity implements MainActivityView
{
    @InjectPresenter
    MainActivityPresenter presenter;

    private EditText etZipCode;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Endereco.RESQUEST_ZIP_CODE_CODE
                && resultCode == RESULT_OK ) {

            etZipCode.setText( data.getStringExtra( Endereco.ZIP_CODE_KEY ) );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void init()
    {
        // Os ids das views que serão bloqueados durante a pesquisa do cep.
        super.setIds(
                R.id.et_zip_code,
                R.id.et_street,
                R.id.et_complement,
                R.id.et_neighbor,
                R.id.et_city,
                R.id.sp_state,
                R.id.et_number,
                R.id.tv_zip_code_search);

        etZipCode = (EditText) findViewById(R.id.et_zip_code);
        etZipCode.addTextChangedListener(new ZipCodeListener(presenter,this));

        initSpinners();
    }

    @Override
    public void lockFields(boolean isToLook)
    {
        super.lockFields(isToLook);
    }

    @Override
    public void setAddress(Endereco endereco)
    {
        setField( R.id.et_street, endereco.getLogradouro() );
        setField( R.id.et_complement, endereco.getComplemento() );
        setField( R.id.et_neighbor, endereco.getBairro() );
        setField( R.id.et_city, endereco.getLocalidade() );
        setSpinner( R.id.sp_state, R.array.states, endereco.getUf() );
    }

    public void searchZipCode( View view )
    {
        Intent intent = new Intent( this, ZipCodeSearchActivity.class );
        startActivityForResult(intent, Endereco.RESQUEST_ZIP_CODE_CODE);
    }

    private void setField( int id, String data ){
        ((EditText) findViewById(id)).setText( data );
    }

    private void setSpinner( int id, int arrayId, String data ){
        String[] itens = getResources().getStringArray(arrayId);

        for( int i = 0; i < itens.length; i++ ){

            if( itens[i].endsWith( "("+data+")" ) ){
                ((Spinner) findViewById(id)).setSelection( i );
                return;
            }
        }
        ((Spinner) findViewById(id)).setSelection( 0 );
    }


    private void initSpinners()
    {
        Spinner spinnerState = (Spinner) findViewById(R.id.sp_state);

        Spinner spinnerTypeAnnouncement = (Spinner) findViewById(R.id.sp_type_announcement);

        ArrayAdapter<CharSequence> adapterStates = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.states,
                R.layout.spinner_item
        );

        ArrayAdapter<CharSequence> adapterTypeAnnouncement = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.type_announcement,
                R.layout.spinner_item
        );

        spinnerState.setAdapter(adapterStates);
        spinnerTypeAnnouncement.setAdapter(adapterTypeAnnouncement);
        spinnerTypeAnnouncement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                 adapterView.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
