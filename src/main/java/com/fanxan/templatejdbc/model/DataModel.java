package com.fanxan.templatejdbc.model;

public class DataModel {
    private int id;
    private String name;
    private String email;
    private String city;
    private int age;
    private String job;

    public DataModel(int id, String name, String email, String city, int age, String job) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
        this.job = job;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public DataModel(int id){
        this.id = id;
    }

    public DataModel(String name, String email, String city, int age, String job) {
//        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.age = age;
        this.job = job;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", age='" + age + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
