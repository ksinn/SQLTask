/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam.Impl;


import TasKer.Exam.Answer;
import TasKer.Tasks.Task;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public abstract class SimpleAnswer implements Answer{

    private static final Logger log = Logger.getLogger(SimpleAnswer.class.getName());
    
    protected Task task;
        

    @Override
    public Task getTask() {
        return task;
    }
    
    @Override
    public void setTask(Task task) {
        if(valid(task))
            this.task=  task;
    }
    
}
