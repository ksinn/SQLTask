/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My;

import TasKer.Exam.Impl.SimpleAnswer;
import TasKer.Tasks.Task;

/**
 *
 * @author ksinn
 */
public class SQLAnswer extends SimpleAnswer{
    
    protected String query;
            
    public String getQuery(){
        return query;
    }    
    
    @Override
    public boolean valid(Task task){
        return task instanceof SQLTask;
    }
    
    public void setQuery(String query){
        this.query = query;
    }   
    
    @Override
    public String toString(){
        return query; 
    }
    
}
