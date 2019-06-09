/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam.Impl;

import TasKer.Tasks.List;
import TasKer.Tasks.Task;
import TasKer.Work.Work;
import TasKer.Exam.ExamTasksGenerator;
import TasKer.Exam.Result;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class StupedExamTasksGenerator implements ExamTasksGenerator {

    private static final Logger log = Logger.getLogger(StupedExamTasksGenerator.class.getName());

    @Override
    public ArrayList generate(Work work, List list, int count) {
        ArrayList<Task> examList = new ArrayList<Task>();

        Random gen = new Random(work.getId());
        int[] id_list = new int[count];

        for (int i = 0; i < count; i++) {
            int N = -1, c;
            do {
                c = 0;
                N = gen.nextInt(list.getTasks().size());
                for (int n = 0; n < i; n++) {
                    if (id_list[n] == N) {
                        c++;
                        break;
                    }
                }
            } while (c != 0);
            id_list[i] = N;
            examList.add(list.getTasks().get(N));
        }
        return examList;
    }

    @Override
    public ArrayList<Task> regenerate(Work work, List list, int count, ArrayList<Result> results) {
        ArrayList generate = generate(work, list, count);
        for (int i = 0; i < results.size(); i++) {
            generate.remove(0);
        }
        return generate;

    }

}
