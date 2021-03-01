package org.home.trekOrganizer.service;

import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.repository.TrekkerRepository;
import org.home.trekOrganizer.request.TrekkerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrekkerService {

    @Autowired
    TrekkerRepository trekkerRepository;

    public List<Trekker> getByFullNameOrEmail(String fullName, String email) {

        return trekkerRepository.findByFullNameOrEmail(fullName, email);
    }

    public List<Trekker> getByFullNameOrEmailContaining(String search) {

        return trekkerRepository.findByFullNameOrEmailContaining(search, search);
    }

    public List<Trekker> getAllTrekkers() {

        return trekkerRepository.findAll();
    }

    public Trekker createTrekker(TrekkerRequest trekkerRequest) {

        Trekker trekker = new Trekker(trekkerRequest);
        trekker = trekkerRepository.save(trekker);
        return trekker;
    }


    public Trekker getTrekkerById(Long id) {

        return trekkerRepository.getOne(id);
    }

    public String deleteTrekker(Long id) {

        trekkerRepository.deleteById(id);
        return String.format("Trekker with id = %s has been deleted successfully", id);
    }

    public Integer deleteTrekkerByFullName(String fullName) {

        return trekkerRepository.deleteByFullName(fullName);
    }

    public Trekker updateTrekker(Long id, TrekkerRequest trekkerRequest) {
        Trekker trekker = trekkerRepository.findById(id).get();
        if (trekker == null) System.out.println("No trekker with id = " + id);

        trekker.setFullName(trekkerRequest.getFullName());
        trekker.setEmail(trekkerRequest.getEmail());
        trekker.setExperience(trekkerRequest.getExperience());

        trekker = trekkerRepository.save(trekker);
        return trekker;
    }


}
