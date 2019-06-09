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
public interface Checker {
    
    public CheckedAnswer check(Answer answer) throws Exception;
    
    public boolean valid(Answer answer);
    
}
