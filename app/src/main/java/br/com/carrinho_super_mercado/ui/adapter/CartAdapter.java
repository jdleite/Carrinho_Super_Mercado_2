package br.com.carrinho_super_mercado.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.carrinho_super_mercado.R;
import br.com.carrinho_super_mercado.model.Product;
import br.com.carrinho_super_mercado.ui.listener.CartListener;
import br.com.carrinho_super_mercado.ui.viewHolder.CartViewHolder;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private final List<Product> productList;
    private final CartListener listener;

    public CartAdapter(CartListener listener, List<Product> productList) {
        this.listener =  listener;
        this.productList = productList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_product_row, parent, false);

        return new CartViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.bindData(product,listener);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void load(List<Product> productList){
        this.productList.clear();
        this.productList.addAll(productList);
        notifyDataSetChanged();
    }

    public void removeList(Product product){
        productList.remove(product);
        notifyDataSetChanged();

    }
}
