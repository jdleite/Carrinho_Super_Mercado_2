package br.com.carrinho_super_mercado.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.carrinho_super_mercado.R;
import br.com.carrinho_super_mercado.asynctask.ListAsyncTask;
import br.com.carrinho_super_mercado.asynctask.RemoveAsyncTask;
import br.com.carrinho_super_mercado.asynctask.SaveAsyncTask;
import br.com.carrinho_super_mercado.asynctask.SubAsyncTask;
import br.com.carrinho_super_mercado.database.CartDatabase;
import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Amount;
import br.com.carrinho_super_mercado.model.Product;
import br.com.carrinho_super_mercado.ui.adapter.CartAdapter;
import br.com.carrinho_super_mercado.ui.listener.CartListener;

import static br.com.carrinho_super_mercado.ui.activity.Constants.PRODUCT_KEY;

public class MainActivity extends AppCompatActivity {

    private CartListener listener;
    private CartDao dao;
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<Product> productList = new ArrayList<>();
    TextView txtQuantity, txtTotPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txtQuantity = findViewById(R.id.id_txt_quantity);
        txtTotPrice = findViewById(R.id.id_txt_tot_price);
        CartDatabase database = CartDatabase.getInstance(this);
        dao = database.getCartDatabase();
        recyclerView = findViewById(R.id.id_recyclerview);
        getListener();
        getForm();


        Toast.makeText(this, "asda " + dao.getTotPrice(), Toast.LENGTH_LONG).show();


    }


    private void getForm() {
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));
            }
        });
    }

    private void editMode(Product product){
        Intent intent = new Intent(MainActivity.this,FormActivity.class);
        intent.putExtra(PRODUCT_KEY, product);
        startActivity(intent);

    }

    private void getListener() {
        listener = new CartListener() {
            @Override
            public void onclickList(Product product) {

                editMode(product);

            }

            @Override
            public void onDeleteClick(Product product) {
                dao.delete(product);
                BigDecimal bd = new BigDecimal(dao.getTotPrice()).subtract(new BigDecimal(product.getPrice().replaceAll(",", ".")));
                dao.totPrice(String.valueOf(bd));
                adapter.load(dao.listAll());
                dao.subtractionQuantity(product.getQuantity());
                onResume();
            }
        };
    }

    private void getList() {
        adapter = new CartAdapter(listener, productList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dao.listAll();
        adapter.load(dao.listAll());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.id_clear_all) {


            deleteAll();

        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
        totQuantity();
        totPrice();
    }

    private void totQuantity() {


        txtQuantity.setText(String.valueOf(dao.getTotQuantity()));
    }

    private void totPrice() {

        if (dao.getAmountId()) {

            txtTotPrice.setText(String.valueOf("R$ " + dao.getTotPrice()));
        } else {
            txtTotPrice.setText("R$ 0.00");
        }


    }

    private void deleteAll() {
        dao.deleteAllAmount();
        dao.deleteAllProduct();
        onResume();

    }
}