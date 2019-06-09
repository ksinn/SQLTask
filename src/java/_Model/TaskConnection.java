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
import javax.naming.NamingException;

/**
 *
 * @author ksinn
 */
public class TaskConnection extends DBConnect {

    private static final int ROW_LIMIT = 100;
    private Connection conn;
    private SQLException ex;
    private String schema;

    @Override
    protected String getDataSourseName() {
        return "postgres";
    }

    public TaskConnection(String schema) {
        this.schema = schema;
    }

    public ArrayList exequtQuery(String query) throws NamingException, UserSQLException, SQLException {

        try {
            this.conn = this.getConnection();
            Statement stmt = conn.createStatement();
            stmt.setMaxRows(this.ROW_LIMIT);
            stmt.execute("SET search_path TO " + this.schema);
            try {
                ResultSet rs = stmt.executeQuery(query);
                return getResultArray(rs);
            } catch (SQLException ex) {
                throw new UserSQLException(ex);
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

    }

    public ArrayList getResultArray(ResultSet rs) throws SQLException {
        ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();

        ArrayList<Object> buf = new ArrayList<Object>();
        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
            buf.add(rs.getMetaData().getColumnName(i));
        }
        list.add(buf);

        while (rs.next()) {
            buf = new ArrayList<Object>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                buf.add(rs.getObject(i));
            }
            list.add(buf);
        }

        return list;
    }

}
