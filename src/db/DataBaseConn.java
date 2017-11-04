package db;

import config.Config;
import customException.DBInitException;
import customException.EnvInitException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConn {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = String.format("jdbc:mysql://%s:%s/%s", Config.DB_HOST,Config.DB_PORT,Config.DB_NAME);

    //数据库连接
    static Connection conn = null;

    public static boolean InitDb() throws DBInitException{
        if (DataBaseConn.GetConn()==null){
            throw new DBInitException("DataBaseConn GetConn null");
        }
        System.out.println("Database Connect Success");
        if (!DataBaseConn.InitTable()){
            throw new DBInitException("InitTable failed");
        }
        System.out.println("Database InitTable Success");
        return true;
    }

    public static Connection GetConn(){
        if (conn != null)
            return conn;
        Statement stmt = null;
        try {
            //reg jdbc driver
            Class.forName(JDBC_DRIVER);
            //make conn
            conn = DriverManager.getConnection(DB_URL,Config.DB_USER,Config.DB_PWD);
            return conn;
        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void ReleaseConn(){
        try {
            if (conn!=null)
                conn.close();
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    private static boolean InitTable(){
        Statement stmt = null;
        Connection conn = GetConn();
        if (conn == null){
            return false;
        }
        try {
            stmt = conn.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS USER (id INTEGER auto_increment NOT NULL,account VARCHAR(100) UNIQUE ,pwd VARCHAR(100) NOT NULL,PRIMARY KEY(id));");
            //execute
            stmt.execute(sb.toString());
        }catch (SQLException se){
            se.printStackTrace();
        }
        return true;
    }

}
