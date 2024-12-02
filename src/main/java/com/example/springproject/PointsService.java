package com.example.springproject;

import com.example.springproject.Enums.Airport;
import com.example.springproject.Enums.AviosResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PointsService {
    ArrayList<Route> routes;
    ArrayList<CabinBonus> bonuses;

    PointsService() {
        Route LhrToLax = new Route(Airport.LHR, Airport.LAX, 4500);
        Route LhrToSfo = new Route(Airport.LHR, Airport.SFO, 4400);
        Route LhrToJfk = new Route(Airport.LHR, Airport.JFK, 3200);
        Route LgwToYyz = new Route(Airport.LGW, Airport.YYZ, 3250);

        routes = new ArrayList<>();
        routes.add(LhrToLax);
        routes.add(LhrToSfo);
        routes.add(LhrToJfk);
        routes.add(LgwToYyz);

        CabinBonus worldTraveller = new CabinBonus("World Traveller", "M", 0);
        CabinBonus worldTravellerPlus = new CabinBonus("World Traveller Plus", "W", 0.2f);
        CabinBonus clubWorld = new CabinBonus("Club World", "J", 0.5f);
        CabinBonus first = new CabinBonus("First", "F", 1f);

        bonuses = new ArrayList<>();
        bonuses.add(worldTraveller);
        bonuses.add(worldTravellerPlus);
        bonuses.add(clubWorld);
        bonuses.add(first);
    }


    public int returnAvios(Airport depAirportCode, Airport arrAirportCode) {
        int points = 0;
        for (Route route : routes) {
            if (Arrays.asList(route.getAirports()).contains(depAirportCode) && Arrays.asList(route.getAirports()).contains(arrAirportCode)) {
                points = route.getPointsEarned();
                break;
            }
        }
        return points;
    }

    public float returnBonus(String cabinCode) {
        float bonus = 0;
        for (CabinBonus cabinBonus : bonuses) {
            if (cabinBonus.getCabinCode() == cabinCode) bonus = cabinBonus.getBonus();
        }
        return bonus;
    }

    public int calculateAvios(
            Airport arrAirportCode,
            Airport depAirportCode,
            String cabinCode) {

        int avios = 0;
        float bonus = 0;
        avios = returnAvios(arrAirportCode, depAirportCode);
        bonus = returnBonus(cabinCode);

        return avios + (int) (avios * bonus);
    }

    public List<AviosResponse> getBonuses(Airport dep, Airport arr, String code) {
        List<AviosResponse> pointsBonuses = new ArrayList<>();

        if (code == null) {
            for (CabinBonus cabinBonus : bonuses) {
                pointsBonuses.add(new AviosResponse(calculateAvios(arr, dep, cabinBonus.getCabinCode()), cabinBonus.getCabinCode()));
            }
        } else {
            pointsBonuses.add(new AviosResponse(calculateAvios(arr, dep, code), code));
        }

        return pointsBonuses;
    }

}
