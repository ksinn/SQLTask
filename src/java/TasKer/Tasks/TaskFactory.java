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
public interface TaskFactory {
    
    public Task create(HttpServletRequest request) throws ErrorParameterException;
    public Task create(HttpServletRequest request, Task task) throws ErrorParameterException;
    public Task createById(int id) throws Exception;
    public Task create(int list) throws Exception;
    public Task create();
    
    
}
