package com.example.restBasic.exceptions.details;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceValidationDetails extends ResourcesDetails {
    private Map<String,String> erros;

    public ResourceValidationDetails(){}

}
