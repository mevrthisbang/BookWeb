/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author mevrthisbang
 */
public class CartObj implements Serializable {

    private String customerID;
    private HashMap<String, BookDTO> cart;

    public CartObj() {
        this.customerID = "Guest";
        this.cart = new HashMap<>();
    }

    public CartObj(String customerID) {
        this.customerID = customerID;
        this.cart = new HashMap<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public HashMap<String, BookDTO> getCart() {
        return cart;
    }

    public void addToCart(BookDTO book) throws Exception {
        if (this.cart.containsKey(book.getId())) {
            int quantity = this.cart.get(book.getId()).getQuantity() + book.getQuantity();
            book.setQuantity(quantity);
        } else {
            book.setQuantity(1);
        }
        cart.put(book.getId(), book);
    }
    public void removeFromCart(String bookID) throws Exception{
        if(this.cart.containsKey(bookID)){
            this.cart.remove(bookID);
        }
    }
    public double getTotal(){
        double total=0;
        for(BookDTO book:this.cart.values()){
            total+=book.getPrice()*book.getQuantity();
        }
        return total;
    }
}
