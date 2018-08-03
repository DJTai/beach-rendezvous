package com.example.beachrendezvous.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class addDetailsDB {
    private static final String TAG = "add db details";
    private SportsEntity e;
public  void start(SportsEntity e)
{
DbConnection db=new DbConnection();
    Connection con=db.getConnection();
    if(con!=null) {
        String query1 = "select * from events";
        Log.i(TAG, "query1" + query1);

        PreparedStatement st1 = null;
        try {
            // st1 = con.prepareStatement(query1);
            //ResultSet rs2 = st1.executeQuery(query1);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Log.i(TAG, "count" + rs.last());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
}
