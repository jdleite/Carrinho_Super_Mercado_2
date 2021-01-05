package br.com.carrinho_super_mercado.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.carrinho_super_mercado.model.Amount;
import br.com.carrinho_super_mercado.model.Product;

@Dao
public interface CartDao {

    @Insert
    Long save(Product product);

    @Query("SELECT * FROM product ORDER BY ID DESC")
    List<Product> listAll();

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

    @Insert
    Long saveAmount(Amount amount);


    @Query("DELETE FROM product")
    void deleteAllProduct();

    @Query("DELETE FROM Amount")
    void deleteAllAmount();

    @Query("UPDATE AMOUNT SET totQuantity = totQuantity + :quantity")
    void sumQuantity(int quantity);

    @Query("UPDATE AMOUNT SET totQuantity = totQuantity - :quantity")
    void subtractionQuantity(int quantity);

    @Query("SELECT totQuantity FROM AMOUNT")
    int getTotQuantity();

    @Query("SELECT ID FROM AMOUNT")
    boolean getAmountId();

    @Query("SELECT totPrice from AMOUNT")
    String getTotPrice();

    @Query("UPDATE Amount SET totPrice =:price")
    void totPrice(String price);

    @Query("UPDATE AMOUNT SET totPrice = totPrice +:price")
    void SumTotPrice(String price);


}
