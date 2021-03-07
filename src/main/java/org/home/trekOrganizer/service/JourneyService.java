package org.home.trekOrganizer.service;

import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.repository.JourneyRepository;
import org.home.trekOrganizer.repository.TrekRepository;
import org.home.trekOrganizer.repository.TrekkerRepository;
import org.home.trekOrganizer.request.JourneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JourneyService {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    TrekService trekService;

    @Autowired
    TrekkerRepository trekkerRepository;

    @Autowired
    TrekkerService trekkerService;

    public List<Journey> getAllJourneys() {

        List<Journey> journeys = journeyRepository.findAll();
        return journeys;
    }

    public Journey getJourneyById(Long id) {
        Optional<Journey> optionalJourney = journeyRepository.findById(id);

        if (!optionalJourney.isEmpty()) {
            return optionalJourney.get();
        } else {
            throw new ItemNotFoundException("Journey", id);
        }
    }

    public List<Journey> getJourneysByNameContaining(String query) {

        List<Journey> journeys = journeyRepository.findByNameContainingIgnoreCase(query);
        return journeys;
    }

    public Journey createJourney(JourneyRequest journeyRequest) {

        Journey journey = new Journey(journeyRequest);

        Trek trek = trekService.getTrekById(journeyRequest.getTrekId());
        journey.setTrek(trek);

        journey = journeyRepository.save(journey);

        return journey;

    }

    public Journey updateJourney(Long id, JourneyRequest journeyRequest) {

        Optional<Journey> optionalJourney = journeyRepository.findById(id);

        if (!optionalJourney.isEmpty()) {
            Journey journey = optionalJourney.get();

            Trek trek = trekService.getTrekById(journeyRequest.getTrekId());
            journey.setTrek(trek);
            journey.setName(journeyRequest.getName());
            journey = journeyRepository.save(journey);

            return journey;
        } else {
            throw new ItemNotFoundException("Journey", id);
        }
    }

    public Journey addTrekkers(Long id, List<Long> trekkerIdList) {

        Optional<Journey> optionalJourney = journeyRepository.findById(id);


        if (!optionalJourney.isEmpty()) {
            Journey journey = optionalJourney.get();
            Trekker trekker;

            List<Trekker> trekkers = new ArrayList<>();
            for (Long trekkerId : trekkerIdList) {
                trekker = trekkerService.getTrekkerById(trekkerId);
                trekkers.add(trekker);
            }

            journey.setTrekkers(trekkers);
            journey = journeyRepository.save(journey);

            return journey;

        } else {
            throw new ItemNotFoundException("Journey", id);
        }

    }

    public String deleteJourney(Long id) {

        try {
            journeyRepository.deleteById(id);
            return String.format("Journey with id = %s has been deleted successfully", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItemNotFoundException("Journey", id);
        }
    }

    public Integer deleteJourneysByName(String name) {

        return journeyRepository.deleteByName(name);
    }
}
