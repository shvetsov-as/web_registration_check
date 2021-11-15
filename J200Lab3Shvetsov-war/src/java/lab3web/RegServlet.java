/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3web;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab3ejb.EJBDemo;

/**
 *
 * @author User
 */
@WebServlet(name = "RegServlet", urlPatterns = {"/RegServlet"})
public class RegServlet extends HttpServlet {

    EJBDemo registrator = lookupRegistratorLocal();//click --> insert code -->  call enterprise bean --> Inteface

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String login = request.getParameter("login");
        String psw = request.getParameter("psw");
        String msg = null;
        boolean registered = false;

        if (request.getParameter("sign") != null) {

            registered = registrator.login(login, psw);
            if (registered) {
                registrator.setName(login);
                msg = "Hello, " + login;
            } else {
                msg = "You entered an incorrect username or password";
            }

        } else if (request.getParameter("msg") != null) {
            msg = registrator.getMessage(login);

        }

        request.setAttribute("msg", msg);
        request.getRequestDispatcher("index.jsp").forward(request, response);//libo eto libo otvet-kod

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Login  " + login + "  password " + psw + " registered " + registered + "</h1>");
//            out.println("<h1>Get message login " + login + "  msg " + msg + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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

    private EJBDemo lookupRegistratorLocal() {
        try {
            Context c = new InitialContext();
            return (EJBDemo) c.lookup("java:global/J200Lab3Shvetsov/J200Lab3Shvetsov-ejb/Registrator!lab3ejb.EJBDemo");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
