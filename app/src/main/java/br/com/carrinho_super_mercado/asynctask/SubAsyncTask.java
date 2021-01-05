package br.com.carrinho_super_mercado.asynctask;

import android.os.AsyncTask;

import java.nio.file.AccessMode;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Amount;

public class SubAsyncTask extends AsyncTask<Void, Void, Void> {


    private final CartDao dao;
    private final int quantity;


    public SubAsyncTask(CartDao dao, int quantity) {
        this.dao = dao;
        this.quantity = quantity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.subtractionQuantity(quantity);
        return null;
    }
}
