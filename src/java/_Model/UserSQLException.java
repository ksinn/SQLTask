/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.sql.SQLException;

/**
 *
 * @author ksinn
 */
public class UserSQLException extends SQLException{
    
    public UserSQLException(SQLException ex){
        super(ex);
    }
    
}
