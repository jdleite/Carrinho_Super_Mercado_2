package br.com.carrinho_super_mercado.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Amount {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    private String totPrice;
    private int totQuantity;


    public Amount() {
    }


    public void setTotPrice(String totPrice) {
        this.totPrice = totPrice;
    }

    public String getTotPrice(){
        return totPrice;
    }

    public void setTotQuantity(int totQuantity){
        this.totQuantity = totQuantity;
    }

    public int getTotQuantity(){
        return totQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
