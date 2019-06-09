/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My;

import TasKer.Core.UserSQLException;
import TasKer.Exam.Impl.SimpleCheckedAnswer;
import TasKer.Exam.Answer;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;


/**
 *
 * @author ksinn
 */
public class SQLCheckedAnswer extends SimpleCheckedAnswer{
    
    private static final Logger log = Logger.getLogger(SQLCheckedAnswer.class.getName());

    private UserSQLException exception;
    private ArrayList resultArray;

    public SQLCheckedAnswer(boolean accept, Answer answer) {
        super(accept, answer);
    }
    
    public boolean hasException(){
        return exception!=null;
    }
    
    public boolean hasResultArray(){
        return resultArray!=null;
    }
    
    public SQLException getException(){
        return this.exception;
    }
    
    public ArrayList getResultArray(){
        return this.resultArray;
    }
        
    
    public void setException(UserSQLException ex){
        this.exception = ex;
    }
    
    public void setResultArray(ArrayList resultArray){
        this.resultArray = resultArray;
    } 
   
}
