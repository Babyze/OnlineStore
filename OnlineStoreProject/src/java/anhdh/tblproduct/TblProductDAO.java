package anhdh.tblproduct;

import anhdh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author babyz
 */
public class TblProductDAO implements Serializable {

    private List<TblProductDTO> listProducts = null;

    public List<TblProductDTO> getListProducts() {
        return listProducts;
    }

    public void getProducts() throws SQLException, NamingException {
        Connection cnn = DBHelper.makeConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            if (cnn != null) {
                String query = "SELECT pId, pName "
                        + "FROM tblProduct";
                st = cnn.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    String pid = rs.getString("pId");
                    String pname = rs.getString("pName");
                    TblProductDTO dto = new TblProductDTO(pid, pname);
                    if (listProducts == null) {
                        listProducts = new ArrayList<>();
                    }
                    listProducts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public TblProductDTO getProduct(String pid) throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (cnn != null) {
                String query = "SELECT pId, pName "
                        + "FROM tblProduct "
                        + "WHERE pId = ?";
                ps = cnn.prepareStatement(query);
                ps.setString(1, pid);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String pname = rs.getString("pName");
                    TblProductDTO dto = new TblProductDTO(pid, pname);
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

}
