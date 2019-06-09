/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ksinn
 */
public class InitParams {
    static private InitParams Singl = new InitParams();
    static public String LogPath;
    static public String RealPath;
    
    private InitParams(){
        
        /*try {
            //InitialContext initialContext = new InitialContext();
            //this.LogPath = (String) initialContext.lookup("java:/comp/env/path/log");
            //this.RealPath =  (String) initialContext.lookup("java:/comp/env/path/real");
        } catch (NamingException ex) {
            
        }*/
    }
    
}
