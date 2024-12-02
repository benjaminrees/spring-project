package com.example.springproject;

import com.example.springproject.Enums.Airport;
import com.example.springproject.Enums.AviosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    PointsService pointsService;

    @Autowired
    public Controller(PointsService pointsService) {
        this.pointsService = pointsService;
    }


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/returnAvios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AviosResponse> getAvios(@RequestParam String dep, @RequestParam String arr, @RequestParam(required = false) String code) {
        List<AviosResponse> responses = pointsService.getBonuses(Airport.valueOf(dep), Airport.valueOf(arr), code);
        return responses;
    }
}
