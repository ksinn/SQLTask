/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Work.Impl;

import TasKer.Core.DBEntety;
import static TasKer.TasKer.getListFactory;
import TasKer.Exam.Impl.ResultEntety;
import TasKer.Tasks.List;
import TasKer.Exam.Result;
import TasKer.Work.Work;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 * @author ksinn
 */
public class WorkEntety extends DBEntety implements Work {

    protected UUID WORK_KEY;
    protected int userId;
    protected int listId;
    protected List list;
    protected int count;
    private int mark;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();        
        list.put("work_key", this.WORK_KEY.toString());
        list.put("user_id", this.userId);
        list.put("list_id", this.listId);
        list.put("count", this.count);
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        this.WORK_KEY = UUID.fromString((String) list.get("work_key"));
        this.userId = (int) list.get("user_id");
        this.listId = (int) list.get("list_id");
        this.count = (int) list.get("count");
        this.list = getListFactory().createById(listId);
    }

    @Override
    protected String _getTableName() {
        return "works";
    }

    @Override
    protected boolean _isCorrect() {
        return userId != 0
                && listId != 0
                && count != 0;
    }

    public WorkEntety() {

    }

    @Override
    public void getById(int id) throws Exception {
        this._id = id;
        this._select();

    }    

    @Override
    public boolean getByKey(String key) throws Exception {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("work_key", key);
        ArrayList<HashMap<String, Object>> Params = this.getObjectsParam(param);
        if (Params.isEmpty()) {
            return false;
        } else {
            this.getFromParam(Params.get(0));
            return true;
        }
    }
    
    @Override
    public boolean save() throws Exception {
        return this._insert();
    }

    @Override
    public void setList(List list) {
        this.listId = list.getId();
        this.list = list;
    }

    @Override
    public void setUserId(int user) {
        this.userId = user;
    }

    @Override
    public void setCount(int data) {
        this.count = data;
    }

    @Override
    public int getListId() {
        return this.list.getId();
    }

    @Override
    public List getList() {
        return this.list;
    }

    @Override
    public UUID getKey() {
        return this.WORK_KEY;
    }

    @Override
    public int getUser() {
        return this.userId;
    }

    @Override
    public int getCount() {
        return this.count;
    }

    @Override
    public String getKeyAsString() {
        return this.WORK_KEY.toString();
    }

    @Override
    public void setKeyFromString(String key) {
        this.WORK_KEY = UUID.fromString(key);
    }

    @Override
    public int getMark() {
        return this.mark;
    }

    @Override
    public void setMark(int mark) {
        this.mark = mark;
    }
    
    public int getCountWrong() throws Exception{
        
        Result result = new ResultEntety();
        ArrayList<Result> results = result.getResultsByWork(_id);
        int count = 0;
        for(int i=0; i<results.size(); i++)
            if(results.get(i).getMark()<=0)
                count++;
        return count;
    }
    
    public int getCountAccepted() throws Exception{
        
        Result result = new ResultEntety();
        ArrayList<Result> results = result.getResultsByWork(_id);
        int count = 0;
        for(int i=0; i<results.size(); i++)
            if(results.get(i).getMark()>0)
                count++;
        return count;
    }

}
