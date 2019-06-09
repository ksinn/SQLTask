/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Tasks;

import TasKer.Core.ErrorParameterException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ksinn
 */
public interface ListFactory {
    
    public List create(HttpServletRequest request) throws ErrorParameterException;
    public List create(HttpServletRequest request, List list) throws ErrorParameterException;
    public List createById(int id) throws Exception;
    public List create();
    
    
}
