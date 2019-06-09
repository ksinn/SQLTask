/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam;


/**
 *
 * @author ksinn
 */
public interface CheckedAnswer{
    
    public Result getResult();
    
    public Attempt getAttempt();
    
    public boolean isAccept();
    
    public Answer getAnswer();
    
}
