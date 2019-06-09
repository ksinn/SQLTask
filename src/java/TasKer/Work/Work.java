/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Work;

import TasKer.Tasks.List;
import java.util.UUID;

/**
 *
 * @author ksinn
 */
public interface Work {
    
    public boolean save() throws Exception;
    
    public boolean getByKey(String key) throws Exception;
    
    public void getById(int id) throws Exception;
    
    public int getId();
    
    public int getListId();
    
    public List getList(); 
    
    public int getUser(); 
    
    public UUID getKey();
    
    public String getKeyAsString();
    
    public int getCount();

    public int getMark();
    
    public void setList(List list);
    
    public void setUserId(int user_id);

    public void setKeyFromString(String key);
    
    public void setCount(int data);
    
    public void setMark(int mark);
    
}
