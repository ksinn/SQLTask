/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ksinn
 */
public class db_page extends DBConnect{

    @Override
    protected String getDataSourseName() {
        return "DB";
    }
    public String SQL;
    
    public db_page(){
    }
    
    public ArrayList Execute(String sql) throws Exception{
        SQL = sql;
        Connection conn = null;
        try{
            conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs;
            boolean execute = stmt.execute(SQL);
            if(execute){
                rs = stmt.getResultSet();
                return this.getResultArray(rs);
            } else {
                ArrayList<String> ar = new ArrayList<String>();
                ar.add(execute?"true":"false");
                return ar;
            }
        } finally{
            if(conn!=null)
                conn.close();
        }
    }
    
    
    public ArrayList getResultArray(ResultSet rs) throws SQLException{
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        
        ArrayList<String> buf = new ArrayList<String>();
            for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
                buf.add(rs.getMetaData().getColumnName(i));
            }
            list.add(buf);
            while(rs.next()){
                buf = new ArrayList<String>();
                for(int i=1; i<=rs.getMetaData().getColumnCount(); i++){
                    buf.add(rs.getString(i));
                }
                list.add(buf);
            }
        
        
        return list;
    }
    
}
