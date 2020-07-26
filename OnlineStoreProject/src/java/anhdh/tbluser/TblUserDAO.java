/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.tbluser;

import anhdh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author babyz
 */
public class TblUserDAO implements Serializable {

    public TblUserDTO checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (cnn != null) {
                String query = "SELECT uUsername, uPassword, uFullName, uAddress, isAdmin "
                        + "FROM tblUser "
                        + "WHERE uUsername LIKE ? AND uPassword LIKE ?";
                ps = cnn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String uUsername = rs.getString("uUsername");
                    String uPassword = rs.getString("uPassword");
                    String uFullname = rs.getString("uFullName");
                    String uAddress = rs.getString("uAddress");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    TblUserDTO dto = new TblUserDTO(uUsername, uPassword, uFullname, uAddress, isAdmin);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return null;
    }

    private List<TblUserDTO> listAccounts = null;

    public List<TblUserDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchName(String searchValue) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (cnn != null) {
                String query = "SELECT uUsername, uPassword, uFullName, uAddress, isAdmin "
                        + "FROM tblUser "
                        + "WHERE uFullname LIKE ?";
                ps = cnn.prepareStatement(query);
                ps.setString(1, "%" + searchValue + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String uUsername = rs.getString("uUsername");
                    String uPassword = rs.getString("uPassword");
                    String uFullname = rs.getString("uFullName");
                    String uAddress = rs.getString("uAddress");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    TblUserDTO dto = new TblUserDTO(uUsername, uPassword, uFullname, uAddress, isAdmin);
                    if (listAccounts == null) {
                        listAccounts = new ArrayList<TblUserDTO>();
                    }
                    listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public boolean createNewAccount(TblUserDTO newUser) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        try {
            if (cnn != null) {
                String query = "INSERT INTO tblUser VALUES(?,?,?,?,?)";
                ps = cnn.prepareStatement(query);
                ps.setString(1, newUser.getuUsername());
                ps.setString(2, newUser.getuPassword());
                ps.setString(3, newUser.getuFullname());
                ps.setString(4, "");
                ps.setBoolean(5, newUser.isIsAdmin());
                int result = ps.executeUpdate();
                if (result > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return false;
    }

    public boolean deleteAccount(String username) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        try {
            if (cnn != null) {
                String query = "DELETE tblUser "
                        + "WHERE uUsername = ?";
                ps = cnn.prepareStatement(query);
                ps.setString(1, username);
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return false;
    }

    public boolean updateAccount(TblUserDTO user) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        try {
            if (cnn != null) {
                String query = "UPDATE tblUser SET "
                        + "uPassword = ?, uFullName = ?, uAddress = ?, isAdmin = ? "
                        + "WHERE uUsername LIKE ?";
                ps = cnn.prepareStatement(query);
                ps.setString(1, user.getuPassword());
                ps.setString(2, user.getuFullname());
                ps.setString(3, user.getuAddress());
                ps.setBoolean(4, user.isIsAdmin());
                ps.setString(5, user.getuUsername());
                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return false;
    }
}
