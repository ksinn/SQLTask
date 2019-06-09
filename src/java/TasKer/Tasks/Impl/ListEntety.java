/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Tasks.Impl;

import TasKer.Core.DBEntety;
import My.SQLTaskList;
import TasKer.Tasks.List;
import static TasKer.TasKer.getTaskFactory;
import TasKer.Tasks.Task;
import TasKer.Tasks.TaskFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author ksinn
 */
public class ListEntety extends DBEntety implements List{
    
    private int userId;
    private String name;
    private boolean publicList;
    private ArrayList<Task> tasks;
    
    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("user_id", this.userId);
        list.put("public", this.publicList?1:0);
        list.put("list_name", this.name);
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        this.userId = (int) list.get("user_id");
        this.name = (String) list.get("list_name");
        this.publicList = ((int) list.get("public"))==1;
        this.tasks = new ArrayList<Task>();
        this.ReadTasksFromBd();
    }

    @Override
    protected String _getTableName() {
        return "task_list";
    }

    @Override
    protected boolean _isCorrect() {
        
        if(this.userId==0) return false;
        if(this.name==null&&this.name.isEmpty()) return false;
        return true;
    }
    
    {
        this.tasks = new ArrayList<Task>();
        this.name = "";
    }
    
    public ListEntety(){
        
    }
    
    @Override
    public void getById(int id) throws Exception{
        this._id = id;
        this._select();
        
    }
    
    @Override
    public boolean save() throws Exception{
        return this._insert();
    }
    
    @Override
    public boolean update() throws Exception{
        return this._update();
    }
    
    protected  void ReadTasksFromBd() throws Exception{
        this.tasks.clear();
        TaskFactory taskFactory = getTaskFactory();         
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("list_id", this._id);
        DBEntety task = (DBEntety) taskFactory.create();
        ArrayList<HashMap<String, Object>> Params = task.getObjectsParam(param);
        for(int i=0; i<Params.size(); i++){
            task = (DBEntety) taskFactory.create();;
            task.getFromParam(Params.get(i));
            ((Task) task).setList(this);
            this.tasks.add((Task) task);
        }
    }
    
    @Override
    public ArrayList<List> Lists(HashMap<String, Object> params) throws Exception{
        ArrayList<List> lists = new ArrayList<List>();
        ArrayList<HashMap<String, Object>> _parameterSelect = this._parameterSelect(params);
        Iterator<HashMap<String, Object>> iter = _parameterSelect.iterator();
        while(iter.hasNext()){
            HashMap<String, Object> next = iter.next();
            ListEntety list = new ListEntety();
            list._setParams(next);
            lists.add(list);
        }
        return lists;
    }
    
    @Override
    public ArrayList getByUserId(int user_id) throws Exception {
        ArrayList<SQLTaskList> res = new ArrayList<SQLTaskList>();
        
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", user_id);
        SQLTaskList task = new SQLTaskList();
        ArrayList<HashMap<String, Object>> Params = task.getObjectsParam(param);
        for(int i=0; i<Params.size(); i++){
            task = new SQLTaskList();
            task.getFromParam(Params.get(i));
            res.add(task);
        }
        return res;
    }   
    
    @Override
    public ArrayList<Task> getTasks(){
        return this.tasks;
    }
    
    @Override
    public int getUserId(){
        return this.userId;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public boolean isPublic(){
        return this.publicList;
    }
    
    @Override
    public void setUserId(int value){
            this.userId = value;
    }
    
    @Override
    public void setName(String value){
        this._from_db = false;
        this.name = value;
    }
    
    @Override
    public void setPublish(boolean value){
        this.publicList = value;
    }

    @Override
    public void setTasks(ArrayList<Task> tasks) {
        if(tasks!=null){
            this.tasks = tasks;
        }
    }    
    
    
}
