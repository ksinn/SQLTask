/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;


import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author ksinn
 */
public class TaskList extends Parant{
    
    private int User;
    private boolean Public;
    private String Name;
    private String Schema;
    private ArrayList<Task> Tasks;
    private Service Service;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("user_id", this.User);
        list.put("public", this.Public?1:0);
        list.put("list_name", this.Name);
        list.put("sql_schema", this.Schema);
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        this.User = (int) list.get("user_id");
        this.Public = (int) list.get("public") == 1;
        this.Name = (String) list.get("list_name");
        this.Schema = (String) list.get("sql_schema");
        this.Tasks = new ArrayList<Task>();
    }

    @Override
    protected String _getTableName() {
        return "task_list";
    }

    @Override
    protected boolean _isCorrect() {
        return true;
    }
    
    public TaskList(){
        this._from_db = false;
    }
    
    public TaskList(int id) throws Exception{
        this._from_db = false;
        this.getById(id);
    }
    
    public void getById(int id) throws Exception{
        this._id = id;
        this._select();
        this.ReadTasksFromBd();
        this.ReadServiceFromBd();
        
    }
    
    public void ReadTasksFromBd() throws Exception{
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("list_id", this._id);
        Task task = new Task();
        ArrayList<HashMap<String, Object>> Params = task.getObjectsParam(param);
        for(int i=0; i<Params.size(); i++){
            task = new Task();
            task.getFromParam(Params.get(i));
            this.Tasks.add(task);
        }
    }
    
    public ArrayList<HashMap<String, Object>> Groups(HashMap<String, Object> params) throws Exception{
        return this._parameterSelect(params);
    }
    
    public boolean Write(int user_id) throws Exception{
        this.User = user_id;
        return this._insert();
    }
    
    public boolean Update(int user_id) throws Exception{
        if(this.User != user_id) return false;
        if(this._from_db) return true;
        return this._update();
    }
    
    public boolean Delete(int user_id) throws Exception{
        if(this.User != user_id) return false;
        return this._delete();
    }
    
    public ArrayList<Task> getTasks(){
        return this.Tasks;
    }
    
    public int getUser(){
        return this.User;
    }
    
    public boolean isPublish(){
        return this.Public;
    }
    
    public String getName(){
        return this.Name;
    }
    
    public void setUser(int value){
        if(!this._from_db)
            this.User = value;
    }
    
    public void setPublish(boolean value){
        this._from_db = false;
        this.Public = value;
    }
    
    public void setName(String value){
        this._from_db = false;
        this.Name = value;
    }

    static public ArrayList getByUserId(int user_id) throws Exception {
        ArrayList<TaskList> res = new ArrayList<TaskList>();
        
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("user_id", user_id);
        TaskList task = new TaskList();
        ArrayList<HashMap<String, Object>> Params = task.getObjectsParam(param);
        for(int i=0; i<Params.size(); i++){
            task = new TaskList();
            task.getFromParam(Params.get(i));
            res.add(task);
        }
        return res;
    }

    private void ReadServiceFromBd() throws Exception {
        Service = new Service();
        Service.getById(1);
    }

    public Service getService() {
        return Service;
    }
    
    public String getShema(){
        return this.Schema;
    }
    
    public void getShema(String schema){
        this.Schema = schema;
    }
    
    
}
