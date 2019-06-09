package TasKer.Web.Task;

import static TasKer.TasKer.getTaskFactory;
import TasKer.Tasks.Task;
import TasKer.Web.TasKerServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTask extends TasKerServlet {
        private static final String view = "../taskForm.jsp";

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int list = Integer.parseInt(request.getParameter("list"));
        Task task = getTaskFactory().create(list);
        if (task.getList().getUserId() == user_id) {
            request.setAttribute("task", task);
            request.getRequestDispatcher(view).forward(request, response);
        } else {
            throw new Exception("You can not do this");
        }
    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Task task = getTaskFactory().create(request);
        if (task.getList().getUserId() == user_id) {
            if (task.valid()) {
                if (task.save()) {
                    response.sendRedirect(request.getServletContext().getContextPath() + "/task/task?id=" + task.getId());
                } else {
                    request.setAttribute("task", task);
                    request.getRequestDispatcher(view).forward(request, response);
                }
            } else {
                request.setAttribute("task", task);
                request.setAttribute("error", "Invalid task");
                request.getRequestDispatcher(view).forward(request, response);
            }
        } else {
            throw new Exception("You can not do this");
        }

    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.OnlyForAuthorized;
    }
    
    

}
