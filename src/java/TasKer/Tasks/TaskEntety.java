/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Tasks;

import TasKer.Tasks.Impl.ListEntety;
import TasKer.Core.DBEntety;
import TasKer.Tasks.List;
import TasKer.Tasks.Task;
import java.util.HashMap;

/**
 *
 * @author ksinn
 */
public abstract class TaskEntety extends DBEntety implements Task {

    private static final int DEFAULT_BALL = 1;
    private static final int DEFAULT_TIME = 5;

    protected List list;
    protected int listId;
    protected int time;
    protected int ball;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("list_id", this.listId);
        list.put("ball", this.ball);
        list.put("time", this.time);
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {

        this.listId = (int) list.get("list_id");
        this.ball = (int) list.get("ball");
        this.time = (int) list.get("time");
    }

    @Override
    protected String _getTableName() {
        return "task";
    }

    @Override
    protected boolean _isCorrect() {
        if (this.ball == 0) {
            setDefaultBall();
        }
        if (this.time == 0) {
            setDefaultTime();
        }

        if (this.listId == 0) {
            return false;
        }

        return true;
    }

    public TaskEntety() {

    }

    public void setDefaultBall() {
        this.ball = DEFAULT_BALL;
    }

    public void setDefaultTime() {
        this.time = DEFAULT_TIME;
    }

    @Override
    public boolean save() throws Exception {
        return this._insert();
    }

    @Override
    public boolean update() throws Exception {
        return this._update();
    }

    @Override
    public void getById(int id) throws Exception {
        this._id = id;
        this._select();
    }

    @Override
    public List getList() {
        return this.list;
    }

    @Override
    public int getListId() {
        return this.listId;
    }

    @Override
    public int getBall() {
        return ball;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setList(List list) {
        this.listId = list.getId();
        this.list = list;
    }

    @Override
    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void setBall(int ball) {
        this.ball = ball;
    }

}
