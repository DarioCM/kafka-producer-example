package org.example.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonSerialize
public class Customer {

    private int id;
    private String name;
    private String email;
    private String contactNumber;


}
