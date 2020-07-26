/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.tblorder;

import anhdh.tbluser.TblUserDTO;
import anhdh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;

/**
 *
 * @author babyz
 */
public class TblOrderDAO implements Serializable {

    public int createNewOrder(TblUserDTO user) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (cnn != null) {
                String query = "INSERT INTO tblOrder VALUES(?,?,?)";
                ps = cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getuUsername());
                ps.setString(2, user.getuFullname());
                ps.setString(3, user.getuAddress());
                int result = ps.executeUpdate();
                if (result > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        int primaryKey = rs.getInt(1);
                        return primaryKey;
                    }
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
        return -1;
    }
    
    public boolean updateOrder(String username) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "UPDATE tblOrder SET uUsername = ? WHERE uUsername = ?";
            ps = cnn.prepareStatement(query);
            ps.setString(1, null);
            ps.setString(2, username);
            int result = ps.executeUpdate();
            if(result > 0) {
                return true;
            }
        } finally {
            if(cnn != null) {
                cnn.close();
            }
        }
        return false;
    }

}
