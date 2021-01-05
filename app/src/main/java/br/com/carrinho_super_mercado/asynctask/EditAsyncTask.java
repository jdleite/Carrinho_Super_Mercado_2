package br.com.carrinho_super_mercado.asynctask;

import android.os.AsyncTask;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Product;

public class EditAsyncTask extends AsyncTask<Void,Void,Void> {

    private final CartDao dao;
    private final Product product;



    public EditAsyncTask(CartDao dao,Product product){
        this.dao = dao;
        this.product = product;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.update(product);
        return null;
    }
}
