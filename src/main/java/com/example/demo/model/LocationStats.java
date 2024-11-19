package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationStats {

    private Integer totalRes;
    private Integer totalVb;
//    private Integer totalRegistered;
//    private Integer totalNotRegistered;
    private String percentage;


}
