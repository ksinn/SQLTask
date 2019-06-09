/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Tasks.Impl;

import TasKer.Core.ErrorParameterException;
import TasKer.Tasks.List;
import TasKer.Tasks.ListFactory;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ksinn
 */
public class ListEntetyFactory implements ListFactory {

    @Override
    public List create(HttpServletRequest request) throws ErrorParameterException {

        ListEntety list = new ListEntety();
        
        Map<String, String[]> request_params = request.getParameterMap();
        
        int user_id = 0;
        String name = request_params.getOrDefault("name", new String[]{"unnamed"})[0];
        String publish = request_params.getOrDefault("public", new String[]{"0"})[0];
        
        ErrorParameterException exp = null;
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
            list.setPublish("1".equals(publish));
            return list;
        } else {
            throw exp;
        }

    }

    @Override
    public List create() {
        return new ListEntety();
    }

    @Override
    public List createById(int id) throws Exception {
        ListEntety list = new ListEntety();
        list.getById(id);
        return list;
    }

    @Override
    public List create(HttpServletRequest request, List l) throws ErrorParameterException {
        ListEntety list = (ListEntety) l;
        
        Map<String, String[]> request_params = request.getParameterMap();
        
        String name = request_params.getOrDefault("name", new String[]{list.getName()})[0];
        String publish = request_params.getOrDefault("public", new String[]{list.isPublic()?"1":"0"})[0];

        list.setName(name);
        list.setPublish("1".equals(publish));
        return list;

    }

}
