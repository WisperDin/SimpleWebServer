package model;

import com.mysql.jdbc.SQLError;
import customException.ModelException;
import db.DataBaseConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserModel {

    public UserModel(int id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private int id;
    private String account;
    private String password;

    private static final String tableName   = "USER";

    //
    public boolean Insert()throws ModelException{
        Connection conn = DataBaseConn.GetConn();
        if (conn==null){
            return false;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(String.format("insert ignore into %s(account,pwd) values(?,?)",tableName));
            if (stmt == null){
                throw new ModelException("createStatement get null");
            }
            if (account.equals("")||password.equals("")){
                throw new ModelException("param empty");
            }
            stmt.setString(1,account);
            stmt.setString(2,password);
            int effectedRow = stmt.executeUpdate();
            if (effectedRow == 0){
                return false;
            }
            if (effectedRow!=1){
                throw new ModelException("effectedRow not 1");
            }
        }catch (SQLException se){
            throw new ModelException("sql exception "+se.getMessage());
        }
        return true;
    }

    public static UserModel[] FindUser(String condition,String order,String limit) throws ModelException{
        Connection conn = DataBaseConn.GetConn();
        try {
            Statement stmt = conn.createStatement();
            if (stmt == null){
                throw new ModelException("createStatement get null");
            }
            ResultSet rs = stmt.executeQuery(String.format("select id,account,pwd from %s %s %s %s",tableName,condition,order,limit));
            List<UserModel> userList = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("id");
                String account = rs.getString("account");
                String pwd = rs.getString("pwd");
                if (id==0||account==null||pwd==null){
                    continue;
                    //todo...log
                    //throw new ModelException("id or account or pwd extract error ");
                }
                if (account.equals("")||pwd.equals("")){
                    continue;
                    //todo...log
                    //throw new ModelException("account or pwd empty");
                }
                UserModel tmp = new UserModel(id,account,pwd);
                if (!userList.add(tmp)){
                    //todo...log
                }
            }
            if (userList.size()==0){
                //todo...log
            }
            UserModel[] tmpArray = new  UserModel[userList.size()];
            return userList.toArray(tmpArray);
        }catch (SQLException se){
            throw new ModelException("sql exception "+se.getMessage());
        }
    }

    public boolean Upd(){
        return true;
    }

    /**
     *
     * @param condition 条件
     * @return 受影响的条数
     * @throws ModelException
     */
    public static int DeleteUser(String condition) throws ModelException{
        Connection conn = DataBaseConn.GetConn();
        try {
            Statement stmt = conn.createStatement();
            if (stmt == null){
                throw new ModelException("createStatement get null");
            }
            int rs = stmt.executeUpdate(String.format("delete from %s %s",tableName,condition));
            return rs;
        }catch (SQLException se){
            throw new ModelException("sql exception "+se.getMessage());
        }
    }
}
