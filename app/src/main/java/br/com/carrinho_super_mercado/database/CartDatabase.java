package br.com.carrinho_super_mercado.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.carrinho_super_mercado.database.dao.CartDao;
import br.com.carrinho_super_mercado.model.Amount;
import br.com.carrinho_super_mercado.model.Product;

@Database(entities = {Product.class, Amount.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "product";

    public abstract CartDao getCartDatabase();

    public static CartDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, CartDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

    }


}
