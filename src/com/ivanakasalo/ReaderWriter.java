/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo;

import com.ivanakasalo.model.Brand;
import com.ivanakasalo.model.Driver;
import com.ivanakasalo.model.Vehicle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Ivy
 */
public class ReaderWriter {

    private static final String DELIMITER = ";";
  

    private static final String FIRST_ROW = "IdDriver" + DELIMITER + "Name" + DELIMITER + "Surname" + DELIMITER + "Mobile" + DELIMITER + "LicenceNo"
            + DELIMITER + "IdVehicle" + DELIMITER + "Type" + DELIMITER + "IdBrand" + DELIMITER + "BrandName" + DELIMITER
            + "YearOfProduction" + DELIMITER + "InitialKilometers" + DELIMITER + "IsAvailable";

    public static ArrayList<Driver> readDriver(String path) throws FileNotFoundException, IOException {
        ArrayList<Driver> list= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String data ="";
            //first row
            reader.readLine();

            while ((data =reader.readLine()) != null) {
                String[] line = data.split(DELIMITER);
                Driver d= new Driver();
                d.setIdDriver(Integer.parseInt(line[0]));
                d.setName(line[1]);
                d.setSurname(line[2]);
                d.setMobile(line[3]);
                d.setLicenceNo(line[4]);
                
                list.add(d);

            }
            
            return  list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
return  null;
    }
    
     public static ArrayList<Vehicle> readVehicles(String path) throws FileNotFoundException, IOException {
        ArrayList<Vehicle> list= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String data ="";
            //first row
            reader.readLine();

            while ((data =reader.readLine()) != null) {
                String[] line = data.split(DELIMITER);
                
               Vehicle v= new Vehicle();
                System.out.println(line[5]);
                v.setIdVehicle(Integer.parseInt(line[5]));
                v.setType(line[6]);
                Brand b= new Brand();
                b.setIdBrand(Integer.parseInt(line[7]));
                b.setName(line[8]);
                v.setBrand(b);
                
               v.setYearOfProduction(Integer.parseInt(line[9]));
               v.setInitialKilometers(Double.parseDouble(line[10]));
               boolean available; 
               if ("Yes".equals(line[11])) {
                    available=true;
                }
               else{
               available=false;
               }
               v.setIsAvailable(available);               
                             
                list.add(v);

            }
            
            return  list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
return  null;
    }

    public static void generateData(String path, int rows) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            writer.append(FIRST_ROW);
            writer.append("\n");

            for (int i = 0; i < rows; i++) {

                //driver
                writer.append(String.valueOf(i+1));
                writer.append(DELIMITER);
                writer.append(genString(6));
                writer.append(DELIMITER);
                writer.append(genString(6));
                writer.append(DELIMITER);
                writer.append(genNumber(10));
                writer.append(DELIMITER);
                writer.append(genNumber(8));
                writer.append(DELIMITER);

                //vehicle
                writer.append(String.valueOf(i+1));
                writer.append(DELIMITER);
                writer.append(genString(4));
                writer.append(DELIMITER);
                //brand
                writer.append(genBrand());
                //vehicle
                writer.append(genYear());
                writer.append(DELIMITER);
                writer.append(genNumber(4));
                writer.append(DELIMITER);
                writer.append(genBool());
                writer.append("\n");
                
                System.out.println(String.valueOf(i));
                        
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String genString(int n) {
       StringBuilder sb= new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char)ThreadLocalRandom.current().nextInt(97,122));
        }
        
        return sb.toString();
    }

     private static String genYear() {
         StringBuilder sb = new StringBuilder();
        Random rnd= new Random();
        int min=1990;
        int max=2018;
            sb.append(String.valueOf(rnd.nextInt((max - min) + 1) + min));
        

        return sb.toString();
    }
     
     private static String genBool() {
         StringBuilder sb = new StringBuilder();
        Random rnd= new Random();
        int index= rnd.nextInt(2);
         if (index ==1) {
             sb.append("Yes");
         }
         else{
         sb.append("No");

         } 
        

        return sb.toString();
    }
    
    
    private static String genNumber(int n) {
        StringBuilder sb = new StringBuilder();
        Random rnd= new Random();
        for (int i = 0; i < n; i++) {
            sb.append(String.valueOf(rnd.nextInt(9)));
        }

        return sb.toString();
    }

    private static String genBrand() {
        ArrayList<Brand> list = getBrands();
        StringBuilder sb = new StringBuilder();

        int index = ThreadLocalRandom.current().nextInt(0, 7);
        if (list.get(index) != null) {
            sb.append(String.valueOf(list.get(index).getIdBrand()));
            sb.append(DELIMITER);
            sb.append(list.get(index).getName());
            sb.append(DELIMITER);

        }

        return sb.toString();
    }

    private static ArrayList<Brand> getBrands() {
        ArrayList<Brand> list = new ArrayList<>();
        list.add(new Brand(1, "BMW"));
        list.add(new Brand(2, "Mercedes-Benz"));
        list.add(new Brand(3, "Volkswagen"));
        list.add(new Brand(4, "Volvo"));
        list.add(new Brand(5, "Audi"));
        list.add(new Brand(6, "Nissan"));
        list.add(new Brand(7, "Toyota"));

        return list;

    }
}
