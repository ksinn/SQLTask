/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Tasks;

/**
 *
 * @author ksinn
 */
public interface Task {
    
    public boolean valid() throws Exception;
    
    public boolean save() throws Exception;
    
    public boolean update() throws Exception;
    
    public void getById(int id) throws Exception;
    
    public int getId();
    
    public List getList();
    
    public int getListId();
    
    public int getBall();
    
    public int getTime();
    
    public void setList(List list);
    
    public void setTime(int time);
    
    public void setBall(int ball);
    
    
}
