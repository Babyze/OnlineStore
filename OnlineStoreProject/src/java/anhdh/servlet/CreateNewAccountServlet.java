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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author babyz
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String fullName = request.getParameter("txtFullname");
        String url = authorizationMap.get("createaccountpageerror");

        TblUserError errors = new TblUserError();
        boolean errorFound = false;
        try {
            if (!username.matches("(.){6,20}")) {
                errors.setUsernameLengthErr("Username is required from 6 to 20 characters");
                errorFound = true;
            }
            if (!password.matches("(.){6,20}")) {
                errors.setPasswordLengthErr("Password is required from 6 to 20 characters");
                errorFound = true;
            } else if (!confirmPassword.equals(password)) {
                errors.setConfirmPasswordMatched("Confirm password must match password");
            }
            if (!fullName.matches("([a-zA-Z\\s]+){2,50}")) {
                errors.setFullNameLengthErr("Full name is requiered 2 to 50 word characters!");
                errorFound = true;
            }
            if (errorFound) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
                TblUserDAO dao = new TblUserDAO();
                TblUserDTO dto = new TblUserDTO(username, password, fullName, "", false);
                boolean result = dao.createNewAccount(dto);
                if (result) {
                    url = authorizationMap.get("");
                }
            }
        } catch (NamingException ex) {
            log("CreateNewAccountServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("CreateNewAccountServerlet_SQL " + ex.getMessage());
            String msg = ex.getMessage();
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted("Username is existed!");
                request.setAttribute("CREATE_ERROR", errors);
            }
        } catch (NullPointerException ex) {
            log("CreateNewAccountServlet_Null " + ex.getMessage());
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
