package com.example.restBasic.exceptions.details;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResourcesDetails {

    protected String title;
    protected int status;
    protected String detail;
    protected LocalDateTime timeStamp;
    protected String developerMessage;

    public ResourcesDetails(){}

}
