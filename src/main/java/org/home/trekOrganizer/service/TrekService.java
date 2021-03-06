package org.home.trekOrganizer.service;

import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.repository.TrekRepository;
import org.home.trekOrganizer.request.TrekRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrekService {

    @Autowired
    TrekRepository trekRepository;

    public List<Trek> getAllTreks() {
        return trekRepository.findAll();
    }

    public Trek getTrekById(Long id) {
        Optional<Trek> optionalTrek = trekRepository.findById(id);
        if (!optionalTrek.isEmpty()) {

            return optionalTrek.get();
        } else {
            throw new ItemNotFoundException("Trek", id);
        }
    }

    public List<Trek> getTreksByNameOrDescriptionContaining(String query) {
        return trekRepository.findByNameOrDescriptionContainingIgnoreCase(query, query);
    }

    public Trek createTrek(TrekRequest trekRequest) {

        Trek trek = new Trek(trekRequest);
        trek = trekRepository.save(trek);
        return trek;
    }

    public Trek updateTrek(Long id, TrekRequest trekRequest) {

        Optional<Trek> optionalTrek = trekRepository.findById(id);

        if (!optionalTrek.isEmpty()) {
            Trek trek = optionalTrek.get();
            trek.setName(trekRequest.getName());
            trek.setDuration(trekRequest.getDuration());
            trek.setDescription(trekRequest.getDescription());

            trek = trekRepository.save(trek);
            return trek;
        } else {
            throw new ItemNotFoundException("Trek", id);
        }
    }

    public String deleteTrek(Long id) {

        try {
            trekRepository.deleteById(id);
            return String.format("Trek with id = %s has been deleted successfully", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItemNotFoundException("Trek", id);
        }
    }

    public Integer deleteTreksByName(String name) {
        return trekRepository.deleteByName(name);
    }
}
