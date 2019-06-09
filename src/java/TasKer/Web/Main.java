/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Web;

import TasKer.Tasks.List;
import static TasKer.TasKer.getListFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksinn
 */
public class Main extends TasKerServlet {
    
    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

            ArrayList<List> list = getListFactory().create().getByUserId(user_id);
            request.setAttribute("lists", list);
            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
    
    }

    
    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected int PrivateMod() {
        return TasKerServlet.OnlyForAuthorized;
    }

}
