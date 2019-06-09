package TasKer.Web.Exam;

import API.HTTPClient;
import TasKer.Tasks.Impl.Service;
import TasKer.Core.JWTHelper;
import TasKer.Exam.Examinator;
import TasKer.Web.TasKerServlet;
import TasKer.Work.Work;
import com.google.gson.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.oauth.jsontoken.JsonToken;

public class FinishExam extends TasKerServlet {

    private static final String view = "finish.jsp";


    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Service service = new Service();
        service.getById(1);

        Examinator exam = (Examinator) request.getSession().getAttribute("examinator");
        request.getSession().invalidate();
        if (exam == null) {
            throw new Exception("null exam");
        }
        Work work = exam.finishExam();

        JsonToken token = JWTHelper.newJWT(getServerName());
        JsonObject payload = token.getPayloadAsJsonObject();
        payload.addProperty("work_key", work.getKeyAsString());
        payload.addProperty("mark", work.getMark());
        HTTPClient client = new HTTPClient(service.getMarkURL(), token.serializeAndSign(), "POST");
        client.sendRequest();
        String error = "Error whith connect. Sed to you teach.";
        if (client.getRequestText() != null) {
            token = JWTHelper.parsJWT(client.getRequestText());
            if (token.getParamAsPrimitive("status").getAsInt() != 200) {
                error = "";
            }
        }
        request.setAttribute("oneTimeError", "");
        request.getSession().setAttribute("work", work);
        request.getRequestDispatcher(view).forward(request, response);
    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.ForAll;//OnlyForAuthorized;
    }
}
