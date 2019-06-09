/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package My;

import TasKer.Exam.Answer;
import TasKer.Exam.AnswerFactory;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public class SQLAnswerFactory implements AnswerFactory{
    
    private static final Logger log = Logger.getLogger(SQLAnswerFactory.class.getName());

    @Override
    public Answer create(HttpServletRequest request) {
        SQLAnswer answer = new SQLAnswer(); 
        answer.setQuery(request.getParameter("answer")==null?"":request.getParameter("answer"));
        return answer;
    }

    @Override
    public Answer create() {
        return new SQLAnswer(); 
    }
    
}
