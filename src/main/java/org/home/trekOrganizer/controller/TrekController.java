package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.request.TrekRequest;
import org.home.trekOrganizer.response.TrekResponse;
import org.home.trekOrganizer.service.TrekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/treks/")
public class TrekController {

    @Autowired
    TrekService trekService;

    @GetMapping("/")
    public List<TrekResponse> getAllTreks() {
        List<Trek> treks = trekService.getAllTreks();
        return getTrekResponses(treks);
    }

    @GetMapping("/{id}")
    public TrekResponse getTrekById(@PathVariable Long id){

        Trek trek = trekService.getTrekById(id);
        return new TrekResponse(trek);
    }

    @GetMapping("/search")
    public List<TrekResponse> getTreksByNameOrDescriptionContaining(
            @RequestParam (name = "query") String query){

        List<Trek> treks = trekService.getTreksByNameOrDescriptionContaining(query);
        return getTrekResponses(treks);
    }

    @PostMapping("/create")
    public TrekResponse createTrek(@RequestBody @Valid TrekRequest trekRequest){

        Trek trek = trekService.createTrek(trekRequest);
        return new TrekResponse(trek);
    }

    @PutMapping("/update/{id}")
    public TrekResponse updateTrek(@PathVariable Long id, @RequestBody @Valid TrekRequest trekRequest) {

        Trek trek = trekService.updateTrek(id, trekRequest);
        return new TrekResponse(trek);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrek(@PathVariable Long id){
        return trekService.deleteTrek(id);
    }

    @DeleteMapping("/deleteByName")
    public String deleteTreksByName(@RequestParam(name = "name") String name) {

        return trekService.deleteTreksByName(name) + " Trek(s) deleted";
    }

    private List<TrekResponse> getTrekResponses(List<Trek> treks) {
        List<TrekResponse> trekResponses = new ArrayList<>();
        treks.stream().forEach(trek -> {
          trekResponses.add(new TrekResponse(trek));
                }
        );
        return trekResponses;
    }



}
