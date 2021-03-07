package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.exception.ErrorsConverter;
import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.request.JourneyRequest;
import org.home.trekOrganizer.response.JourneyResponse;
import org.home.trekOrganizer.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/journeys/")
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    @GetMapping("/")
    public List<JourneyResponse> getAllJourneys() {
        List<Journey> journeys = journeyService.getAllJourneys();
        if (journeys.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journeys not found");
        return getJourneyResponses(journeys);
    }

    @GetMapping("/{id}")
    public JourneyResponse getJourneyById(@PathVariable Long id){

        try {
            Journey journey = journeyService.getJourneyById(id);
            return new JourneyResponse(journey);
        } catch (ItemNotFoundException exception) {

            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());

        }
    }

    @GetMapping("/search")
    public List<JourneyResponse> getJourneysByNameContaining(
            @RequestParam(name = "query") String query){

        if (query.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "query is required" );
        List<Journey> journeys = journeyService.getJourneysByNameContaining(query);
        if (journeys.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Journeys not found with query " + query);
        return getJourneyResponses(journeys);
    }

    @PostMapping("/create")
    public JourneyResponse createJourney(@RequestBody @Valid JourneyRequest journeyRequest, Errors errors){

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }
        try {
            Journey journey = journeyService.createJourney(journeyRequest);
            return new JourneyResponse(journey);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public JourneyResponse updateJourney(@PathVariable Long id, @RequestBody @Valid JourneyRequest journeyRequest, Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }

        try {
            Journey journey = journeyService.updateJourney(id, journeyRequest);
            return new JourneyResponse(journey);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PutMapping("/addTrekkers/{id}")
    public JourneyResponse addTrekkersToJourney(@PathVariable Long id,
                                                @RequestParam List<Long> trekkerIdList){

        try {
            Journey journey = journeyService.addTrekkers(id, trekkerIdList);
            return new JourneyResponse(journey);
        } catch (ItemNotFoundException exception) {

            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public String deleteJourney(@PathVariable Long id){

        try {
            return journeyService.deleteJourney(id);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/deleteByName")
    public String deleteJourneysByName(@RequestParam(name = "name") String name) {

        return journeyService.deleteJourneysByName(name) + " Journey(s) deleted";
    }

    private List<JourneyResponse> getJourneyResponses(List<Journey> journeys) {
        List<JourneyResponse> journeyResponses = new ArrayList<>();
        journeys.stream().forEach(journey -> {
                    journeyResponses.add(new JourneyResponse(journey));
                }
        );
        return journeyResponses;
    }


}
