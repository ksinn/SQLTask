/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import _Model.StudentConnect;
import TasKer.Web.TasKerServlet;
import _Model.Task;
import _Model.TaskList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksinn
 */
public class CheckGroup extends TasKerServlet {

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        int group;
        TaskList task_group = new TaskList();

        group = Integer.parseInt(request.getParameter("group"));
        task_group.getById(group);

        Map<Task, String> tasks = new HashMap<Task, String>();
        if (user_id == task_group.getUser()) {
            Iterator<Task> iterator = task_group.getTasks().iterator();
            while (iterator.hasNext()) {
                Task next = iterator.next();
                StudentConnect conn = null;
                try {
                    conn = new StudentConnect(task_group.getShema());
                    conn.exequtQuery(next.getAnswer());
                    if(conn.getException()!=null){
                        tasks.put(next, conn.getException().getMessage());
                    } else {
                        if(conn.getResultSet().next()){
                            tasks.put(next, null);
                        } else {
                            tasks.put(next, "");
                        }                            
                    }
                } finally {
                    if (conn != null) {
                        conn.close();
                    }
                }
            }

            request.setAttribute("group", task_group);
            request.setAttribute("tasks", tasks);
            request.getRequestDispatcher("Group_1.jsp").forward(request, response);
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
