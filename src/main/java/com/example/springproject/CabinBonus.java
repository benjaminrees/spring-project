package com.example.springproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CabinBonus {
    String cabinName;
    String cabinCode;
    float bonus;
}
