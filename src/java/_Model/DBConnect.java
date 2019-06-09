/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ksinn
 */
abstract public class DBConnect {
    
    abstract protected String getDataSourseName();
    
    protected Connection getConnection() throws NamingException, SQLException{
        
        InitialContext initContext;
        initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/"+ this.getDataSourseName());
        return ds.getConnection();
    }
    
}
