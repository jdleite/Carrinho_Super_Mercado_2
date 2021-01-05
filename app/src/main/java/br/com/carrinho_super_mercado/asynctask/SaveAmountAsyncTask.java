package br.com.carrinho_super_mercado.asynctask;

import android.os.AsyncTask;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Amount;

public class SaveAmountAsyncTask extends AsyncTask<Void,Void,Void> {

    private final CartDao dao;
    private final Amount amount;


    public SaveAmountAsyncTask(CartDao dao,Amount amount){
        this.dao = dao;
        this.amount = amount;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.saveAmount(amount);
        return null;
    }
}
