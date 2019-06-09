/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Web.Task;

import TasKer.Tasks.List;
import TasKer.Web.TasKerServlet;
import static TasKer.TasKer.getListFactory;
import TasKer.Tasks.Task;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksinn
 */
public class ListPage extends TasKerServlet {
    private static final String view = "list.jsp";

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        List list = getListFactory().createById(id);
        ArrayList<Task> tasks = list.getTasks();
        if (user_id == list.getUserId()) {
            request.setAttribute("list", list);
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher(view).forward(request, response);
        } else {
            throw new ServletException("You are not owner this work");
        }

    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.OnlyForAuthorized;
    }

}
