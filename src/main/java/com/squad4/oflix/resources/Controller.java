package com.squad4.oflix.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author savio
 */
public class Controller extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = getId(request);
        String verb = request.getMethod();
        String action = getAction(request);
        boolean badRequest = extraParameters(request);

        response.setContentType("text/html;charset=UTF-8");
        
        if (badRequest){ response.setStatus(400); return; }
        
        response.getWriter().println(id + " " + verb + " " + action + " " + badRequest);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Rota de usuários";
    }// </editor-fold>
    
    private void AddParameters(HttpServletRequest request, HttpServletResponse response){
    }
    
    private int getId(HttpServletRequest request){
        int id = -1;
        
        try {
            id = Integer.parseInt(request.getPathInfo().split("/")[1]);
            if(id <= 0) id = -1;
        }
        catch(Exception e){ id = -1; }
        
        return id;
    }
    private String getAction(HttpServletRequest request){
        String action = "";
        boolean id = getId(request) != -1;
        int index = 1;
        if(id) index++ ;

        try{ action = request.getPathInfo().split("/")[index]; }
        catch(Exception e){ action = ""; }
        
        if(action == ""){
            if(id) action = "show";
            else action = "index";
        }
        return action;
    }
    private boolean extraParameters(HttpServletRequest request){
        int args = 1;
        if(getId(request) != -1) args++ ;
        if(getAction(request) != null) args++ ;
        System.out.println("\n*********\n" + args + "\n********\n" + request.getPathInfo() + "\n********\n" + request.getPathInfo().split("/").length);
        return !(args == request.getPathInfo().split("/").length);
    }

}
