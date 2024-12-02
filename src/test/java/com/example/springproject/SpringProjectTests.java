package com.example.springproject;

import com.example.springproject.Enums.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SpringProjectTests {
    ArrayList<Route> routes;
    ArrayList<CabinBonus> bonuses;
    PointsService pointsService;


    @BeforeEach
    void setup(){
        pointsService = new PointsService();
    }

    @Test
    public void testReturnAvios(){
        assertEquals(pointsService.returnAvios(Airport.LHR, Airport.LAX), 4500);
        assertEquals(pointsService.returnAvios(Airport.LHR, Airport.SFO), 4400);
        assertEquals(pointsService.returnAvios(Airport.LHR, Airport.JFK), 3200);
        assertEquals(pointsService.returnAvios(Airport.LGW, Airport.YYZ), 3250);
    }

    @Test
    public void testBonuses(){
        assertEquals(pointsService.returnBonus("M"), 0);
        assertEquals(pointsService.returnBonus("W"), 0.2f);
        assertEquals(pointsService.returnBonus("J"), 0.5f);
        assertEquals(pointsService.returnBonus("F"), 1f);
    }

    @Test
    public void testCalculateAvios(){
        assertEquals(pointsService.calculateAvios(Airport.LHR, Airport.LAX, "M"), 4500);
        assertEquals(pointsService.calculateAvios(Airport.LHR, Airport.SFO, "W"), 5280);
        assertEquals(pointsService.calculateAvios(Airport.LHR, Airport.JFK, "J"), 4800);
        assertEquals(pointsService.calculateAvios(Airport.LGW, Airport.YYZ, "F"), 6500);
    }

}
