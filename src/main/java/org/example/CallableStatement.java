package org.example;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Types;

public class CallableStatement {
    public static void main(String[] args) {
        try{
            Connection connection = Main.connectMySQL();
//            CREATE PROCEDURE GetCustomerLevel(
//                    IN  customerNo INT,
//                    OUT customerLevel VARCHAR(20)
//            )
//            BEGIN
//            DECLARE credit DEC(10,2) DEFAULT 0;
//            SELECT
//            creditLimit
//            INTO credit
//            FROM customers
//            WHERE
//                    customerNumber = customerNo;
//            SET customerLevel = CustomerLevel(credit);
//            END$
//customerNumber                    creditLimit
// 103	            Name update1	21000.0
//            DELIMITER $$
//            CREATE FUNCTION CustomerLevel(
//                    credit DECIMAL(10,2)
//            )
//            RETURNS VARCHAR(20)
//            DETERMINISTIC
//                    BEGIN
//            DECLARE customerLevel VARCHAR(20)
//            IF credit > 50000 THEN
//            SET customerLevel = 'PLATINUM';
//            ELSEIF (credit >= 50000 AND
//                    credit <= 10000) THEN
//            SET customerLevel = 'GOLD';
//            ELSEIF credit < 10000 THEN
//            SET customerLevel = 'SILVER';
//            END IF;
//            RETURN (customerLevel);
//            END$$
//            DELIMITER ;
            String sqlCallProcedure ="{call GetCustomerLevel(?,?)}";
            java.sql.CallableStatement callableStatement = connection.prepareCall(sqlCallProcedure);
            callableStatement.setInt(1,103);
            callableStatement.registerOutParameter(2, Types.VARCHAR );
            callableStatement.execute();
            System.out.println(callableStatement.getString(2));//GOLD
            String sqlCallFuction = "{? = call CustomerLevel(?) }";
            java.sql.CallableStatement callableFuctions = connection.prepareCall(sqlCallFuction);
            callableFuctions.setInt(2,150);
            callableFuctions.registerOutParameter(1, Types.VARCHAR);
            callableFuctions.execute();
            System.out.println(callableFuctions.getString(1));
            callableStatement.close();
            callableFuctions.close();
            connection.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
