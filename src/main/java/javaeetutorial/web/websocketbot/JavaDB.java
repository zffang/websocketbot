/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.web.websocketbot;

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;


public class JavaDB {

    public static void main(String[] args) {
        JavaDB dB = new JavaDB();
        List<Term> list = ToAnalysis.parse("北京好热");

        System.out.println(dB.findCity(list));
        System.out.println(dB.isCity("北京"));
    }

     public boolean isCity(String aString) {
        boolean flag = false;
        String cityname = "";
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            System.out.println("Load the network driver");
            Connection conn = null;
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "123");
            //create and connect the database named helloDBNet 
            System.out.println("start to create and connect to qaDB");

            try {
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/qaDB;create=false", props);
                System.out.println("create and connect to qaDB");
                conn.setAutoCommit(false);
                Statement statement = conn.createStatement();
                ResultSet rSet = null;
                String sqlString = "select CITYNAME from USER1.CITY where CITYNAME=\'" + aString + "\'";
                rSet = statement.executeQuery(sqlString);
                if (rSet.next()) {
                    cityname = aString;
                    System.out.println(cityname);
                    flag = true;
                }

                rSet.close();
                statement.close();
                conn.commit();
                conn.close();
                //  DriverManager.getConnection("jdbc:derby:<localhost:1527/qaDB>;shutdown=true");
                 props.put("shutdown", "true");
                DriverManager.getConnection("jdbc:derby://localhost:1527/<qaDB>",props);

            } catch (SQLException se) {
                //se.printStackTrace();
               // System.out.println("Database shut down normally");
            }
        } catch (Throwable e) {
            // handle the exception
            e.printStackTrace();
        }
        return flag;
    }


    public String findCity(List<Term> list) {
        String cityname = "天津";
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            System.out.println("Load the network driver");
            Connection conn = null;
            Properties props = new Properties();
            props.put("user", "user1");
            props.put("password", "123");
            //create and connect the database named helloDBNet 
            System.out.println("start to create and connect to qaDB");

            try {
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/qaDB;create=false", props);
                System.out.println("create and connect to qaDB");
                conn.setAutoCommit(false);
                Statement statement = conn.createStatement();
                ResultSet rSet = null;
                for (int i = 0; i < list.size(); i++) {
                    String sqlString = "select CITYNAME from USER1.CITY where CITYNAME=\'" + list.get(i).getName() + "\'";
                    rSet = statement.executeQuery(sqlString);
                    System.out.println(rSet.toString());
                    if (rSet != null) {
                        cityname = list.get(i).getName();
                    }
                }

                rSet.close();
                statement.close();
                conn.commit();
                conn.close();
                DriverManager.getConnection("jdbc:derby//localhost:1527/qaDB;shutdown=true");
            } catch (SQLException se) {
                System.out.println("Database shut down normally");
            }
        } catch (Throwable e) {
            // handle the exception
        }
        return cityname;
    }
}
