package com.example.rahul.firebase;

public class consumerProfile {

    public  String consumerName;
    public  String consumerEmail;
    public String consumerAge;
    public String consumerType;
    public String consumerAddress;
    public  String consumerMobile;
    consumerProfile()
    {

    }

    consumerProfile(String consumerName,String consumerEmail,String consumerAge,String consumerType,String consumerAddress,String consumerMobile)
    {

        this.consumerName=consumerName;
        this.consumerEmail=consumerEmail;
        this.consumerAge=consumerAge;
        this.consumerType=consumerType;
        this.consumerAddress=consumerAddress;
        this.consumerMobile=consumerMobile;


    }

    public String getConsumerMobile() {
        return consumerMobile;
    }

    public void setConsumerMobile(String consumerMobile) {
        this.consumerMobile = consumerMobile;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerEmail() {
        return consumerEmail;
    }

    public void setConsumerEmail(String consumerEmail) {
        this.consumerEmail = consumerEmail;
    }

    public String getConsumerAge() {
        return consumerAge;
    }

    public void setConsumerAge(String consumerAge) {
        this.consumerAge = consumerAge;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress;
    }
}
