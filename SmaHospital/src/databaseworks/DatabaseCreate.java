
package databaseworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import manipulater.Commonalit;


public class DatabaseCreate extends Commonalit{
    private Connection connect=null;
    private Statement st;
    private final String url="jdbc:mysql://localhost:3306/SMA";
    private final String driver="com.mysql.jdbc.Driver";
    private final String userName="root";
    private final String password="";
//    private final String database="lugoba";    
        
    public  Connection connectToDB(){
        try{
            Class.forName(driver).newInstance();
            connect=DriverManager.getConnection(url, userName, password);
            st=connect.createStatement();
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException m){
            dialog.informationAlert("Database not started: start your server");
        }
        return connect;
    }
          
    public int createDatabase(){
        connectToDB();
        int value=0;
//        try {           
//            sql="CREATE DATABASE IF NOT EXISTS "+database;
//            st.executeUpdate(sql);
//            sql="USE "+database;
//            st.executeUpdate(sql);
//        } catch (SQLException ex) {
//            System.out.println("Failed "+ex);
//        }
        return value;
    }
    
    public int createTables(){
        connectToDB();
        int value=0;
        try{            
            sql="CREATE TABLE IF NOT EXISTS student(studentObjectId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "className VARCHAR(20) DEFAULT NULL, studentObject BLOB)";
            value=st.executeUpdate(sql);
            sql="CREATE TABLE IF NOT EXISTS subject(subjectObjectId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
                    + "className VARCHAR(20) DEFAULT NULL, subjectObject BLOB)";
            value=st.executeUpdate(sql);
        }catch(SQLException ex){
            System.out.println("Failed2 "+ex);
        }    
        return value;
    }
    
    
    public int querryDb(String sql){
        connectToDB();
        int value=0;
        try {
           value= st.executeUpdate(sql);
        } catch (SQLException ex) {
        }
        return value;
    }
    
    public ResultSet getDb(String sql){
        connectToDB();
        ResultSet value=null;
        try {   
            value=st.executeQuery(sql);
        } catch (SQLException ex) {
        }
        return value;
    }
    
    
}
