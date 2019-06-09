/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Core;

/**
 *
 * @author ksinn
 */
public class ErrorParameterException extends Exception {
    
    private String ParameterName;
    private String WantedValue;
    private String HasValue;
    
    public ErrorParameterException(String ...par){
        super(par[0]);
        /*switch(par.length){
            case 1:{
                ParameterName = par[1];
            }
            case 2:{
                WantedValue = par[1];
            }
            case 3:{
                HasValue = par[2];
            }
        }*/
        
        
        
        
        
    
    }
    
}
