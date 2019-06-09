/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam;

import java.util.ArrayList;

/**
 *
 * @author ksinn
 */
public interface Result {

    public boolean save() throws Exception;
    
    public ArrayList<Result> getResultsByWork(int id) throws Exception;

    public int getWorkId();

    public int getMark();

    public int getTaskId();

    public void setWorkId(int work_id);

    public void setTaskId(int task_id);

    public void setMark(int mark);

}
