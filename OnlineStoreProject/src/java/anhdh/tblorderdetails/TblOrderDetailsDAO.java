/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.tblorderdetails;

import anhdh.tblproduct.TblProductDTO;
import anhdh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author babyz
 */
public class TblOrderDetailsDAO implements Serializable {

    public boolean insertOrderItem(int orderKey, Map<TblProductDTO, Integer> order)
            throws NamingException, SQLException {
        Connection cnn = DBHelper.makeConnection();
        PreparedStatement ps = null;
        try {
            if (cnn != null) {
                String query = "INSERT INTO tblOrderDetails VALUES "
                        + "(?,?,?)";
                cnn.setAutoCommit(false);
                for (TblProductDTO dto : order.keySet()) {
                    ps = cnn.prepareStatement(query);
                    ps.setString(1, dto.getPid());
                    ps.setInt(2, order.get(dto));
                    ps.setInt(3, orderKey);
                    ps.executeUpdate();
                }
                cnn.commit();
                return true;
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
