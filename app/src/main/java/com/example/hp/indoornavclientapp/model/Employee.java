package com.example.hp.indoornavclientapp.model;

public class Employee {
    private String name;
    private String employeeId;
    private String password;
    private String type;
    Employee() {

    }
    Employee(String name, String employeeId, String password, String type) {
        this.name = name;
        this.employeeId = employeeId;
        this.password = password;
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
