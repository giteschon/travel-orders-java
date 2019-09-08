/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivanakasalo.model;

/**
 *
 * @author Ivy
 */
public class Vehicle {
    private int idVehicle;
    private String type;
    private Brand brand;
    private int yearOfProduction;
    private double initialKilometers;
    private boolean isAvailable;

    public Vehicle(int idVehicle, String type, Brand brand, int yearOfProduction, double initialKilometers, boolean isAvailable) {
        this.idVehicle = idVehicle;
        this.type = type;
        this.brand = brand;
        this.yearOfProduction = yearOfProduction;
        this.initialKilometers = initialKilometers;
        this.isAvailable = isAvailable;
    }

    public Vehicle() {
    }
    
    

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getInitialKilometers() {
        return initialKilometers;
    }

    public void setInitialKilometers(double initialKilometers) {
        this.initialKilometers = initialKilometers;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    
}
