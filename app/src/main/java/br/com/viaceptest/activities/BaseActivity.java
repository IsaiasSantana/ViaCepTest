package br.com.viaceptest.activities;

import com.arellomobile.mvp.MvpAppCompatActivity;

/**
 * @author isaias
 * @since 10/05/17.
 */

public class BaseActivity extends MvpAppCompatActivity
{
    /**Guarda os ids dos campos do layout dessa Activity */
    private int ids[];

    protected void setIds(int ... ids)
    {
        this.ids = ids;
    }

    protected void lockFields(boolean isToLock)
    {
        checkIDs();
        for( int id : ids )
        {
            setLockField( id, isToLock );
        }
    }

    private void setLockField( int fieldId, boolean isToLock )
    {
        findViewById( fieldId ).setEnabled( !isToLock );
    }

    private void checkIDs()
    {
        if(ids == null || ids.length == 0)
            throw new ArrayStoreException("ids não inicializado ou nulo. Inicialize usando setIds " +
                    "passando os ids da view correspondente.");
    }
}
