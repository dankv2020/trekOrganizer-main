package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.request.JourneyRequest;
import org.home.trekOrganizer.request.TrekRequest;
import org.home.trekOrganizer.response.JourneyResponse;
import org.home.trekOrganizer.response.TrekResponse;
import org.home.trekOrganizer.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return getJourneyResponses(journeys);
    }

    @GetMapping("/{id}")
    public JourneyResponse getJourneyById(@PathVariable Long id){

        Journey journey = journeyService.getJourneyById(id);
        return new JourneyResponse(journey);
    }

    @GetMapping("/search")
    public List<JourneyResponse> getJourneysByNameContaining(
            @RequestParam(name = "query") String query){

        List<Journey> journeys = journeyService.getJourneysByNameContaining(query);
        return getJourneyResponses(journeys);
    }

    @PostMapping("/create")
    public JourneyResponse createJourney(@RequestBody @Valid JourneyRequest journeyRequest){

        Journey journey = journeyService.createJourney(journeyRequest);
        return new JourneyResponse(journey);
    }

    @PutMapping("/update/{id}")
    public JourneyResponse updateJourney(@PathVariable Long id, @RequestBody @Valid JourneyRequest journeyRequest) {

        Journey journey = journeyService.updateJourney(id, journeyRequest);
        return new JourneyResponse(journey);
    }

    @PutMapping("/addTrekkers/{id}")
    public JourneyResponse addTrekkersToJourney(@PathVariable Long id,
                                                @RequestParam List<Long> trekkerIdList){

        Journey journey = journeyService.addTrekkers(id, trekkerIdList);
        return new JourneyResponse(journey);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteJourney(@PathVariable Long id){
        return journeyService.deleteJourney(id);
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
