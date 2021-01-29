/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.MyConnection;
import dto.CategoryDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mevrthisbang
 */
public class CategoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CategoryDAO() {
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public List<CategoryDTO> getAllCategories() throws Exception {
        List<CategoryDTO> result = null;
        CategoryDTO dto;
        String id, name;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select bookCategoryID, name From BookCategoryTBL";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("bookCategoryID");
                name = rs.getString("name");
                dto = new CategoryDTO(id, name);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public CategoryDTO getCategoryByID(String cateID) throws Exception {
        CategoryDTO result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select name From BookCategoryTBL Where bookCategoryID=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, cateID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                result = new CategoryDTO(cateID, name);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
