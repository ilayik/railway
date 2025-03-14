package com.example.railway.controller;

import com.example.railway.model.Train;
import com.example.railway.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainRestControllerV1 {

    private final TrainService trainService;

    @Autowired
    public TrainRestControllerV1(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('read')")
    public Iterable<Train> getTrains() {
        return trainService.getTrain();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('write')")
    public Train addTrain(@RequestBody Train train) {
        return trainService.addTrain(train);
    }

    @PostMapping("/find")
    @PreAuthorize("hasAuthority('read')")
    public List<Train> findTrain(@RequestBody List<String> trainSearchParam) {
        try {
            return trainService.findTrain(trainSearchParam);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('read')")
    public Train updateTrain(@RequestBody Train train) {
        return trainService.updateTrain(train);
    }
}
