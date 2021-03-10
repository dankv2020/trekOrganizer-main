package org.home.trekOrganizer.service;

import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Journey;
import org.home.trekOrganizer.model.Manager;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.repository.JourneyRepository;
import org.home.trekOrganizer.request.JourneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    TrekkerService trekkerService;

    @Autowired
    ManagerService managerService;

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

    public Journey createJourney(JourneyRequest journeyRequest, String currentManager) {

        Manager manager = null;
        Journey journey = new Journey(journeyRequest);

        Trek trek = trekService.getTrekById(journeyRequest.getTrekId());
        journey.setTrek(trek);


        if (journeyRequest.getManagerId() != null ) {

            manager = managerService.getManagerById(journeyRequest.getManagerId());

        } else { //get Manager from currentSession
            manager = managerService.getManagerByLogin(currentManager);

        }

        journey.setManager(manager);

        journey = journeyRepository.save(journey);

        return journey;

    }

    public Journey updateJourney(Long id, JourneyRequest journeyRequest, String currentManager) {

        Optional<Journey> optionalJourney = journeyRepository.findById(id);

        if (!optionalJourney.isEmpty()) {
            Journey journey = optionalJourney.get();

            Trek trek = trekService.getTrekById(journeyRequest.getTrekId());
            journey.setTrek(trek);
            journey.setName(journeyRequest.getName());
            Manager manager;
            if (journeyRequest.getManagerId() != null ) {

                manager = managerService.getManagerById(journeyRequest.getManagerId());

            } else { //get Manager from currentSession
                manager = managerService.getManagerByLogin(currentManager);

            }
            journey.setManager(manager);

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
