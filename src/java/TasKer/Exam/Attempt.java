/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam;

/**
 *
 * @author ksinn
 */
public interface Attempt {
    
    public boolean save() throws Exception;

    public void getById(int id) throws Exception;

    public int getWorkId();

    public String getAnswer();

    public int getTaskId();
    
    public void setFlag(int accept);

    public void setWorkId(int work_id);

    public void setTaskId(int task_id);

    public void setAnswer(String answer);
    
}
