/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author ksinn
 */
public class Result extends Parant {

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("work_id", this.WorkId);
        list.put("result", this.Result);
        list.put("task_id", this.Task.getId());
        list.put("addDate", this.CreateTime);
        list.put("time", this.Time);

        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        this.WorkId = (int) list.get("work_id");
        this.TaskId = (int) list.get("task_id");
        this.Result = (int) list.get("result");
        this.CreateTime = (Date) list.get("addDate");
        this.Time = (int) list.get("time");
    }

    @Override
    protected String _getTableName() {
        return "task_result";
    }

    @Override
    protected boolean _isCorrect() {
        return true;
    }

    protected int WorkId;
    protected int TaskId;
    protected int Result;
    protected Date CreateTime;
    protected int Time;
    protected Work Work;
    protected Task Task;
    protected Exception Ex;
    protected ArrayList ResultArray;

    public Result() {
        this._from_db = false;
        this.CreateTime = new Date();

    }

    public Result(int id) throws Exception {
        this._from_db = false;
        this.getById(id);
    }

    public boolean Write() throws Exception {
        this.Result = -1;
        return this._insert();
    }

    public boolean Update() throws Exception {
        return this._update();
    }

    public boolean putAnswer(String answer) throws Exception {

        String sql = "((\n"
                + answer
                + ") except (\n"
                + this.Task.getAnswer()
                + ")) union ((\n"
                + this.Task.getAnswer()
                + ") except (\n"
                + answer
                + "))";

        StudentConnect conn_stud = new StudentConnect(this.Task.getList().getShema());
        ResultSet rs = null;
        if (!conn_stud.exequtQuery(sql)) {
            this.Ex = conn_stud.getException();
            return false;
        }
        if (conn_stud.getResultSet().next()) {
            this.Result = 0;
            conn_stud.exequtQuery(answer);
            this.ResultArray = conn_stud.getResultArray();
        } else {
            this.Result = 1;
        }
        conn_stud.close();
        return true;

        /*
        answer = answer.toLowerCase();
        if (answer.indexOf("alter") != -1) {
            return false;
        }

        StudentConnect conn_stud = new StudentConnect(this.Task.getList().getShema());
        StudentConnect conn_tut = new StudentConnect(this.Task.getList().getShema());
        ResultSet stud, tut;
        try {
            if (!conn_stud.exequtQuery(answer)) {
                this.Ex = conn_stud.getException();
                return false;
            } else {
                this.ResultArray = conn_stud.getResultArray();
                if (!conn_tut.exequtQuery(this.Task.getAnswer())) {
                    throw conn_stud.getException();
                }

                tut = conn_tut.getResultSet();
                stud = conn_stud.getResultSet();

                try {
                    this.setTotalTime();
                    this.Result = this.Compear(tut, stud) ? 1 : 0;
                } catch (Exception ex) {
                    this.Ex = ex;
                    return false;
                }

                return true;
            }
        } finally {
            conn_stud.close();
            conn_tut.close();
        }
         */
}

private boolean Compear(ResultSet r1, ResultSet r2) throws SQLException {

        r1.beforeFirst();
        r2.beforeFirst();

        if (r1.getMetaData().getColumnCount() != r2.getMetaData().getColumnCount()) {
            return false;
        }
        r1.last();
        r2.last();
        if (r1.getRow() != r2.getRow()) {
            return false;
        }

        r1.beforeFirst();
        r2.beforeFirst();

        for (int i = 1; i <= r1.getMetaData().getColumnCount(); i++) {
            if (!r1.getMetaData().getColumnName(i).equals(r2.getMetaData().getColumnName(i))) {
                return false;
            }
        }

        while (r1.next()) {
            r2.next();
            for (int i = 1; i <= r1.getMetaData().getColumnCount(); i++) {
                if (!r1.getString(i).equals(r2.getString(i))) {
                    return false;
                }
            }
        }

        if (r2.next()) {
            return false;
        }
        return true;
    }

    public void getById(int id) throws Exception {
        this._id = id;
        this._select();
        this.ReadTaskFromDB();
        this.ReadWorkFromDB();
    }

    public void ReadTaskFromDB() throws Exception {
        this.Task = new Task(this.TaskId);
    }

    public void ReadWorkFromDB() throws Exception {
        this.Work = new Work(this.WorkId);
    }

    public void setTask(Task task) {
        this.TaskId = task.getId();
        this.Task = task;
        this._from_db = false;
    }

    public void setResult(int data) {
        this.Result = data;
        this._from_db = false;
    }

    public void setTotalTime() {
        this.Time = (int) ((new Date()).getTime() - this.CreateTime.getTime());
        this._from_db = false;
    }

    public void setWork(Work data) {
        this.WorkId = data.getId();
        this.Work = data;
        this._from_db = false;
    }

    public int getResult() {
        return this.Result;
    }

    public Task getTask() {
        return this.Task;
    }

    public Work getWork() {
        return this.Work;
    }

    public Exception getException() {
        return this.Ex;
    }

    public ArrayList getResultArray() {
        return this.ResultArray;
    }

    public int getLeftTime() {
        return (int) (this.Task.getTime() * 1000 * 60
                - new Date().getTime()
                + this.CreateTime.getTime());
    }

}
