package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchProcessing {

    public  static void addGroupOfQueries(Connection connection)throws SQLException {
        //information table
//            CREATE TABLE information(
//                    name VARCHAR(255) NOT NULL,
//                    id INT AUTO_INCREMENT,
//                    City VARCHAR(255),
//                    PRIMARY KEY(id)
//            );

        Statement statement = connection.createStatement();
        statement.addBatch("INSERT INTO information VALUES ('Nguyen van A',DEFAULT,'DaNang')");
        statement.addBatch("INSERT INTO information VALUES ('Nguyen van B',DEFAULT,'TPHCM')");
        statement.addBatch("INSERT INTO information VALUES ('Nguyen van C',DEFAULT,'Hai Phong')");
        statement.addBatch("INSERT INTO information VALUES ('Nguyen van C1',DEFAULT,'Hai Phong2')");
        statement.addBatch("INSERT INTO information VALUES ('Nguyen van C2',DEFAULT,'Hai Phong3')");
        statement.addBatch("DELETE FROM  information WHERE id = 2 ");
        statement.addBatch("UPDATE information SET name = 'nameUpdate' WHERE id = 4 ");
        statement.executeBatch();
        connection.commit();
//        Nguyen van A	1	DaNang
//        Nguyen van C	3	Hai Phong
//        nameUpdate	4	Hai Phong2
//        Nguyen van C2	5	Hai Phong3
    }
    public static void exampleBathProcessingUsingPreparedStatment(Connection connection) throws SQLException{
        String sqlInsertInformation = "INSERT INTO information VALUES (?,DEFAULT,?)";
        String[] names = new String[]{"Nguyen Van A","Tran Van B","Nguyen Xuan C","Tran Xaun D","Vu Van E"};
        String[] cities = new String[]{"Ha Noi","Da Nang","Hai Phong","Cao Bang","Thai Binh"};
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertInformation);
        for(int i =0 ;i< names.length;i++){
            preparedStatement.setString(1,names[i]);
            preparedStatement.setString(2,cities[i]);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
        connection.close();
    }


    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = Main.connectMySQL();
            connection.setAutoCommit(false);
//            addGroupOfQueries(connection);
            exampleBathProcessingUsingPreparedStatment(connection);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}
