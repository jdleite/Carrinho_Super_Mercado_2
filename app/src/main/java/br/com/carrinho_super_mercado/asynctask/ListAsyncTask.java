package br.com.carrinho_super_mercado.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Product;
import br.com.carrinho_super_mercado.ui.adapter.CartAdapter;

public class ListAsyncTask extends AsyncTask<Void, Void, List<Product>> {

    private final CartDao dao;
    private final CartAdapter adapter;


    public ListAsyncTask(CartDao dao, CartAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Product> doInBackground(Void... voids) {
        return dao.listAll();
    }

    @Override
    protected void onPostExecute(List<Product> productList) {
        super.onPostExecute(productList);
        adapter.load(productList);
    }
}
