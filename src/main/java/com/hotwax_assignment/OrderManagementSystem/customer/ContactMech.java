package com.hotwax_assignment.OrderManagementSystem.customer;

import jakarta.persistence.*;

@Entity(name = "Contact_Mech")
public class ContactMech {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactMechId;
    private int customerId;
    @Column(length = 100, nullable = false)
    private String streetAddress;
    @Column(length = 50, nullable = false)
    private String city;
    @Column(length = 50, nullable = false)
    private String state;
    @Column(length = 20, nullable = false)
    private String postalCode;
    @Column(length = 20)
    private String phoneNumber;
    @Column(length = 100)
    private String email;

    public int getContactMechId() {
        return contactMechId;
    }

    public void setContactMechId(int contactMechId) {
        this.contactMechId = contactMechId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactMech{" +
                "contactMechId=" + contactMechId +
                ", customerId=" + customerId +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
