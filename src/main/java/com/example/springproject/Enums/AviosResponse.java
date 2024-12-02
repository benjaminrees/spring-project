package com.example.springproject.Enums;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AviosResponse {
    int points;
    String cabinCode;

    public AviosResponse(int points, String cabinCode) {
        this.points = points;
        this.cabinCode = cabinCode;
    }
}
