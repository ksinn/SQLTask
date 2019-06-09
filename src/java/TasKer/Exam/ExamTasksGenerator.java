/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam;

import TasKer.Tasks.List;
import TasKer.Tasks.Task;
import TasKer.Work.Work;
import java.util.ArrayList;

/**
 *
 * @author ksinn
 */
public interface ExamTasksGenerator {
    
    public ArrayList<Task> generate(Work work, List list, int count);

    public ArrayList<Task> regenerate(Work work, List list, int count, ArrayList<Result> results);
    
}
