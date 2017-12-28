package th.co.wacoal.springtemplates.db;



import java.sql.*;
import java.util.*;

public  class DatabaseSqlserver {
    private Connection connect;
    
    public DatabaseSqlserver() {
        try {
            Class.forName(DatabaseSqlserverInfo.DRIVER);

            connect = DriverManager.getConnection(
                        DatabaseSqlserverInfo.URL,
                        DatabaseSqlserverInfo.USER, 
                        DatabaseSqlserverInfo.PASSWORD);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    public Map<String, Object> querySingle(String sql, Object... args) {
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }
            
            ResultSet         rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            
            if (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    map.put(md.getColumnLabel(i), rs.getObject(i));
                }
                rs.close();
                pstmt.close();
                return map;
            }
            else {
                rs.close();
                pstmt.close();
                return null;
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Map<String, Object>> queryList(String sql, Object... args) {
        try {
            List<Map<String, Object>> list 
                    = new ArrayList<Map<String, Object>>();
            
            PreparedStatement pstmt = connect.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }

            ResultSet         rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    map.put(md.getColumnLabel(i), rs.getObject(i));
                }
                
                list.add(map);
            }

            rs.close();
            pstmt.close();
            return list;



        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    public int add(String sql, Object... args) {
//        try {
//            PreparedStatement pstmt = connect.prepareStatement(
//                                        sql, Statement.RETURN_GENERATED_KEYS);
//
//            for (int i = 0; i < args.length; i++) {
//                pstmt.setObject(i+1, args[i]);
//            }
//
//            pstmt.executeUpdate();
//
//            //  Find Auto Increment Id
//            ResultSet rs = pstmt.getGeneratedKeys();
//
//            rs.next();
//
//            return rs.getInt(1);
//        }
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }

            pstmt.executeUpdate();

            return 1;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int update(String sql, Object... args) {
        try {
            PreparedStatement pstmt = connect.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i+1, args[i]);
            }

            return pstmt.executeUpdate();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int remove(String sql, int id) {
        return update(sql, id);
    }
    
    public void beginTransaction() {
        try {
            connect.setAutoCommit(false);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void commit() {
        try {
            connect.commit();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void rollback() {
        try {
            connect.rollback();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void close() {
        try {
            if (connect != null) {
                connect.close();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}