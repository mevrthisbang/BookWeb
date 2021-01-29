/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author mevrthisbang
 */
public class OrderLineDTO implements Serializable{
    private String orderLineID, orderID, bookID;
    int quantity;
    double price;

    public OrderLineDTO() {
    }

    public OrderLineDTO(String orderLineID, String orderID, String bookID, int quantity, double price) {
        this.orderLineID = orderLineID;
        this.orderID = orderID;
        this.bookID = bookID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(String orderLineID) {
        this.orderLineID = orderLineID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
