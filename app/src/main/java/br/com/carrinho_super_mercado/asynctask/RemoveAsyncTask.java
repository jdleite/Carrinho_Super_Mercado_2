package br.com.carrinho_super_mercado.asynctask;

import android.os.AsyncTask;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Product;
import br.com.carrinho_super_mercado.ui.adapter.CartAdapter;

public class RemoveAsyncTask extends AsyncTask<Void,Void,Void> {

    private CartDao dao;
    private Product product;
    private CartAdapter adapter;

    public RemoveAsyncTask(CartDao dao,Product product,CartAdapter adapter){
        this.dao = dao;
        this.product = product;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.delete(product);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        adapter.removeList(product);
    }
}
