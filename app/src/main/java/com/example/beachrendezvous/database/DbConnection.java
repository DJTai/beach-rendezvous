package com.example.beachrendezvous.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public Connection getConnection()
    {

        String TAG = "Db Connection";
        String Db = "jdbc:mysql://ec2-18-222-73-252.us-east-2.compute.amazonaws.com:3306/test";
        String username = "chaitu";
        String password = "chaitu";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
                      e.printStackTrace();
        }


        Connection connection = null;
        try {

            connection = DriverManager.getConnection(Db, username, password);

                   } catch (SQLException e) {
            Log.i(TAG, e.getMessage());
            Log.i(TAG, Integer.toString(e.getErrorCode()));

        }

return  connection;

    }

}
