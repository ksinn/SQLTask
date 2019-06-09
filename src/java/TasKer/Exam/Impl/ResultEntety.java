/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam.Impl;

import TasKer.Core.DBEntety;
import TasKer.Exam.Result;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class ResultEntety extends DBEntety implements Result {

    private static final Logger log = Logger.getLogger(ResultEntety.class.getName());

    protected int workId;
    protected int taskId;
    protected int mark;
    protected int time;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("work_id", this.workId);
        list.put("result", this.mark);
        list.put("task_id", this.taskId);
        list.put("time", this.time);
        list.put("addDate", new Timestamp(System.currentTimeMillis()));
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        try {
            this.workId = (int) list.get("work_id");
            this.taskId = (int) list.get("task_id");
            this.mark = (int) list.get("result");
            this.time = (int) list.get("time");
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    protected String _getTableName() {
        return "task_result";
    }

    @Override
    protected boolean _isCorrect() {
        return true;
    }

    public ResultEntety() {

    }

    @Override
    public boolean save() throws Exception {
        try {            
            return this._insertOrUpdate();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public ArrayList<Result> getResultsByWork(int id) throws Exception {
        try {
            ArrayList<Result> results = new ArrayList<Result>();

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("work_id", id);
            ResultEntety result = new ResultEntety();
            ArrayList<HashMap<String, Object>> Params = result.getObjectsParam(param);
            for (int i = 0; i < Params.size(); i++) {
                result = new ResultEntety();
                result._setParams(Params.get(i));
                results.add(result);
            }
            return results;
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public void setTaskId(int task_id) {
        this.taskId = task_id;
    }

    @Override
    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public void setWorkId(int work_id) {
        this.workId = work_id;
    }

    @Override
    public int getMark() {
        return this.mark;
    }

    @Override
    public int getTaskId() {
        return this.taskId;
    }

    @Override
    public int getWorkId() {
        return this.workId;
    }

}
