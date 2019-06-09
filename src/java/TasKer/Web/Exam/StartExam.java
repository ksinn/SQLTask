package TasKer.Web.Exam;

import API.HTTPClient;
import TasKer.Work.Impl.WorkEntety;
import TasKer.Core.JWTHelper;
import TasKer.Web.TasKerServlet;
import static TasKer.TasKer.getListFactory;
import TasKer.Exam.Examinator;
import TasKer.Exam.Impl.SimpleExamenator;
import TasKer.Tasks.Impl.Service;
import TasKer.Work.Work;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.oauth.jsontoken.JsonToken;

public class StartExam extends TasKerServlet {

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Service service = new Service();
        service.getById(1);
        JsonToken iToken = JWTHelper.parsJWT(request.getParameter("t"));
        if (JWTHelper.check(iToken, getServerName())) {
            String WORK_KEY = iToken.getParamAsPrimitive("work_key").getAsString();
            HTTPClient client = new HTTPClient(service.getURL() + "/api/work/get", "work_key=" + WORK_KEY, "POST");
            client.sendRequest();
            JsonToken token = JWTHelper.parsJWT(client.getRequestText());
            if (JWTHelper.check(token,  getServerName())) {
                if (token.getParamAsPrimitive("status").getAsInt() == 200) {
                    Work work = new WorkEntety();
                    Examinator exam = new SimpleExamenator();
                    if (!work.getByKey(WORK_KEY)) {
                        work.setCount(token.getParamAsPrimitive("count").getAsInt());
                        work.setList(getListFactory().createById(token.getParamAsPrimitive("list").getAsInt()));
                        work.setKeyFromString(token.getParamAsPrimitive("work_key").getAsString());
                        work.setUserId(token.getParamAsPrimitive("user_id").getAsInt());
                        if (!work.save()) {
                            throw new Exception("Error writing");
                        }
                        exam.prepareExam(work);
                    } else {
                        exam.continueExam(work);
                    }
                    
                    request.getSession().setAttribute("examinator", exam);
                    response.sendRedirect("do");
                    return;
                } else {
                    throw new Exception(token.getParamAsPrimitive("errorMessage").getAsString());
                }
            }}

            throw new Exception("Uncorect link");

        }

        @Override
        protected void doMyPost
        (HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        }

        @Override
        protected int PrivateMod
        
            () {
        return TasKerServlet.ForAll;
        }


    }
