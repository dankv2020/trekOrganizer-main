package org.home.trekOrganizer.service;

import org.home.trekOrganizer.exception.TrekkerNotFoundException;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.repository.TrekkerRepository;
import org.home.trekOrganizer.request.TrekkerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrekkerService {

    @Autowired
    TrekkerRepository trekkerRepository;

    public List<Trekker> getAllTrekkers() {

        return trekkerRepository.findAll();
    }

    public Trekker getTrekkerById(Long id) {

        Optional<Trekker> optionalTrekker = trekkerRepository.findById(id);

        if (!optionalTrekker.isEmpty()) {

            Trekker trekker = optionalTrekker.get();
            return trekker;
        } else {

            throw new TrekkerNotFoundException(id);
        }

    }

    public List<Trekker> getTrekkersByFullNameOrEmailContaining(String query) {

        return trekkerRepository.findByFullNameOrEmailContainingIgnoreCase(query, query);
    }

    public Trekker createTrekker(TrekkerRequest trekkerRequest) {

        Trekker trekker = new Trekker(trekkerRequest);
        trekker = trekkerRepository.save(trekker);
        return trekker;
    }

    public Trekker updateTrekker(Long id, TrekkerRequest trekkerRequest) {

        Optional<Trekker> optionalTrekker = trekkerRepository.findById(id);

        if (!optionalTrekker.isEmpty()) {
            Trekker trekker = optionalTrekker.get();

            trekker.setFullName(trekkerRequest.getFullName());
            trekker.setEmail(trekkerRequest.getEmail());
            trekker.setExperience(trekkerRequest.getExperience());

            trekker = trekkerRepository.save(trekker);
            return trekker;
        } else {
            throw new TrekkerNotFoundException(id);
        }

    }

    public String deleteTrekker(Long id) {

        try {
            trekkerRepository.deleteById(id);
            return String.format("Trekker with id = %s has been deleted successfully", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TrekkerNotFoundException(id);
        }
    }

    public Integer deleteTrekkersByFullName(String fullName) {

        return trekkerRepository.deleteByFullName(fullName);
    }


}
