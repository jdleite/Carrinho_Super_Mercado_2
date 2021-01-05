package br.com.carrinho_super_mercado.asynctask;

import android.os.AsyncTask;

import br.com.carrinho_super_mercado.database.dao.CartDao;

public class TotPriceAsyncTask extends AsyncTask<Void, Void, Void> {

    private final CartDao dao;
    private final String price;


    public TotPriceAsyncTask(CartDao dao, String price) {
        this.dao = dao;
        this.price = price;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.totPrice(price);

        return null;
    }


}
