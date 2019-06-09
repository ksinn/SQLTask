/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author ksinn
 */
public class Work extends Parant{
    
    protected UUID WORK_KEY;
    protected int UserId;
    protected int ListId;
    protected TaskList Group;
    protected int Count;
    protected ArrayList<Result> Accepts;

    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("user_id", this.UserId);
        list.put("list_id", this.ListId);
        list.put("count", this.Count);
        list.put("WORK_KEY", this.WORK_KEY.toString());
        return list;
    }

    @Override
    protected void _setParams(HashMap<String, Object> list) throws Exception {
        this.UserId = (int) list.get("user_id");
        this.ListId = (int) list.get("list_id");
        this.Count = (int) list.get("count");
        this.WORK_KEY = UUID.fromString((String) list.get("WORK_KEY"));
        this.Accepts = new ArrayList<Result>();
    }

    @Override
    protected String _getTableName() {
        return "works";
    }

    @Override
    protected boolean _isCorrect() {
        return UserId != 0
                && ListId != 0
                && Count != 0
                && Group._from_db;                
    }
    
    
    
    public Work(){
        this._from_db = false;
    }
    
    public Work(int id) throws Exception{
        this._from_db = false;
        this.getById(id);
    }
    
    public boolean Write() throws Exception{
        return this._insert();
    }  
    
    public boolean isExistKey() throws Exception{
        HashMap<String, Object> param = new HashMap<String, Object>();
                param.put("WORK_KEY", this.WORK_KEY.toString());
                ArrayList<HashMap<String, Object>> Params = this.getObjectsParam(param);
                if(Params.isEmpty()) {
                    return false;
                } else {
                    this.getFromParam(Params.get(0));
                    this.ReadAcceptsFromDB();
                    this.ReadTaskList();
                    return true;
                }
    }
    
    public Result Next() throws Exception{
        if(this.Accepts.size() == this.Count) 
            return null;
        /*if(!this.Accepts.isEmpty()){
            if(this.Accepts.get(this.Accepts.size()-1).getLeftTime()>0
                    &&this.Accepts.get(this.Accepts.size()-1).Result==-1){
                return this.Accepts.get(this.Accepts.size()-1);
            }
        }*/
        ArrayList<Task> list = this._generatTaskList();
        if(this.Accepts.size() == list.size()) return null;
        int ch;
        for(int j=0; j<list.size(); j++){
            ch=0;
            for(int i=0; i<this.Accepts.size(); i++){
                if(this.Accepts.get(i).getTask().getId()==list.get(j).getId()) {
                    ch++;
                    break;
                }
            }
            if(ch==0){
                Result accept = new Result();
                accept.setTask(list.get(j));
                accept.setWork(this);
                accept.Write();
                return accept;
            }
        }
        return null;
    } 
    
    public void getById(int id) throws Exception{
        this._id = id;
        this._select();
        this.ReadTaskList();
        this.ReadAcceptsFromDB();
        
    }
    
    public void ReadTaskList() throws Exception{
        this.Group = new TaskList(this.ListId);
    }
    
    public void ReadAcceptsFromDB() throws Exception{
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("work_id", this._id);
        Result accept = new Result();
        ArrayList<HashMap<String, Object>> Params = accept.getObjectsParam(param);
        for(int i=0; i<Params.size(); i++){
            accept = new Result();
            accept.getFromParam(Params.get(i));
            accept.ReadTaskFromDB();
            this.Accepts.add(accept);
        }
    }
    
    public void setList(int group) throws Exception{
        this._from_db = false;
        this.ListId = group;
        this.Group = new TaskList(group);
        if(!this.Group._from_db) throw new Exception();
    }
    
    public void setUser(int data){
        this._from_db = false;
        this.UserId = data;
    }
    
    public void setUser(int data, String serviceURL){
        this._from_db = false;
        this.UserId = data;
    }
    
    public void setCount(int data){
        this._from_db = false;
        this.Count = data;
    }
    
    public int getListId(){
        return this.Group.getId();
    }
    
    public TaskList getGroup() throws Exception{
        return this.Group;
    }
    
    
    public UUID getKey(){
        return this.WORK_KEY;
    }
    
    public int getUser(){
        return this.UserId;
    }
    
    public int getCount(){
        return this.Count;
    }
    
    private ArrayList<Task> _generatTaskList(){
        ArrayList<Task> list = new ArrayList<Task>();
        
        Random gen = new Random(this._id);
        int[] id_list = new int[this.Count];
        
        for(int i=0; i<this.Count; i++){
            int N=-1, c;
            do{
                c=0;
                N = gen.nextInt(this.Group.getTasks().size());
                for(int n=0; n<i; n++){
                    if(id_list[n]==N){
                        c++;
                        break;
                    }
                }
            } while (c!=0);
            id_list[i]=N;
            list.add(this.Group.getTasks().get(N));
        }
        return list;
    }
    
    private int _calculateLiveTime(ArrayList<Task> list){
        int time=0;
        for(int i=0; i<list.size(); i++){
            time+=list.get(i).getTime()*60+60;
        }
        return time;
    }
    public String getWorkKey() {
        return this.WORK_KEY.toString();
    }
    public void setWorkKey(String data) {
        this.WORK_KEY = UUID.fromString(data);
    }

    public int getMark() {
        int max=0, res=0;
        for(int i=0; i<this.Accepts.size(); i++){
            if(Accepts.get(i).Result>0)
                res+=Accepts.get(i).Result;
        }
        return res;
    }
    
}
