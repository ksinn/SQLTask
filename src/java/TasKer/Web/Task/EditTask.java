package TasKer.Web.Task;


import static TasKer.TasKer.getTaskFactory;
import TasKer.Tasks.Task;
import TasKer.Web.TasKerServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditTask extends TasKerServlet {
        private static final String view = "../taskForm.jsp";

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        Task task = getTaskFactory().createById(id);
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

        int id = Integer.parseInt(request.getParameter("id"));
        Task task = getTaskFactory().createById(id);
        if (task.getList().getUserId() == user_id) {
            task = getTaskFactory().create(request, task);
            if (task.valid()) {
                if (task.update()) {
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
