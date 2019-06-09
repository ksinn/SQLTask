/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.util.HashMap;

/**
 *
 * @author ksinn
 */
public class Task extends Parant{

    protected TaskList List;
    protected String Answer;
    protected String Question;
    protected int Time;
    protected int Ball;
    protected int ListId;
    protected String Img;
    
    
    @Override
    protected HashMap<String, Object> _getParams() {
        HashMap<String, Object> list = new HashMap<String, Object>();
        list.put("ball", this.Ball);
        list.put("time", this.Time);
        list.put("question", this.Question);
        list.put("answer", this.Answer);
        list.put("list_id", this.ListId);
        list.put("img", this.Img);
        return list;
    }
    
    @Override
    protected void _setParams(HashMap<String, Object> list)  throws Exception {
        
        this.Ball = (int) list.get("ball");
        this.Time = (int) list.get("time");
        this.Question = (String) list.get("question");
        this.Answer = (String) list.get("answer");
        this.ListId = (int) list.get("list_id");
        this.Img = (String) list.get("img");
    }
    
    @Override
    protected String _getTableName() {
        return "task";
    }
    
    @Override
    protected boolean _isCorrect() {
        if(this.Ball == 0) this.Ball = 1;
        if(this.Time == 0) this.Time = 5;
        return  (!this.Question.isEmpty()) 
                && (!this.Answer.isEmpty()) 
                && (this.List._from_db);
    }
    
    public Task(){
        this._from_db = false;
    }
    
    public Task(int id) throws Exception{
        this._from_db = false;
        this.getById(id);
    }
    
    public boolean Write(int user_id) throws Exception{
        if(this.List.getUser() != user_id) return false;
        return this._insert();
    }
    
    public boolean Update(int user_id) throws Exception{
        if(this.List.getUser() != user_id) return false;
        if(this._from_db) return true;
        return this._update();
    }
    
    public boolean Delete(int user_id) throws Exception{
        if(this.List.getUser() != user_id) return false;
        return this._delete();
    }
    
    public void getById(int id) throws Exception{
        this._id = id;
        this._select();
        this.ReadGroupFromDB();
    }
    
    public void ReadGroupFromDB() throws Exception{
        this.List = new TaskList(this.ListId);
    }
    
    public void setList(int group) throws Exception{
        this._from_db = false;
        this.ListId = group;
        this.List = new TaskList(group);
        if(!this.List._from_db) throw new Exception();
    }
    
    public void setAnswer(String answer){
        this._from_db = false;
        this.Answer = answer.toLowerCase();
    }
    
    public void setImg(String img){
        this._from_db = false;
        this.Img = img;
    }
    
    public void setQuestion(String question){
        this._from_db = false;
        this.Question = question;
    }
    
    public void setTime(int time){
        this._from_db = false;
        this.Time = time;
    }
    
    public void setBall(int ball){
        this._from_db = false;
        this.Ball = ball;
    }
    
    public TaskList getList(){
        return this.List;
    }
    
    public int getBall(){
        return Ball;
    }
    
    public int getTime(){
        return Time;
    }

    public String getAnswer() {
        return this.Answer;
    }
    
    public String getImg() {
        return this.Img;
    }
    
    public String getQuestion() {
        return this.Question;
    }

}
