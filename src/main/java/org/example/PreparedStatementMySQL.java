package org.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;



public class PreparedStatementMySQL {
    public static void updateData(Connection connection)throws Exception{
        String sqlUpdate = "UPDATE customers "
                +"SET customerName = ? "
                +"WHERE customerNumber = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate);
        String customerNameUpdate = "Name update1";
        int customerNumberUpdate = 103;
        preparedStatement.setString(1,customerNameUpdate);
        preparedStatement.setInt(2,customerNumberUpdate);
        //send UPDATE statememt to MySQL by executeUpdate() method
        int rowAffected = preparedStatement.executeUpdate();
        System.out.println(rowAffected);
        //Result : 103	Name update1	Schmitt	Carine 	40.32.2555	54, rue Royale
        preparedStatement.close();//close PreparedStatement

    }
    public static void insertData(Connection connection)throws Exception{
        String sqlInsert = "INSERT INTO information (name,City) "
                +"VALUES (?,?)";
        String sqlInsertId = "INSERT INTO information (name,id,City)"
                +"VALUES (?,DEFAULT,?)";
        PreparedStatement prstInsert = connection.prepareStatement(sqlInsert);
        prstInsert.setString(1,"Nam2");
        prstInsert.setString(2,"HN2");
//        prstInsert = connection.prepareStatement(sqlInsertId);
//        prstInsert.setString(1,"Nam23");
//        prstInsert.setString(2,"HN2");
        prstInsert.executeUpdate();
        prstInsert.close();
    }
    public static void deleteData(Connection connection)throws Exception{
        String sqlDelete = "DELETE FROM information "
                +"WHERE name = ?";
        String name = "Nam";
        PreparedStatement prstDelete = connection.prepareStatement(sqlDelete);
//        prstDelete.setInt(1,4);
        prstDelete.setString(1,name);
        prstDelete.executeUpdate();
        prstDelete.close();
    }
    public static void main(String[] args) {
        //add parameters into your SQL statement using placeholders in the form of question marks (?). This helps you avoid SQL injection
        //execute the SQL statement multiple times with different parameters
        //increase the performance of the executed statement by precompiling the SQL statement
        try {
            Connection connection = Main.connectMySQL();
            //executeUpdate for UPDATE,
            //Update data
//            updateData(connection);
//            insertData(connection);
            deleteData(connection);
            connection.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
