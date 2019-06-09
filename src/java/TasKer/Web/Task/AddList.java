/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Web.Task;

import TasKer.Tasks.List;
import TasKer.Web.TasKerServlet;
import static TasKer.TasKer.getListFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksinn
 */
public class AddList extends TasKerServlet {
    private static final String view = "../listForm.jsp";
    

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {      
        
        List list = getListFactory().create(request);            
        try {
            if (list.save()) {
                response.sendRedirect(request.getServletContext().getContextPath() + "/task/list?id=" + list.getId());
            } else {
                request.setAttribute("list", list);
                request.getRequestDispatcher(view).forward(request, response);
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.OnlyForAuthorized;
    }

}
