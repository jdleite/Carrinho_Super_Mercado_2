package br.com.carrinho_super_mercado.ui.listener;

import br.com.carrinho_super_mercado.model.Product;

public interface CartListener {

    void onclickList(Product product);

    void onDeleteClick(Product product);
}
