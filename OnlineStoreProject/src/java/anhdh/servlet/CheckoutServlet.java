/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.servlet;

import anhdh.cart.Cart;
import anhdh.tblorder.TblOrderDAO;
import anhdh.tblorderdetails.TblOrderDetailsDAO;
import anhdh.tblproduct.TblProductDTO;
import anhdh.tbluser.TblUserDAO;
import anhdh.tbluser.TblUserDTO;
import anhdh.tbluser.TblUserError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author babyz
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context = request.getServletContext();
        Map<String, String> siteMap
                = (Map<String, String>) context.getAttribute("SITE_MAP");

        String url = siteMap.get("orderpage");
        String fullName = request.getParameter("txtFullname");
        String address = request.getParameter("txtAddress");

        TblUserError errors = new TblUserError();
        boolean errorFound = false;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    if (!fullName.trim().matches("([a-zA-Z\\s]+){2,50}")) {
                        errors.setFullNameLengthErr("Full name is requiered 2 to 50 word characters!");
                        errorFound = true;
                    }
                    if (!address.trim().matches("([a-zA-Z0-9\\s\\-\\.\\,/]+){10,200}")) {
                        errors.setAddressLengthErr("Address length between 10 and 200 (letters | numbers | / | . | , | - )");
                        errorFound = true;
                    }
                    
                    if (errorFound) {
                        request.setAttribute("ERROR_INFORMATION", errors);
                    } else {
                        TblUserDTO user
                                = (TblUserDTO) session.getAttribute("USER_INFORMATION");
                        if (user != null) {
                            user.setuAddress(address);
                            TblUserDAO userDAO = new TblUserDAO();
                            userDAO.updateAccount(user);
                            user.setuFullname(fullName);
                        } else {
                            user = new TblUserDTO(fullName, address);
                        }
                        
                        TblOrderDAO orderDAO = new TblOrderDAO();
                        int orderKey = orderDAO.createNewOrder(user);
                        Map<TblProductDTO, Integer> order = cart.getCart();
                        TblOrderDetailsDAO orderDetailsDAO
                                = new TblOrderDetailsDAO();
                        boolean result = orderDetailsDAO.insertOrderItem(orderKey, order);
                        
                        if (result) {
                            request.setAttribute("CART", order);
                            session.setAttribute("CART", null);
                            url = siteMap.get("checkoutpage");
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            log("CheckoutServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            ex.printStackTrace();
            log("CheckoutServlet_SQL " + ex.getMessage());
        } catch (NullPointerException ex) {
            log("CheckoutServlet_Null " + ex.getMessage());
            errors.setEmptyParameter("Please check / fill your information before checkout!");
            request.setAttribute("ERROR_INFORMATION", errors);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
