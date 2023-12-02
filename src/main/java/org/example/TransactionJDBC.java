package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionJDBC {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            //information table
//            CREATE TABLE information(
//                    name VARCHAR(255) NOT NULL,
//                    id INT AUTO_INCREMENT,
//                    City VARCHAR(255),
//                    PRIMARY KEY(id)
//            );
            connection = Main.connectMySQL();
            connection.setAutoCommit(false);
            PreparedStatement prst = connection.prepareStatement("INSERT INTO information VALUES (?,DEFAULT,?)");
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            while(true){

                System.out.println("enter name");
                String name=br.readLine();

                System.out.println("enter city");
                String city=br.readLine();
                prst.setString(1,name);
                prst.setString(2,city);
                prst.executeUpdate();
                System.out.println("commit/rollback");
                String answer=br.readLine();
                if(answer.equals("commit")){
                    connection.commit();
                } else if (answer.equals("rollback")){
                    connection.rollback();
                }else{
                    break;
                }
                System.out.println("Want to add more records y/n");
                String ans=br.readLine();
                if(ans.equals("n")){
                    break;
                }

            }
            connection.commit();
            System.out.println("add record successfully");
            prst.close();
            connection.close();
        }catch (Exception e){
            try {
                connection.rollback();

            }catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
            System.out.println(e.getMessage());
        }
    }
}
