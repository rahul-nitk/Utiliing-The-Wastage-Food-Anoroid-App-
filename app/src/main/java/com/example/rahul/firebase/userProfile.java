package com.example.rahul.firebase;

public class userProfile {

    public  String name;
    public  String email;
    public String age;
    public String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public userProfile()
{

}
    public userProfile(String name, String email, String age,String type) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.type=type;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
