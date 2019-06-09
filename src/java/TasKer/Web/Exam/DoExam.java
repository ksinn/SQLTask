package TasKer.Web.Exam;

import TasKer.Core.EndOfExamTasks;
import static TasKer.TasKer.getAnswerFactory;
import TasKer.Exam.Answer;
import TasKer.Exam.CheckedAnswer;
import TasKer.Exam.Examinator;
import TasKer.Web.TasKerServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoExam extends TasKerServlet {

    private static final String view = "do.jsp";

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Examinator exam = (Examinator) request.getSession().getAttribute("examinator");
        if (exam == null) {
            throw new Exception("null exam");
        }
        try {
            Answer answer = getAnswerFactory().create();
            answer.setTask(exam.currentTask());
            request.setAttribute("answer", answer);
            request.getRequestDispatcher(view).forward(request, response);
        } catch (EndOfExamTasks ex) {
            response.sendRedirect(request.getContextPath() + "/exam/finish");
        }

    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Examinator exam = (Examinator) request.getSession().getAttribute("examinator");
        if (exam == null) {
            throw new Exception("null exam");
        }
        Answer answer = getAnswerFactory().create(request);
        answer.setTask(exam.currentTask());

        CheckedAnswer checkedAnswer = exam.check(answer);
        if (checkedAnswer.isAccept()) {
            response.sendRedirect(request.getContextPath() + "/exam/next");
        } else {
            request.setAttribute("answer", answer);
            request.setAttribute("checkedAnswer", checkedAnswer);
            request.getRequestDispatcher(view).forward(request, response);
        }

    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.ForAll;// OnlyForAuthorized;
    }

}
