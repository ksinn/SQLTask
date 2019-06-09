/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Tasks;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ksinn
 */
public interface List{    
    
    public ArrayList getByUserId(int user_id) throws Exception;
    
    public ArrayList<List> Lists(HashMap<String, Object> params) throws Exception;
    
    public void getById(int id) throws Exception;
    
    public boolean save() throws Exception;
    
    public boolean update() throws Exception;
    
    public int getId();
    
    public int getUserId();
    
    public String getName();
    
    public boolean isPublic();
    
    public ArrayList<Task> getTasks();
    
    public void setUserId(int value);
    
    public void setName(String value);
    
    public void setPublish(boolean value);
    
    public void setTasks(ArrayList<Task> tasks);

    

    
}
