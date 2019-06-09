/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Core;

import java.sql.SQLException;

/**
 *
 * @author ksinn
 */
public class EndOfExamTasks extends SQLException{
    
    public EndOfExamTasks(){
    }
    
    @Override
    public String getMessage(){
        return "Time is out";
    }
    
}
