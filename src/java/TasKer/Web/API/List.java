/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Web.API;

import TasKer.Tasks.Impl.Service;
import TasKer.Core.JWTHelper;
import static TasKer.TasKer.getListFactory;
import com.google.gson.JsonObject;
import TasKer.Web.TasKerServlet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.oauth.jsontoken.JsonToken;

/**
 *
 * @author ksinn
 */
public class List extends TasKerServlet {

    @Override
    protected void doMyGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    @Override
    protected void doMyPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int userid = Integer.parseInt(request.getParameter("user"));
        String anwser = request.getParameter("anwser");

        ArrayList<TasKer.Tasks.List> list = getListFactory().create().getByUserId(userid);

        Map<Integer, String> map = new HashMap<Integer, String>();
        Iterator<TasKer.Tasks.List> iterator = list.iterator();
        while (iterator.hasNext()) {
            TasKer.Tasks.List next = iterator.next();
            map.put(next.getId(), next.getName().replace(",", ""));
        }

        Service service = new Service();
        service.getById(1);

        JsonToken token = JWTHelper.newJWT(getServerName());

        JsonObject payload = token.getPayloadAsJsonObject();
        if (map.isEmpty()) {
            payload.addProperty("status", 400);
            payload.addProperty("error", "No list for user");
        } else {
            payload.addProperty("status", 200);
            payload.addProperty("list", map.toString());
        }
        response.getWriter().write(token.serializeAndSign());

    }

    @Override
    protected int PrivateMod() {
        return TasKerServlet.ForAll;
    }

}
