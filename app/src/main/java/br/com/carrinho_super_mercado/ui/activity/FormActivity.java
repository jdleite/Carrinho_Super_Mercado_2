package br.com.carrinho_super_mercado.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

import br.com.carrinho_super_mercado.R;
import br.com.carrinho_super_mercado.database.CartDatabase;
import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Amount;
import br.com.carrinho_super_mercado.model.Product;

import static br.com.carrinho_super_mercado.ui.activity.Constants.PRODUCT_KEY;

public class FormActivity extends AppCompatActivity {

    StartFields startFields = new StartFields();
    private CartDao dao;
    private Product product;
    private Amount amount = new Amount();
    int oldQuantity;
    String oldPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        CartDatabase database = CartDatabase.getInstance(this);
        dao = database.getCartDatabase();
        getFields();


    }


    private void getFields() {
        quantityField();
        priceField();
        productNameField();
        loadProduct();
        btnSave();
    }


    private void quantityField() {
        startFields.edtQuantity = findViewById(R.id.id_edt_product_quantity);
    }

    private void productNameField() {
        startFields.edtProductName = findViewById(R.id.id_edt_product_name);
    }

    private void priceField() {
        startFields.edtPrice = findViewById(R.id.id_edt_product_price);
    }

    private void setProduct() {
        String name = startFields.edtProductName.getEditText().getText().toString();
        String price = startFields.edtPrice.getText().toString();
        int quantity = Integer.parseInt(startFields.edtQuantity.getEditText().getText().toString());

        product.setName(name);
        product.setQuantity(quantity);
        product.setPrice(price);


        sumQuantity(price, quantity);


    }

    private void sumQuantity(String price, int quantity) {
        amount.setTotPrice(price.replaceAll(",", "."));
        amount.setTotQuantity(quantity);


        if (dao.getAmountId()) {
            String d = dao.getTotPrice();
            BigDecimal bd = new BigDecimal(d).add(new BigDecimal(amount.getTotPrice()));
            sumProduct(String.valueOf(bd), quantity);
        } else {
            sumProduct(amount.getTotPrice(), quantity);
        }
    }

    private void sumProduct(String price, int quantity) {
        int a = Integer.parseInt(price);
        int b = Integer.parseInt(oldPrice);
        int o = Integer.parseInt(startFields.edtQuantity.getEditText().getText().toString());
        if (oldQuantity < o || a < b) {

            dao.sumQuantity(quantity);
            dao.subtractionQuantity(oldQuantity);
            dao.totPrice(price);
        } else {

            dao.subtractionQuantity(oldQuantity);
            dao.sumQuantity(quantity);
        }
        dao.saveAmount(amount);

    }

    private void btnSave() {
        startFields.btnSave = findViewById(R.id.id_btn_save);
        startFields.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishForm(product);
                finish();
            }
        });
    }

    private void loadProduct() {

        Intent intent = getIntent();
        if (intent.hasExtra(PRODUCT_KEY)) {
            product = (Product) intent.getSerializableExtra(PRODUCT_KEY);
            oldQuantity = product.getQuantity();
            oldPrice = product.getPrice();
            fillFields();

        } else {
            product = new Product();
        }


    }

    private void fillFields() {
        startFields.edtProductName.getEditText().setText(product.getName());

        startFields.edtQuantity.getEditText().setText(String.valueOf(product.getQuantity()));
        startFields.edtPrice.setText(product.getPrice());

    }

    private class StartFields {
        TextInputLayout edtQuantity, edtProductName;
        EditText edtPrice;
        Button btnSave;
    }


    private void finishForm(Product product) {
        setProduct();
        if (product.validateId()) {
            dao.update(product);
        } else {
            dao.save(product);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}
