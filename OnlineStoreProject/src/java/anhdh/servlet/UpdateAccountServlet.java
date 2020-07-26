/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhdh.servlet;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author babyz
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

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
        Map<String, String> authorizationMap
                    = (Map<String, String>) context.getAttribute("AUTHORIZATION_MAP");

        String searchValue = request.getParameter("lastSearchValue");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String address = request.getParameter("txtAddress");
        String fullName = request.getParameter("txtFullname");
        String admin = request.getParameter("chkAdmin");
        boolean isAdmin = false;
        if (admin != null) {
            isAdmin = true;
        }
        String url = authorizationMap.get("searchpage");

        TblUserError errors = new TblUserError();
        boolean errorFound = false;

        try {
            if (!password.trim().matches("(.){6,20}")) {
                errors.setPasswordLengthErr("Password is required 6 to 20 characters!");
                errorFound = true;
            }
            if (!fullName.trim().matches("([a-zA-Z\\s]+){2,50}")) {
                errors.setFullNameLengthErr("Full name is requiered 2 to 50 word characters!");
                errorFound = true;
            }
            if (!address.trim().matches("([a-zA-Z0-9\\s\\-\\.\\,/]+){10,200}")) {
                errors.setAddressLengthErr("Address length between 10 and 200 (letters | numbers | / | . | , | - )");
                errorFound = true;
            }
            if (errorFound) {
                request.setAttribute("USER_INFORMATION_ERROR", errors);
            } else {
                TblUserDAO dao = new TblUserDAO();
                TblUserDTO dto = new TblUserDTO(username, password, fullName, address, isAdmin);
                dao.updateAccount(dto);
                HttpSession session = request.getSession(false);
                TblUserDTO userSession
                        = (TblUserDTO) session.getAttribute("USER_INFORMATION");
                if (userSession.getuUsername().equals(dto.getuUsername())) {
                    Cookie usernameCookie = new Cookie("username", username);
                    Cookie passwordCookie = new Cookie("password", password);
                    usernameCookie.setMaxAge(60 * 5);
                    passwordCookie.setMaxAge(60 * 5);
                    response.addCookie(passwordCookie);
                }
            }
            url = authorizationMap.get("search")
                    + "?txtSearchValue=" + searchValue;
        } catch (NamingException ex) {
            log("UpdateAccountServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("UpdateAccountServlet_SQL " + ex.getMessage());
        } catch (NullPointerException ex) {
            log("UpdateAccountServlet_Null " + ex.getMessage());
            HttpSession session = request.getSession(false);
            if (session == null) {
                url = authorizationMap.get("");
            }
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
