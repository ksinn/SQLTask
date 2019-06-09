/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Web.Exam;

import TasKer.Exam.Examinator;
import TasKer.Web.TasKerServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksinn
 */
public class Next extends TasKerServlet {

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Examinator exam = (Examinator) request.getSession().getAttribute("examinator");
        if (exam == null) {
            throw new Exception("null exam");
        }
        if (exam.next()) {
            response.sendRedirect(request.getContextPath() + "/exam/do");
        } else {
            response.sendRedirect(request.getContextPath() + "/exam/finish");
        }
    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.ForAll;
    }

}
