package br.com.carrinho_super_mercado.ui.viewHolder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.carrinho_super_mercado.R;
import br.com.carrinho_super_mercado.model.Product;
import br.com.carrinho_super_mercado.ui.listener.CartListener;

public class CartViewHolder extends RecyclerView.ViewHolder {

    TextView txtName,txtPrice,txtQuantity;
    ImageView imgDelete;
    CardView cardView;
    Context context;

    public CartViewHolder(@NonNull View itemView, Context context) {
        super(itemView);

        txtName = itemView.findViewById(R.id.id_txt_name);
        txtQuantity = itemView.findViewById(R.id.id_txt_product_quantity);
        txtPrice = itemView.findViewById(R.id.id_txt_price);
        imgDelete = itemView.findViewById(R.id.id_img_delete);
        cardView = itemView.findViewById(R.id.id_cardview);
        this.context = context;

    }

    public void bindData(final Product product, final CartListener listener){

        txtName.setText(product.getName());
        txtQuantity.setText(String.valueOf(product.getQuantity()));
        txtPrice.setText(product.getPrice());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog
                        .Builder(context)
                        .setTitle("Remoção de item")
                        .setMessage("Deseja remover este item")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDeleteClick(product);
                            }
                        })
                        .setNeutralButton("Não",null)
                        .show();
            }

        });


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onclickList(product);
            }
        });



    }

}
