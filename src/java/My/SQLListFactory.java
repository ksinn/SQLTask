/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My;

import TasKer.Core.ErrorParameterException;
import TasKer.Tasks.ListFactory;
import TasKer.Tasks.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class SQLListFactory implements ListFactory {

    private static final Logger log = Logger.getLogger(SQLListFactory.class.getName());

    @Override
    public List create(HttpServletRequest request) throws ErrorParameterException {
        try {
            SQLTaskList list = new SQLTaskList();

            Map<String, String[]> request_params = request.getParameterMap();

            String schema = null;
            int user_id = 0;
            String name = request_params.getOrDefault("name", new String[]{"unnamed"})[0];
            String publish = request_params.getOrDefault("public", new String[]{"0"})[0];
            String[] schemaAr = request_params.get("schema");

            ErrorParameterException exp = null;
            if (schemaAr != null) {
                schema = schemaAr[0];
            } else {
                exp = new ErrorParameterException("Miss parameter 'schema'");
            }
            if (request.getSession().getAttribute("user_id") != null) {
                try {
                    user_id = (int) request.getSession().getAttribute("user_id");
                } catch (Exception ex) {
                    exp = new ErrorParameterException("Error value of parameter 'user_id'");
                }
            } else {
                exp = new ErrorParameterException("Miss parameter 'user_id'");
            }

            if (exp == null) {
                list.setUserId(user_id);
                list.setName(name);
                list.setSchema(schema);
                list.setPublish("1".equals(publish));
                return list;
            } else {
                throw exp;
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    @Override
    public List create() {
        return new SQLTaskList();
    }

    @Override
    public List createById(int id) throws Exception {
        try {
            SQLTaskList list = new SQLTaskList();
            list.getById(id);
            return list;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public List create(HttpServletRequest request, List l) throws ErrorParameterException {
        try {
            SQLTaskList list = (SQLTaskList) l;

            Map<String, String[]> request_params = request.getParameterMap();

;
            String schema = request_params.getOrDefault("schema", new String[]{list.getSchema()})[0];
            String name = request_params.getOrDefault("name", new String[]{list.getName()})[0];
            String publish = request_params.getOrDefault("public", new String[]{list.isPublic() ? "1" : "0"})[0];

            list.setName(name);
            list.setSchema(schema);
            list.setPublish("on".equals(publish));
            return list;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

}
