/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;

/**
 *
 * @author Ivy
 */
public class DataSourceSingleton {
    private static final String PASSWORD = "SQL";
    private static final String USERNAME = "sa";
    private static final String DATABASE_NAME = "TravelOrders";
    private static final String SERVER = "localhost";
   
    private DataSourceSingleton() {
        
    }
   
    private static DataSource instance;

    public static DataSource getInstance() {
        if(instance == null){
        instance=createInstance();
        }
        
        return instance;
    }
    
    
     private static DataSource createInstance() {
        SQLServerDataSource ds= new SQLServerDataSource();
        ds.setServerName(SERVER);
        ds.setDatabaseName(DATABASE_NAME);
        ds.setUser(USERNAME);
        ds.setPassword(PASSWORD);
        
        return ds;
    }
}
