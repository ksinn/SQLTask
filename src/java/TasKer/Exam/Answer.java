/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam;

import TasKer.TasKer;
import TasKer.Tasks.Task;

/**
 *
 * @author ksinn
 */
public interface Answer extends TasKer{
    
    public Task getTask();
    
    public void setTask(Task task);
    
    public boolean valid(Task task);
    
}
