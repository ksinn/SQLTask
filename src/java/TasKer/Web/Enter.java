/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Web;

import TasKer.Core.JWTHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.oauth.jsontoken.JsonToken;

/**
 *
 * @author ksinn
 */
public class Enter extends TasKerServlet {

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JsonToken iToken = JWTHelper.parsJWT(request.getParameter("t"));
        if (JWTHelper.check(iToken, getServerName())) {
            int user_id = iToken.getParamAsPrimitive("user_id").getAsInt();
            request.getSession().setAttribute("user_id", user_id);
            response.sendRedirect(request.getContextPath() + "/cabinet");
        } else {
            throw new Exception("Uncorect link");
        }

    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.ForAll;
    }

}
