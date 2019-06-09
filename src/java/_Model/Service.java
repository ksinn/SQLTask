/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.util.HashMap;

/**
 *
 * @author ksinn
 */
public class Service extends Parant {

    private static String MarkAPI = "/api/work/mark";
    
    private String Name;
    private String URL;
    private String MyKey;
    private String ServiceKey;

    public Service() {

    }

    public String getServiceKey() {

        return this.ServiceKey;
    }

    public String getMyKey() {

        return this.MyKey;
    }

    public String getURL() {

        return this.URL;
    }

    public String getName() {

        return this.Name;
    }

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("name", this.Name);
        list.put("url", this.URL);
        list.put("my_key", this.MyKey);
        list.put("service_key", this.ServiceKey);

        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> Params) throws Exception {
        this.Name = (String) Params.get("name");
        this.URL = (String) Params.get("url");
        this.MyKey = (String) Params.get("my_key");
        this.ServiceKey = (String) Params.get("service_key");
    }

    @Override
    protected boolean _isCorrect() {
        return true;
    }

    public void getById(int id) throws Exception {
        if (id > 0) {
            this._id = id;
            this._select();            
        } else {
            throw new Exception("Invalid input data for teaching with id=" + _id);
        }
    }
    
    public String getMarkURL(){
        return this.URL + MarkAPI;
    }

    @Override
    protected String _getTableName() {
        return "auth_service";
    }

}
