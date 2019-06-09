
// Метод generate возвращает ArrayList тасков заданного размера, 
// таски будут распологатся в ArrayList'е в порядке увеличения сложности, 
// количество тасков каждой сложности варьируется в зависимости от процентного содержания этой сложности в переданном list тасков

package TasKer.Exam.Impl;

import TasKer.Tasks.List;
import TasKer.Tasks.Task;
import TasKer.Work.Work;
import TasKer.Exam.ExamTasksGenerator;
import TasKer.Exam.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * @author Timur Galiullin
 */
 
public class CleverExamTasksGenerator implements ExamTasksGenerator {

    private static final Logger log = Logger.getLogger(StupedExamTasksGenerator.class.getName());

    @Override
    public ArrayList generate(Work work, List list, int count) {

        // Проверка на запрос большего количества тасков чем есть в list
        if (count > list.getTasks().size())
            count = list.getTasks().size();

        ArrayList<Task> examList = new ArrayList<Task>();                   // Возвращаемый ArrayList
        ArrayList<Task> easyTasks = new ArrayList<Task>();                  // ArrayList с легкими заданиями
        ArrayList<Task> mediumTasks = new ArrayList<Task>();                // ArrayList со средними заданиями
        ArrayList<Task> hardTasks = new ArrayList<Task>();                  // ArrayList со сложными заданиями
        ArrayList<Task> tempTasks = new ArrayList<Task>(list.getTasks());   // ArrayList, содержащий передаваемые из list taks'и

        int maxDifficulty = 0;      // Маскимальная сложность тасков
        int minDifficulty = 0;      // Минимальная сложность тасков
        int avgDifficulty;          // Средняя сложность тасков

        float easyCount = 0;        // Количество тасков по сложностям
        float mediumCount = 0;      //
        float hardCount = 0;        //

        // Поиск минимальной и максимальной сложности тасков
        for (int i = 0; i < tempTasks.size(); i++) {
            if (minDifficulty > tempTasks.get(i).getTime()) {
                minDifficulty = tempTasks.get(i).getTime();
            }
            if (maxDifficulty < tempTasks.get(i).getTime()) {
                maxDifficulty = tempTasks.get(i).getTime();
            }
        }
        avgDifficulty = (maxDifficulty + minDifficulty) / 2;

        // Распределение тасков по их уровню сложности
        for (int i = 0; i < tempTasks.size(); i++) {
            if (tempTasks.get(i).getTime() <= avgDifficulty) {
                easyTasks.add(tempTasks.get(i));
                easyCount++;
            } else if (tempTasks.get(i).getTime() <= avgDifficulty * 2) {
                mediumTasks.add(tempTasks.get(i));
                mediumCount++;
            } else {
                hardTasks.add(tempTasks.get(i));
                hardCount++;
            }
        }

        // Перетасовка тасков по сиду
        Collections.shuffle(easyTasks, new Random(work.getId()));
        Collections.shuffle(mediumTasks, new Random(work.getId()));
        Collections.shuffle(hardTasks, new Random(work.getId()));

        // Добавление тасков в возвращаемый ArrayList в зависимости от их процентного содержания по сложности
        for (int i = 0; i < Math.ceil(easyCount / tempTasks.size() * count); i++)
            examList.add(easyTasks.get(i));
        for (int i = 0; i < Math.ceil(mediumCount / tempTasks.size() * count); i++)
            examList.add(mediumTasks.get(i));
        for (int i = 0; i < Math.ceil(hardCount / tempTasks.size() * count); i++)
            examList.add(hardTasks.get(i));

        // Удаление лишних тасков, всегда удаляются самые легкие
        while (examList.size() > count) {
            examList.remove(0);
        }

        return examList;
    }

    public ArrayList<Task> regenerate(Work work, List list, int count, ArrayList<Result> results) {
        ArrayList generate = generate(work, list, count);
        for (int i = 0; i < results.size(); i++) {
            generate.remove(0);
        }
        return generate;
    }


}
