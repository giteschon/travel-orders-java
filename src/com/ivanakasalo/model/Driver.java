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
public class Driver {
    private int idDriver;
    private String name;
    private String surname;
    private String mobile;
    private String licenceNo;

    public Driver(int idDriver, String name, String surname, String mobile, String licenceNo) {
        this.idDriver = idDriver;
        this.name = name;
        this.surname = surname;
        this.mobile = mobile;
        this.licenceNo = licenceNo;
    }

    public Driver() {
    }
    
    

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    @Override
    public String toString() {
        return idDriver + " " + name + " " + surname + " " + mobile + " " + licenceNo;
    }
    
    
    
    
}
