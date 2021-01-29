/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import db.MyConnection;
import dto.AccountDTO;
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
public class AccountDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public AccountDAO() {
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

    public AccountDTO checkLogin(String email, String password) throws Exception {
        AccountDTO result = null;
        String username, firstname, lastname, role, phone, address;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select username, role, firstname, lastname, phone, address From AccountTBL\n"
                    + "Where email=? AND password=? and isDisable=0";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
                role = rs.getString("role");
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                phone = rs.getString("phone");
                address = rs.getString("address");
                result = new AccountDTO(username, firstname, lastname, role);
                result.setAddress(address);
                result.setPhone(phone);
                result.setEmail(email);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertNewAccount(AccountDTO account) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Insert Into AccountTBL(username, password, firstname, lastname, email, phone, gender, address, description, role)\n"
                    + "Values(?,?,?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getUsername());
            preStm.setString(2, account.getPassword());
            preStm.setString(3, account.getFirstname());
            preStm.setString(4, account.getLastname());
            preStm.setString(5, account.getEmail());
            preStm.setString(6, account.getPhone());
            preStm.setString(7, account.getGender());
            preStm.setString(8, account.getAddress());
            preStm.setString(9, account.getDescription());
            preStm.setString(10, account.getRole());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateAccount(AccountDTO account) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update AccountTBL\n"
                    + "Set email=?, password=?, firstname=?, lastname=?, phone=?, gender=?, address=?, description=?\n"
                    + "Where username=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getEmail());
            preStm.setString(2, account.getPassword());
            preStm.setString(3, account.getFirstname());
            preStm.setString(4, account.getLastname());
            preStm.setString(5, account.getPhone());
            preStm.setString(6, account.getGender());
            preStm.setString(7, account.getAddress());
            preStm.setString(8, account.getDescription());
            preStm.setString(9, account.getUsername());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String getLastInsertUsername() throws Exception {
        String result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select username\n"
                    + "From AccountTBL\n"
                    + "Where dateOfCreate=(Select MAX(dateOfCreate)\n"
                    + "From AccountTBL)";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getString("username");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean disableAccount(String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update AccountTBL\n"
                    + "Set isDisable=1\n"
                    + "Where username=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<AccountDTO> getAllEmployee() throws Exception {
        List<AccountDTO> result = null;
        String username, lastname, firstname, gender, email, phone;
        AccountDTO employee;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select username, lastname, firstname, email, phone, gender\n"
                    + "From AccountTBL\n"
                    + "Where role='employee' AND isDisable=0";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("username");
                lastname = rs.getString("lastname");
                firstname = rs.getString("firstname");
                gender = rs.getString("gender");
                email = rs.getString("email");
                phone = rs.getString("phone");
                employee = new AccountDTO(username, firstname, lastname, "employee");
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setGender(gender);
                result.add(employee);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public AccountDTO getAccountByPrimaryKey(String username) throws Exception {
        AccountDTO result = null;
        String firstname, lastname, role, email, phone, gender, address, description;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select firstname, lastname, role, email, phone, gender, address, description, role, isStillWorking From AccountTBL\n"
                    + "Where username=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                email = rs.getString("email");
                phone = rs.getString("phone");
                gender = rs.getString("gender");
                address = rs.getString("address");
                description = rs.getString("description");
                role = rs.getString("role");
                boolean isStillWorking = rs.getBoolean("isStillWorking");
                result = new AccountDTO(username, firstname, lastname, email, phone, gender, address, description);
                result.setRole(role);
                result.setIsStillWorking(isStillWorking);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getPasswordByUsername(String username) throws Exception {
        String result = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select password From AccountTBL\n"
                    + "Where username=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getString("password");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean existedEmail(String email) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select email From AccountTBL\n"
                    + "Where email=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean existedPhone(String phone) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select phone From AccountTBL\n"
                    + "Where phone=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<AccountDTO> getEmployeesByLikeName(String search) throws Exception {
        List<AccountDTO> result = null;
        String username, lastname, firstname, gender, email, phone;
        AccountDTO employee;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Select username, lastname, firstname, email, phone, gender\n"
                    + "From AccountTBL\n"
                    + "Where role='employee' AND lastname LIKE ? OR role='employee' AND firstname LIKE ?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setString(2, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("username");
                lastname = rs.getString("lastname");
                firstname = rs.getString("firstname");
                gender = rs.getString("gender");
                email = rs.getString("email");
                phone = rs.getString("phone");
                employee = new AccountDTO(username, firstname, lastname, "employee");
                employee.setEmail(email);
                employee.setPhone(phone);
                employee.setGender(gender);
                result.add(employee);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertNewEmployee(AccountDTO account) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Insert Into AccountTBL(username, firstname, lastname, email, phone, gender, address, description, role, isStillWorking)\n"
                    + "Values(?,?,?,?,?,?,?,?,?,?)";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getUsername());
            preStm.setString(2, account.getFirstname());
            preStm.setString(3, account.getLastname());
            preStm.setString(4, account.getEmail());
            preStm.setString(5, account.getPhone());
            preStm.setString(6, account.getGender());
            preStm.setString(7, account.getAddress());
            preStm.setString(8, account.getDescription());
            preStm.setString(9, account.getRole());
            preStm.setBoolean(10, account.isIsStillWorking());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateEmployee(AccountDTO account) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update AccountTBL\n"
                    + "Set email=?, firstname=?, lastname=?, phone=?, gender=?, address=?, description=?, role=?, isStillWorking=?\n"
                    + "Where username=?";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, account.getEmail());
            preStm.setString(2, account.getFirstname());
            preStm.setString(3, account.getLastname());
            preStm.setString(4, account.getPhone());
            preStm.setString(5, account.getGender());
            preStm.setString(6, account.getAddress());
            preStm.setString(7, account.getDescription());
            preStm.setString(8, account.getRole());
            preStm.setBoolean(9, account.isIsStillWorking());
            preStm.setString(10, account.getUsername());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    public boolean disableEmployee(String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "Update AccountTBL\n"
                    + "Set isDisable=1\n"
                    + "Where username=? AND isStillWorking=0";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
