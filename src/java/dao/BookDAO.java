/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.MyConnection;
import dto.BookDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mevrthisbang
 */
public class BookDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BookDAO() {
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

    public List<BookDTO> getAllBooks() throws Exception {
        List<BookDTO> result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select bookID, title, shortDescription, thumbnailImg, price\n"
                    + "From BookTBL\n"
                    + "Where isDisable=0";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("bookID");
                String title = rs.getString("title");
                String shortDescription = rs.getString("shortDescription");
                String thumbnailImg = rs.getString("thumbnailImg");
                double price = rs.getDouble("price");
                BookDTO book = new BookDTO(id, title, shortDescription, thumbnailImg, price);
                result.add(book);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<BookDTO> getBooksByCategory(String cateID) throws Exception {
        List<BookDTO> result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select bookID, title, shortDescription, thumbnailImg, price\n"
                    + "From BookTBL\n"
                    + "Where categoryBookID=? AND isDisable=0";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, cateID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("bookID");
                String title = rs.getString("title");
                String shortDescription = rs.getString("shortDescription");
                String thumbnailImg = rs.getString("thumbnailImg");
                double price = rs.getDouble("price");
                BookDTO book = new BookDTO(id, title, shortDescription, thumbnailImg, price);
                result.add(book);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<BookDTO> getBooksByLikeName(String search) throws Exception {
        List<BookDTO> result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select bookID, title, shortDescription, thumbnailImg, price\n"
                    + "From BookTBL\n"
                    + "Where title LIKE ? and isDisable=0";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("bookID");
                String title = rs.getString("title");
                String shortDescription = rs.getString("shortDescription");
                String thumbnailImg = rs.getString("thumbnailImg");
                double price = rs.getDouble("price");
                BookDTO book = new BookDTO(id, title, shortDescription, thumbnailImg, price);
                result.add(book);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public BookDTO getBookByID(String bookID) throws Exception {
        BookDTO result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select title, shortDescription, description, author, mainImg, price, categoryBookID, releasedDate, issuers, publishingCompany,"
                    + "size, coverType, numOfPage, thumbnailImg, stillProducting\n"
                    + "From BookTBL\n"
                    + "Where bookID=? and isDisable=0";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String author = rs.getString("author");
                String mainImg = rs.getString("mainImg");
                String categoryBookID = rs.getString("categoryBookID");
                double price = rs.getDouble("price");
                Date releasedDate = new Date(rs.getTimestamp("releasedDate").getTime());
                String issuers = rs.getString("issuers");
                String publishingCompany = rs.getString("publishingCompany");
                String size = rs.getString("size");
                String coverType = rs.getString("coverType");
                int numOfPage = rs.getInt("numOfPage");
                String shortDescription = rs.getString("shortDescription");
                String thumbnailImg = rs.getString("thumbnailImg");
                boolean stillProducting=rs.getBoolean("stillProducting");
                result = new BookDTO(bookID, title, author, description, mainImg, categoryBookID, price);
                result.setCoverType(coverType);
                result.setIssuers(issuers);
                result.setNumOfPage(numOfPage);
                result.setPublishingCompany(publishingCompany);
                result.setReleasedDate(releasedDate);
                result.setSize(size);
                result.setShortDescription(shortDescription);
                result.setThumbnailImg(thumbnailImg);
                result.setStillProducing(stillProducting);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getLastInsertBookID() throws Exception {
        String result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select bookID\n"
                    + "From BookTBL\n"
                    + "Where dateOfCreate=(Select MAX(dateOfCreate)\n"
                    + "From BookTBL)";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getString("bookID");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertBook(BookDTO book) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Insert Into BookTBL(bookID, title, shortDescription, description, author, mainImg, price, categoryBookID, releasedDate, issuers, publishingCompany,"
                    + "size, coverType, numOfPage, thumbnailImg)\n"
                    + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, book.getId());
            preStm.setString(2, book.getTitle());
            preStm.setString(3, book.getShortDescription());
            preStm.setString(4, book.getDescritption());
            preStm.setString(5, book.getAuthor());
            preStm.setString(6, book.getMainImg());
            preStm.setDouble(7, book.getPrice());
            preStm.setString(8, book.getCategoryID());
            preStm.setTimestamp(9, new Timestamp(book.getReleasedDate().getTime()));
            preStm.setString(10, book.getIssuers());
            preStm.setString(11, book.getPublishingCompany());
            preStm.setString(12, book.getSize());
            preStm.setString(13, book.getCoverType());
            preStm.setInt(14, book.getNumOfPage());
            preStm.setString(15, book.getThumbnailImg());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateBook(BookDTO book) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update BookTBL\n"
                    + "Set title=?, shortDescription=?, description=?, author=?, mainImg=?, price=?, categoryBookID=?, releasedDate=?, issuers=?, publishingCompany=?,"
                    + "size=?, coverType=?, numOfPage=?, thumbnailImg=?, stillProducting=?\n"
                    + "Where bookID=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, book.getTitle());
            preStm.setString(2, book.getShortDescription());
            preStm.setString(3, book.getDescritption());
            preStm.setString(4, book.getAuthor());
            preStm.setString(5, book.getMainImg());
            preStm.setDouble(6, book.getPrice());
            preStm.setString(7, book.getCategoryID());
            preStm.setTimestamp(8, new Timestamp(book.getReleasedDate().getTime()));
            preStm.setString(9, book.getIssuers());
            preStm.setString(10, book.getPublishingCompany());
            preStm.setString(11, book.getSize());
            preStm.setString(12, book.getCoverType());
            preStm.setInt(13, book.getNumOfPage());
            preStm.setString(14, book.getThumbnailImg());
            preStm.setBoolean(15, book.isStillProducing());
            preStm.setString(16, book.getId());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean disableBook(String bookID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update BookTBL\n"
                    + "Set isDisable=1\n"
                    + "Where bookID=? AND stillProducting=0";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

}
