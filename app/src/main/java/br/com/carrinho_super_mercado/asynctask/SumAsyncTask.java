package br.com.carrinho_super_mercado.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Amount;

public class SumAsyncTask extends AsyncTask<Void, Void,Void> {

    private final CartDao dao;
    private final int quantity;


    public SumAsyncTask(CartDao dao, int quantity) {
        this.dao = dao;
        this.quantity = quantity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.sumQuantity(quantity);
        return null;
    }
}


