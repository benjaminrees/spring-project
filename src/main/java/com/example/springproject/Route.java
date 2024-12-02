package com.example.springproject;

import com.example.springproject.Enums.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Route {
    Airport[] airports;
    int pointsEarned;

    public Route(Airport firstAirport, Airport secondAirport, int pointsEarned){

        airports = new Airport[2];
        this.airports[0] = firstAirport;
        this.airports[1] = secondAirport;
        this.pointsEarned = pointsEarned;
    }

}
