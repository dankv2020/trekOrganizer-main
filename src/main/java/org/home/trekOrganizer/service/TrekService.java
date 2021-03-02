package org.home.trekOrganizer.service;

import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.repository.TrekRepository;
import org.home.trekOrganizer.request.TrekRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrekService {

    @Autowired
    TrekRepository trekRepository;

    public List<Trek> getAllTreks() {
        return trekRepository.findAll();
    }

    public Trek getTrekById(Long id) {
        return trekRepository.findById(id).get();
    }

    public List<Trek> getTreksByNameOrDescriptionContaining(String query) {
        return trekRepository.findByNameOrDescriptionContaining(query, query);
    }

    public Trek createTrek(TrekRequest trekRequest){

        Trek trek = new Trek(trekRequest);
        trek = trekRepository.save(trek);
        return trek;
    }

    public Trek updateTrek(Long id, TrekRequest trekRequest) {
        Trek trek = trekRepository.findById(id).get();

        trek.setName(trekRequest.getName());
        trek.setDuration(trekRequest.getDuration());
        trek.setDescription(trekRequest.getDescription());

        trek = trekRepository.save(trek);
        return trek;
    }

    public String deleteTrek(Long id) {
        trekRepository.deleteById(id);
        return String.format("Trek with id = %s has been deleted successfully", id);
    }

    public Long deleteTreksByName(String name) {
        return trekRepository.deleteByName(name);
    }
}
