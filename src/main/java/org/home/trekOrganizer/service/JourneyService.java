package org.home.trekOrganizer.service;

import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.repository.JourneyRepository;
import org.home.trekOrganizer.repository.TrekRepository;
import org.home.trekOrganizer.repository.TrekkerRepository;
import org.home.trekOrganizer.request.JourneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    TrekRepository trekRepository;

    @Autowired
    TrekkerRepository trekkerRepository;

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
        Trek trek = trekRepository.findById(journeyRequest.getTrekId()).get();
        journey.setTrek(trek);

        journey = journeyRepository.save(journey);

        return journey;

    }

    public Journey updateJourney(Long id, JourneyRequest journeyRequest) {

        Journey journey = journeyRepository.findById(id).get();
        Trek trek = trekRepository.findById(journeyRequest.getTrekId()).get();

        journey.setTrek(trek);
        journey.setName(journeyRequest.getName());
        journey = journeyRepository.save(journey);

        return journey;
    }

    public Journey addTrekkers(Long id, List<Long> trekkerIdList) {

        Journey journey = journeyRepository.findById(id).get();
        Trekker trekker;

        List<Trekker> trekkers = new ArrayList<>();
        for (Long trekkerId: trekkerIdList) {
            trekker = trekkerRepository.findById(trekkerId).get();
            trekkers.add(trekker);
            }

        journey.setTrekkers(trekkers);
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
