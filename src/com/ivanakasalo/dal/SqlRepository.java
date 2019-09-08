/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.dal;

import com.ivanakasalo.model.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Ivy
 */
public class SqlRepository {
    private static final String ADD_DRIVERS="insert into Driver values (?,?,?,?)";
    private static final String ADD_VEHICLES="{CALL AddVehicle (?,?,?,?,?)}";
    
    private static final String GET_DRIVERS="select * from Driver";
    private static final String GET_VEHICLES="{CALL GetVehicles}";
    
    public static void addDrivers(List<Driver> drivers, int chunk) {
       DataSource ds= DataSourceSingleton.getInstance();
        try (Connection con=ds.getConnection();
                PreparedStatement stmt=con.prepareStatement(ADD_DRIVERS)){
            
            int counter=0;
            for (Driver driver : drivers) {
                stmt.setString(1, driver.getName());
                stmt.setString(2,driver.getSurname());
                stmt.setString(3,driver.getMobile());
                stmt.setString(4,driver.getLicenceNo());
               
              stmt.addBatch();
              
                if (++counter==chunk) {
            stmt.executeBatch();
            counter=0;
                }
            }
            
           
            if (counter >0) {
                  stmt.executeBatch();
            }
          
            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void addVehicles(List<Vehicle> vehicles, int chunk) {
       DataSource ds= DataSourceSingleton.getInstance();
        try (Connection con=ds.getConnection();
                PreparedStatement stmt=con.prepareStatement(ADD_VEHICLES)){
            
            int counter=0;
            for (Vehicle vehicle : vehicles) {
                stmt.setString(1, vehicle.getType());
                stmt.setInt(2,vehicle.getBrand().getIdBrand());
                stmt.setInt(3,vehicle.getYearOfProduction());
                stmt.setDouble(4,vehicle.getInitialKilometers());
                stmt.setBoolean(5,vehicle.isIsAvailable());
               
              stmt.addBatch();
              
                if (++counter==chunk) {
            stmt.executeBatch();
            
            counter=0;
                }
            }
            
           
            if (counter >0) {
                  stmt.executeBatch();
            }
          
            con.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }
    
    public static ArrayList<Driver> getDrivers(){
            DataSource ds = DataSourceSingleton.getInstance();
            ArrayList<Driver> list = new ArrayList<>();
          
                    
        try (
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_DRIVERS    );
                ResultSet re = stmt.executeQuery();) {
            while (re.next()) {
                list.add(
                        new Driver(
                                re.getInt("IDDriver"),
                                re.getString("Name"),
                                re.getString("Surname"),
                                re.getString("Mobile"),
                                re.getString("LicenceNo")
                        ));
            con.commit();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

          return list;
    }
    
     public static ArrayList<Vehicle> getVehicles(){
            DataSource ds = DataSourceSingleton.getInstance();
            ArrayList<Vehicle> list = new ArrayList<>();
        try (
                
                Connection con = ds.getConnection();
                CallableStatement stmt = con.prepareCall(GET_VEHICLES);
                ResultSet re = stmt.executeQuery();) {
            while (re.next()) {
                list.add(
                        new Vehicle(
                                re.getInt("IDVehicle"),
                                re.getString("Type"),
                                new Brand(
                                re.getInt("IDBrand"),
                                re.getString("Name")
                                ),
                                re.getInt("YearOfProduction"),
                                re.getDouble("InitialKilometers"),
                                re.getBoolean("IsAvailable")
                        ));
                con.commit();
          
            }            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

             return list;
    }
}
