package com.squad4.oflix.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.squad4.oflix.model.*;
@WebServlet(urlPatterns = {"/usuarios/*"})

public class UsersController extends Controller {
    @Override
    protected void doIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("resources" , User.getResources(""));
        request.setAttribute("pageNumbers", User.pageQuantity(""));
        request.getRequestDispatcher("/Usuario/index.jsp").forward(request, response);
    }
    
    @Override
    protected void doNew(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("method", "Novo");
        request.getRequestDispatcher("/Usuario/form.jsp").forward(request, response);
    }
    
    @Override
    protected void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("workd");
    }
}
