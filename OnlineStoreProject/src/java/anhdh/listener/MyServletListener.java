/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.listener;

import anhdh.tblproduct.TblProductDAO;
import anhdh.tblproduct.TblProductDTO;
import anhdh.utils.FileHelper;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author babyz
 */
public class MyServletListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String, String> siteMap = new HashMap<>();
        Map<String, String> authorizationMap = new HashMap<>();

        String realPath = context.getRealPath("/") + "WEB-INF";

        try {
            siteMap = FileHelper.getResource(realPath, "SiteMap");
            authorizationMap = FileHelper.getResource(realPath, "AuthorizationMap");
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.setAttribute("SITE_MAP", siteMap);
        context.setAttribute("AUTHORIZATION_MAP", authorizationMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        TblProductDAO dao = new TblProductDAO();
        try {
            dao.getProducts();
            List<TblProductDTO> listProducts = dao.getListProducts();
            session.setAttribute("LIST_PRODUCT", listProducts);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
