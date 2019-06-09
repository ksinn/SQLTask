/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import TasKer.Web.TasKerServlet;
import _Model.DBManeger;
import _Model.TaskList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksinn
 */
public class DataBaseManager extends TasKerServlet {


    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            request.getRequestDispatcher("DataBaseManager.jsp").forward(request, response);
        
    }


    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        request.setCharacterEncoding("UTF-8");
        String query = "";
        String message = "";
        query = request.getParameter("query");
        TaskList group = new TaskList(Integer.parseInt(request.getParameter("group")));
        if(group.getUser()==user_id){
            DBManeger man = new DBManeger(query, group);
            message = man.getMessage();
        } else {
            request.setAttribute("message", "You cannot create anything in this group!");
            request.getRequestDispatcher("/Message.jsp").forward(request, response);
        }
        
        
        request.setAttribute("message", message);
        request.setAttribute("query", query);
            
        request.getRequestDispatcher("DataBaseManager.jsp").forward(request, response);
            
    }


    @Override
    protected int PrivateMod() {
        return TasKerServlet.OnlyForAuthorized;
    }

}
