/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Exam;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ksinn
 */
public interface AnswerFactory {
    
    public Answer create(HttpServletRequest request);
    public Answer create();
}
