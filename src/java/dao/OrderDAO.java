/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.MyConnection;
import dto.BookDTO;
import dto.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author mevrthisbang
 */
public class OrderDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStmOrder;
    private PreparedStatement preStmOrderLine;
    private ResultSet rs;

    public OrderDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStmOrderLine != null) {
            preStmOrderLine.close();
        }
        if (preStmOrder != null) {
            preStmOrder.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public String getLastOrderByUser(String username) throws Exception {
        String id = null;
        try {
            String sql = "Select orderID From OrderTBL\n"
                    + "Where order_date=(Select MAX(order_date)\n"
                    + "From OrderTBL Where order_by=?)";
            conn = MyConnection.getMyConnection();
            preStmOrder = conn.prepareStatement(sql);
            preStmOrder.setString(1, username);
            rs = preStmOrder.executeQuery();
            if (rs.next()) {
                id = rs.getString("orderID");
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public boolean insertNewOrder(OrderDTO order, HashMap<String, BookDTO> listOrderLine) throws Exception {
        boolean check = false;
        try {
            conn=MyConnection.getMyConnection();
            String sqlInsertOrder="Insert Into OrderTBL(orderID, paymentMethod, order_by, shipAddress, phone, total, fullname)\n"
                    + "Values(?,?,?,?,?,?,?)";
            String sqlInsertOrderLine="Insert Into OrderDetailTBL(orderDetailID, orderID, bookID, quantity, price)\n"
                    + "Values(?,?,?,?,?)";
            preStmOrder=conn.prepareStatement(sqlInsertOrder);
            preStmOrderLine=conn.prepareStatement(sqlInsertOrderLine);
            conn.setAutoCommit(false);
            preStmOrder.setString(1, order.getOrderID());
            preStmOrder.setString(2, order.getPaymentMethod());
            preStmOrder.setString(3, order.getOrderBy());
            preStmOrder.setString(4, order.getAddress());
            preStmOrder.setString(5, order.getPhone());
            preStmOrder.setDouble(6, order.getTotal());
            preStmOrder.setString(7, order.getReceiverName());
            int insertOrder=preStmOrder.executeUpdate();
            int insertOrderLine=0;
            int count=1;
            for(BookDTO book: listOrderLine.values()){
                preStmOrderLine.setString(1, order.getOrderID()+"-"+count);
                count++;
                preStmOrderLine.setString(2, order.getOrderID());
                preStmOrderLine.setString(3, book.getId());
                preStmOrderLine.setInt(4, book.getQuantity());
                preStmOrderLine.setDouble(5, book.getPrice());
                insertOrderLine+=preStmOrderLine.executeUpdate();
                
            }
            conn.commit();
            conn.setAutoCommit(true);
            check=insertOrder>0&&insertOrderLine>0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
