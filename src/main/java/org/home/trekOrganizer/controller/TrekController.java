package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.exception.ErrorsConverter;
import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.request.TrekRequest;
import org.home.trekOrganizer.response.TrekResponse;
import org.home.trekOrganizer.service.TrekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/treks/")
public class TrekController {

    @Autowired
    TrekService trekService;

    @GetMapping("/")
    public List<TrekResponse> getAllTreks() {
        List<Trek> treks = trekService.getAllTreks();
        if (treks.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treks not found");
        return getTrekResponses(treks);
    }

    @GetMapping("/{id}")
    public TrekResponse getTrekById(@PathVariable Long id) {

        try {
            Trek trek = trekService.getTrekById(id);
            return new TrekResponse(trek);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/search")
    public List<TrekResponse> getTreksByNameOrDescriptionContaining(
            @RequestParam(name = "query") String query) {

        if (query.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "query is required" );
        List<Trek> treks = trekService.getTreksByNameOrDescriptionContaining(query);
        if (treks.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treks not found with query = " + query);
        return getTrekResponses(treks);
    }

    @PostMapping("/create")
    public TrekResponse createTrek(@RequestBody @Valid TrekRequest trekRequest, Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }
        Trek trek = trekService.createTrek(trekRequest);
        return new TrekResponse(trek);
    }

    @PutMapping("/update/{id}")
    public TrekResponse updateTrek(@PathVariable Long id, @RequestBody @Valid TrekRequest trekRequest, Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }

        try {
            Trek trek = trekService.updateTrek(id, trekRequest);
            return new TrekResponse(trek);
        } catch (ItemNotFoundException exception) {

            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrek(@PathVariable Long id) {
        try {
            return trekService.deleteTrek(id);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/deleteByName")
    public String deleteTreksByName(@RequestParam(name = "name") String name) {

        return trekService.deleteTreksByName(name) + " Trek(s) deleted";
    }

    private List<TrekResponse> getTrekResponses(List<Trek> treks) {
        List<TrekResponse> trekResponses = new ArrayList<>();
        treks.stream().forEach(trek -> {
                    trekResponses.add(new TrekResponse(trek));
                }
        );
        return trekResponses;
    }


}
