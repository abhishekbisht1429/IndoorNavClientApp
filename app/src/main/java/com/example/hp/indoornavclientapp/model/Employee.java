package com.example.hp.indoornavclientapp.model;

public class Employee {
    private String name;
    private String employeeId;

    Employee() {

    }
    Employee(String name,String employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
