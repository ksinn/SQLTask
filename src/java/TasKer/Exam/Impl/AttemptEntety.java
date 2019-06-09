/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam.Impl;

import TasKer.Core.DBEntety;
import TasKer.Exam.Attempt;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class AttemptEntety extends DBEntety implements Attempt {

    private static final Logger log = Logger.getLogger(AttemptEntety.class.getName());

    public static int ACCEPT = 1;

    protected int workId;
    protected int taskId;
    protected int flag;
    protected String answer;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("work_id", this.workId);
        list.put("task_id", this.taskId);
        list.put("answer", this.answer);
        list.put("flag", this.flag);
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        try {
            this.workId = (int) list.get("work_id");
            this.taskId = (int) list.get("task_id");
            this.answer = (String) list.get("answer");
            this.flag = (int) list.get("flag");
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    protected String _getTableName() {
        return "attempt";
    }

    @Override
    protected boolean _isCorrect() {
        return true;
    }

    @Override
    public boolean save() throws Exception {
        try {
            return this._insert();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    @Override
    public void getById(int id) throws Exception {
        try {
            this._id = id;
            this._select();
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
    public void setWorkId(int work_id) {
        this.workId = work_id;
    }

    @Override
    public void setFlag(int accept) {
        this.flag = accept;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int getWorkId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAnswer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getTaskId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
