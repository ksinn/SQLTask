/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TasKer.Core;

import TasKer.TasKer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author ksinn
 */
public abstract class DBEntety implements TasKer {

    private static final Logger log = Logger.getLogger(DBEntety.class.getName());

    protected int _id;
    protected boolean _from_db;

    abstract protected HashMap<String, Object> _getParams();

    abstract protected void _setParams(HashMap<String, Object> Params) throws Exception;

    abstract protected String _getTableName();

    abstract protected boolean _isCorrect();

    protected Connection getConnection() throws NamingException, SQLException {
        try {
            InitialContext initContext;
            initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/entetyDB");
            return ds.getConnection();
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    public int getId() {
        return this._id;
    }

    public ArrayList<HashMap<String, Object>> getObjectsParam(HashMap<String, Object> params) throws Exception {
        try {
            return this._parameterSelect(params);
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    public void getFromParam(HashMap<String, Object> param) throws Exception {
        try {
            this._id = (int) param.get("id");
            this._setParams(param);
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    protected boolean _select() throws Exception {
        try {
            String query_string = this.generateQueryString(null, "select");

            Connection conn = this.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(query_string);
                stmt.setInt(1, this._id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    HashMap<String, Object> Params = new HashMap<String, Object>();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        Params.put(rs.getMetaData().getColumnName(i), rs.getObject(rs.getMetaData().getColumnName(i)));
                    }
                    conn.close();
                    this._setParams(Params);
                    this._from_db = true;
                    return true;
                } else {
                    conn.close();
                    return false;
                }
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    protected boolean _insert() throws NamingException, SQLException {
        try {
            if (!this._isCorrect()) {
                return false;
            }

            Set<Map.Entry<String, Object>> params = this._getParams().entrySet();
            String query_string = this.generateQueryString(params, "insert");

            Connection conn = this.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(query_string, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                for (Map.Entry<String, Object> param : params) {
                    /*if(param.getValue() == null){
                        i++;
                        continue;
                    }*/
                    if (param.getValue() instanceof Integer) {
                        stmt.setInt(i, (int) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof String) {
                        stmt.setString(i, (String) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof Date) {
                        stmt.setTimestamp(i, new java.sql.Timestamp(((Date) param.getValue()).getTime()));
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof Long) {
                        stmt.setLong(i, ((Long) param.getValue()));
                        i++;
                        continue;
                    }
                }

                int result = stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                int auto_increment_id;
                if (result == 1) {
                    if (rs.next()) {
                        this._id = rs.getInt(1);
                    }
                    this._from_db = true;
                }
                conn.close();
                return result == 1;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    protected boolean _insertOrUpdate() throws NamingException, SQLException {
        try {
            if (!this._isCorrect()) {
                return false;
            }

            Set<Map.Entry<String, Object>> params = this._getParams().entrySet();
            String query_string = this.generateQueryString(params, "insertOrUpdate");

            Connection conn = this.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(query_string, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                for (Map.Entry<String, Object> param : params) {
                    /*if(param.getValue() == null){
                        i++;
                        continue;
                    }*/
                    if (param.getValue() instanceof Integer) {
                        stmt.setInt(i, (int) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof String) {
                        stmt.setString(i, (String) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof Date) {
                        stmt.setTimestamp(i, new java.sql.Timestamp(((Date) param.getValue()).getTime()));
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof Long) {
                        stmt.setLong(i, ((Long) param.getValue()));
                        i++;
                        continue;
                    }
                }

                int result = stmt.executeUpdate();
                if (result == 1) {
                    this._from_db = true;
                }
                conn.close();
                return result == 1;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }

    }

    protected boolean _update() throws NamingException, SQLException {
        try {
            if (this._id == 0) {
                return false;
            }
            if (!this._isCorrect()) {
                return false;
            }
            Set<Map.Entry<String, Object>> params = this._getParams().entrySet();
            String query_string = this.generateQueryString(params, "update");

            Connection conn = this.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(query_string);

                int i = 1;
                for (Map.Entry<String, Object> param : params) {
                    if (param.getValue() instanceof Integer) {
                        stmt.setInt(i, (int) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof String) {
                        stmt.setString(i, (String) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof Date) {
                        stmt.setTimestamp(i, new java.sql.Timestamp(((Date) param.getValue()).getTime()));
                        i++;
                        continue;
                    }
                }
                stmt.setInt(i, this._id);

                int result = stmt.executeUpdate();
                conn.close();
                if (result == 1) {
                    this._from_db = true;
                }
                return result == 1;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }

        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    protected boolean _delete() throws NamingException, SQLException {
        try {
            if (this._id == 0) {
                return false;
            }
            String query_string = this.generateQueryString(null, "delete");

            Connection conn = this.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(query_string);
                stmt.setInt(1, this._id);
                int result = stmt.executeUpdate();
                conn.close();
                return result == 1;
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }

        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    protected ArrayList<HashMap<String, Object>> _parameterSelect(HashMap<String, Object> Params) throws Exception {
        try {
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

            Set<Map.Entry<String, Object>> params = Params.entrySet();
            String query_string = this.generateQueryString(params, "select");

            Connection conn = this.getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement(query_string);
                int i = 1;
                for (Map.Entry<String, Object> param : params) {
                    if (param.getValue() instanceof Integer) {
                        stmt.setInt(i, (int) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof String) {
                        stmt.setString(i, (String) param.getValue());
                        i++;
                        continue;
                    }
                    if (param.getValue() instanceof Date) {
                        stmt.setDate(i, (java.sql.Date) (Date) param.getValue());
                        i++;
                        continue;
                    }
                }
                ResultSet rs = stmt.executeQuery();
                HashMap<String, Object> res_params;
                while (rs.next()) {
                    res_params = new HashMap<String, Object>();
                    for (i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        res_params.put(rs.getMetaData().getColumnName(i), rs.getObject(rs.getMetaData().getColumnName(i)));
                    }
                    list.add(res_params);
                }
                conn.close();
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }

            return list;

        } catch (Exception ex) {
            log.error(null, ex);
            throw ex;
        }
    }

    private String generateQueryString(Set<Map.Entry<String, Object>> params, String mod) {
        switch (mod) {
            case "insert": {
                return generateInsertQuery(params);
            }
            case "insertOrUpdate": {
                return generateInsertOrUpdateQuery(params);
            }
            case "update": {
                return generateUpdateQuery(params);
            }
            case "delete": {
                return generateDeleteQuery(params);
            }
            case "select": {
                return generateSelectQuery(params);
            }
            default: {
                return null;
            }
        }
    }

    private String generateInsertQuery(Set<Map.Entry<String, Object>> params) {
        String query = " INSERT INTO " + this._getTableName() + "(";
        for (Map.Entry<String, Object> param : params) {
            query += " " + param.getKey() + ",";
        }
        query = query.substring(0, query.length() - 1);
        query += " ) VALUES (";
        for (Map.Entry<String, Object> param : params) {
            query += param.getValue() == null ? " DEFAULT," : " ?,";
        }
        query = query.substring(0, query.length() - 1);
        query += " );";

        return query;
    }

    private String generateInsertOrUpdateQuery(Set<Map.Entry<String, Object>> params) {
        String query = " INSERT INTO " + this._getTableName() + "(";
        for (Map.Entry<String, Object> param : params) {
            query += " " + param.getKey() + ",";
        }
        query = query.substring(0, query.length() - 1);
        query += " ) VALUES (";
        for (Map.Entry<String, Object> param : params) {
            query += param.getValue() == null ? " DEFAULT," : " ?,";
        }
        query = query.substring(0, query.length() - 1);
        query += " ) ON CONFLICT ON CONSTRAINT pk_"+this._getTableName()+" DO UPDATE SET ";
        for (Map.Entry<String, Object> param : params) {
            query += " " + param.getKey() + " = EXCLUDED." + param.getKey() + ",";
        }
        query = query.substring(0, query.length() - 1);
        query += ";";
        return query;
    }

    private String generateUpdateQuery(Set<Map.Entry<String, Object>> params) {
        String query = " UPDATE " + this._getTableName() + " SET ";
        for (Map.Entry<String, Object> param : params) {
            query += " " + param.getKey() + " = " + (param.getValue() == null ? "DEFAULT," : "?,");
        }
        query = query.substring(0, query.length() - 1);
        query += " WHERE id = ?";
        query += ";";

        return query;
    }

    private String generateDeleteQuery(Set<Map.Entry<String, Object>> params) {
        String query = " UPDATE " + this._getTableName();
        query += " SET deleted = 1 ";
        if (params == null) {
            query += " WHERE id = ?";
        } else {
            query += " WHERE ";
            for (Map.Entry<String, Object> param : params) {
                query += " " + param.getKey() + " = ?,";
            }
            query = query.substring(0, query.length() - 1);
        }
        query += ";";

        return query;
    }

    private String generateSelectQuery(Set<Map.Entry<String, Object>> params) {
        String query = " SELECT * FROM " + this._getTableName();
        if (params == null) {
            query += " WHERE id = ?";
        } else {
            query += " WHERE ";
            for (Map.Entry<String, Object> param : params) {
                query += " " + param.getKey() + " = ? and";
            }
            query = query.substring(0, query.length() - 3);
        }
        query += " order by id;";

        return query;
    }

}
