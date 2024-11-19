package com.example.demo.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Municipality {
    @Id
    private int citymunCode;
    private String citymunDesc;
    private String provDesc;
    private int provCode;
}
