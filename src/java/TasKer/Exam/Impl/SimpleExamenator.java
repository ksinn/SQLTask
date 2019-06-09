/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam.Impl;

import static TasKer.TasKer.getChecker;
import static TasKer.TasKer.getExamTasksGenerator;
import TasKer.Tasks.Task;
import TasKer.Work.Work;
import TasKer.Core.InvalidAnswer;
import TasKer.Exam.Answer;
import TasKer.Exam.Attempt;
import TasKer.Exam.CheckedAnswer;
import TasKer.Exam.Examinator;
import TasKer.Exam.Result;
import java.util.ArrayList;
import java.util.Iterator;
import TasKer.Core.EndOfExamTasks;
import TasKer.Core.InvalidTask;
import TasKer.TasKer;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class SimpleExamenator implements Examinator {

    private static final Logger log = Logger.getLogger(SimpleExamenator.class.getName());

    private long startTime;
    private long endTime;
    private long timeOfPass;
    private Work work;
    private ArrayList<Task> examTasks;
    private int currentTaskIndex;
    private int solvedProblems;
    

    private String message;

    private boolean takeNext;

    private void start() throws Exception {
        try {
            currentTaskIndex = -1;
            next();
            message = "Exam has been started. ";
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public void prepareExam(Work work) throws Exception {
        this.solvedProblems=0;
        try {
            this.work = work;
            examTasks = getExamTasksGenerator().generate(work, work.getList(), work.getCount());
            start();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public void continueExam(Work work) throws Exception {
        this.solvedProblems = 0;
        try {
                this.work = work;
                Result result = new ResultEntety();
                ArrayList<Result> results = result.getResultsByWork(work.getId());
                this.solvedProblems += results.size();
                examTasks = getExamTasksGenerator().regenerate(work, work.getList(), work.getCount(), results);
                start();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public Work finishExam() throws Exception {
        try {
                work.setMark(calculateMatk());
                return work;

        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    private int calculateMatk() throws Exception {
        try {
                int mark = 0;
                Result result = new ResultEntety();
                Iterator<Result> results = result.getResultsByWork(work.getId()).iterator();
                while (results.hasNext()) {
                    int m = results.next().getMark();
                    if (m > 0) {
                        mark += m;
                    }
                }
                return mark;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public CheckedAnswer check(Answer answer) throws Exception {
        try {
                long time = System.currentTimeMillis();
                if (time - 1000 > endTime) {
                    message += "Time expired. ";
                    return new SimpleCheckedAnswer(true, answer) {
                    };
                }
                if (getChecker().valid(answer)) {
                    CheckedAnswer checkedAnswer = getChecker().check(answer);
                    saveResult(checkedAnswer);
                    if (checkedAnswer.isAccept()) {
                        message += "Answer has been accepted. ";
                        timeOfPass = System.currentTimeMillis();
                        takeNext = false;
                    } else {
                        message += "Wrong answer. ";
                    }
                    return checkedAnswer;
                } else {
                    throw new InvalidAnswer();
                }
            } catch (InvalidTask ex) {
                SimpleCheckedAnswer simpleCheckedAnswer = new SimpleCheckedAnswer(true, answer) {
                };
                saveResult(simpleCheckedAnswer);
                message += "Uncorrect task. ";
                timeOfPass = System.currentTimeMillis();
                takeNext = false;
                return simpleCheckedAnswer;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public boolean next() throws Exception {
        this.solvedProblems++;
        try {
                message += "Next task. ";
                currentTaskIndex++;
                if (examTasks.size() > currentTaskIndex) {
                    takeNext = true;
                    startTime = System.currentTimeMillis() + 1000;
                    endTime = startTime + examTasks.get(currentTaskIndex).getTime() * 60 * 1000;
                    saveNullResult();
                    return true;
                } else {
                    message += "Thet was last task. ";
                    return false;
                }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    private void saveNullResult() throws Exception {
        try {
                Result result = new ResultEntety();
                result.setTaskId(currentTask().getId());
                result.setMark(-1);
                result.setWorkId(work.getId());
                result.save();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    @Override
    public Task currentTask() throws Exception {
        try {
                if (leftTime() < 0) {
                    if (!next()) {
                        throw new EndOfExamTasks();
                    }
                }
                return examTasks.get(currentTaskIndex);
            
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public long leftTime() {
        return endTime - System.currentTimeMillis();
    }
    
    @Override
    public long endTime() {
        return endTime;
    }

    @Override
    public long spentTime() {
        return System.currentTimeMillis() - startTime;
    }

    @Override
    public long totalTime() {
        return timeOfPass - startTime;
    }

    private void saveResult(CheckedAnswer checkedAnswer) throws Exception {
        try {
                if (TasKer.attemptSaveMod()) {
                    Attempt attempt = checkedAnswer.getAttempt();
                    attempt.setWorkId(work.getId());
                    attempt.save();
                }
                Result result = checkedAnswer.getResult();
                result.setWorkId(work.getId());
                result.save();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public String popMessage() {
        String mes = this.message;
        message = "";
        return mes;
    }

    @Override
    public int getSolvedProblems() {
        return this.solvedProblems;
    }

    @Override
    public Work getWork() {
        return this.work;
    }

}
