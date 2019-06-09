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
public class EditList extends TasKerServlet {

    private static final String view = "../listForm.jsp";

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        List list = getListFactory().createById(id);
        if (user_id == list.getUserId()) {
            request.setAttribute("list", list);
            request.getRequestDispatcher(view).forward(request, response);
        } else {
            request.setAttribute("message", "You cannot edit this group!");
            request.getRequestDispatcher("/Message.jsp").forward(request, response);
        }

    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        List list = getListFactory().createById(id);
        if (user_id == list.getUserId()) {
            list = getListFactory().create(request, list);
            try {
                if (list.update()) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/task/list?id=" + list.getId());
                } else {
                    request.setAttribute("list", list);
                    request.getRequestDispatcher(view).forward(request, response);
                }
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            request.setAttribute("message", "You cannot edit this group!");
            request.getRequestDispatcher("/Message.jsp").forward(request, response);
        }

    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.OnlyForAuthorized;
    }

}
