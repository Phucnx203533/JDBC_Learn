package org.example;

import java.sql.*;



public class Main {
    public static Connection connectMySQL() throws Exception{

        String url = "jdbc:mysql://127.0.0.1:3306/classicmodels";
        String username = "root";
        String password = "Phuc@23052002";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }

    public static void main(String[] args){
        try {
            Connection connection = Main.connectMySQL();
            Statement st = connection.createStatement();
            String sql = "SELECT * FROM customers";
            // executeQuery for SELECT queries
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("customerNumber") + "\t" +
                        rs.getString("customerName")  + "\t" +
                        rs.getString("phone"));
            }

            rs.close();
            st.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}