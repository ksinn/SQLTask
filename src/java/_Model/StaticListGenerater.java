/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ksinn
 */
public class StaticListGenerater /*implements ListGenerater*/{
    
    /*private ArrayList<Task> list;
    
    @Override
    public void Create(Work work) {
        
        this.list = new ArrayList<Task>();
        Random gen = new Random(work._id);
        if(work.Count == 0){ 
            this.list =  work.Group.getTasks();
        } else {
            for(int i=0; i<work.Count; i++){
                this.list.add(work.Group.getTasks().get(gen.nextInt(work.Group.getTasks().size())));
            
            Task b;
            int j, k;
            for(i=0; i<this.list.size(); i++){
                j = gen.nextInt(this.list.size());
                k = gen.nextInt(this.list.size());
                b=this.list.get(k);
                this.list.set(k, this.list.get(j));
                this.list.set(j, b);
            }
            }
    }

    @Override
    public Task Next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void GenerateList(){
        
        
    }

    @Override
    public Task Next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
}
