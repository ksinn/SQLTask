/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import API.HTTPClient;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ksinn
 */
public class User{
    int Id;
    String Mail;
    String Name;
    String Surname;
    String Ico;

    public int getId() {
        return this.Id;
    }

    public void setId(int data) {
        this.Id = data;
    }
    
    public ArrayList<TaskList> getGroup() {
        
        ArrayList<TaskList> list = new ArrayList<TaskList>();
        if(Id==0)
            return null;
        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("owner_id", this.Id);
            TaskList group = new TaskList();
            ArrayList<HashMap<String, Object>> Params = group.getObjectsParam(param);
            for(int i=0; i<Params.size(); i++){
                group = new TaskList();
                group.getFromParam(Params.get(i));
                group.ReadTasksFromBd();
                list.add(group); 
            }
        } catch (Exception ex) {
            Log.Write(ex.getMessage());
        }
        
        return list;
        
    }
    
    public boolean getUserData(){
       /* if(Id==0)
            return false;
        try {
            UserJWT wt = new UserJWT();
            HTTPClient client = new HTTPClient(AppInf.main+"/api/user_data", "id="+String.valueOf(this.Id), "POST");
            client.sendRequest();
            if(wt.getData(client.getRequestText(), this, AppInf.main)){
                return true;
            }
        } catch (Exception ex) {
            return false;
        }*/
        return false;
    }

    public String getMail() {
        return this.Mail;
    }

    public String getName() {
        return this.Name;
    }
    public String getSurname() {
        return this.Surname;
    }

    public void setMail(String data) {
        this.Mail = data;
    }
    public void setName(String data) {
        this.Name = data;
    }
    public void setSurname(String data) {
        this.Surname = data;
    }
    
    
}
