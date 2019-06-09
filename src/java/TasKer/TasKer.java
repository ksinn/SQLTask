/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer;

import TasKer.Exam.AnswerFactory;
import TasKer.Tasks.ListFactory;
import TasKer.Tasks.TaskFactory;
import TasKer.Exam.Checker;
import TasKer.Exam.ExamTasksGenerator;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ksinn
 */
public interface TasKer {

    static String getInitParam(String name) throws NamingException {
        InitialContext initContext;
        initContext = new InitialContext();
        String param = (String) initContext.lookup("java:comp/env/cntxt/"+name);
        return param;
    }
    
    static Boolean getInitModValue(String name) throws NamingException {
        InitialContext initContext;
        initContext = new InitialContext();
        return (Boolean) initContext.lookup("java:comp/env/cntxt/"+name);
    }
    
    static boolean attemptSaveMod() throws NamingException{
        return getInitModValue("attemptSaveMod");
    }

    static TaskFactory getTaskFactory() throws ClassNotFoundException, NamingException, InstantiationException, IllegalAccessException {
        
        Class forName = Class.forName(getInitParam("taskFactory"));
        return (TaskFactory) forName.newInstance();
        
    }
    
    static ListFactory getListFactory() throws ClassNotFoundException, NamingException, InstantiationException, IllegalAccessException {
        
        Class forName = Class.forName(getInitParam("listFactory"));
        return (ListFactory) forName.newInstance();
        
    }
   
    static AnswerFactory getAnswerFactory() throws ClassNotFoundException, NamingException, InstantiationException, IllegalAccessException {
        
        Class forName = Class.forName(getInitParam("answerFactory"));
        return (AnswerFactory) forName.newInstance();
        
    }
    
    static Checker getChecker() throws ClassNotFoundException, NamingException, InstantiationException, IllegalAccessException {
        
        Class forName = Class.forName(getInitParam("checker"));
        return (Checker) forName.newInstance();
        
    }
    
    static ExamTasksGenerator getExamTasksGenerator() throws ClassNotFoundException, NamingException, InstantiationException, IllegalAccessException {
        
        Class forName = Class.forName(getInitParam("examTasksGenerator"));
        return (ExamTasksGenerator) forName.newInstance();
        
    }
    
    static Connection getConnection(String name) throws NamingException, SQLException{        
        InitialContext initContext;
        initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/"+ name);
        return ds.getConnection();
    }

}
