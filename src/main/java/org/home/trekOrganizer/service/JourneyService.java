package org.home.trekOrganizer.service;

import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.repository.JourneyRepository;
import org.home.trekOrganizer.request.JourneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    TrekService trekService;

    public List<Journey> getAllJourneys() {

        List<Journey> journeys = journeyRepository.findAll();
        return journeys;
    }

    public Journey getJourneyById(Long id) {

        return journeyRepository.findById(id).get();
    }

    public List<Journey> getJourneysByNameContaining(String query) {

        List<Journey> journeys = journeyRepository.findByNameContainingIgnoreCase(query);
        return journeys;
    }

    public Journey createJourney(JourneyRequest journeyRequest) {

        Journey journey = new Journey(journeyRequest);
        journey = journeyRepository.save(journey);
        return journey;

    }

    public String deleteJourney(Long id) {

        journeyRepository.deleteById(id);
        return String.format("Journey with id = %s has been deleted successfully", id);
    }

    public Integer deleteJourneysByName(String name) {

        return journeyRepository.deleteByName(name);
    }
}
