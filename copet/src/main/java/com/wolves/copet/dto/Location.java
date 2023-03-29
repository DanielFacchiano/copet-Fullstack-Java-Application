package com.wolves.copet.dto;

public class Location {
    private Integer id;
    private String state;
    private String locationName;
    private String address;
    private String contactInfo;
    private String locationDesc;


    public Location(Integer id, String state, String locationName, String address,
                    String contactInfo, String locationDesc) {
        this.id = id;
        this.state = state;
        this.locationName = locationName;
        this.address = address;
        this.contactInfo = contactInfo;
        this.locationDesc = locationDesc;
    }
    public Location(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getLocationDescription() {
        return locationDesc;
    }

    public void setLocationDescription(String locationDesc) {
        this.locationDesc = locationDesc;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", locationName='" + locationName + '\'' +
                ", address='" + address + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", locationDesc='" + locationDesc + '\'' +
                '}';
    }
}
