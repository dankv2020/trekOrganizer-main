package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.exception.ErrorsConverter;
import org.home.trekOrganizer.exception.TrekkerNotFoundException;
import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.request.TrekkerRequest;
import org.home.trekOrganizer.response.TrekkerResponse;
import org.home.trekOrganizer.service.TrekkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trekkers/")
public class TrekkerController {

    @Autowired
    TrekkerService trekkerService;

    @GetMapping("/")
    public List<TrekkerResponse> getAllTrekkers() {

        List<Trekker> trekkers = trekkerService.getAllTrekkers();
        if (trekkers.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trekkers not found");
        return getTrekkerResponses(trekkers);
    }

    @GetMapping("/{id}")
    public TrekkerResponse getTrekkerById(@PathVariable(name = "id") Long id) {

        try {
            Trekker trekker = trekkerService.getTrekkerById(id);
            return new TrekkerResponse(trekker);
        } catch (TrekkerNotFoundException exception) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/search")
    public List<TrekkerResponse> getTrekkersByFullNameOrEmailContaining(
            @RequestParam(name = "query") String query) {

        List<Trekker> trekkers = trekkerService.getTrekkersByFullNameOrEmailContaining(query);
        if (trekkers.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trekkers not found with query = " + query);
        return getTrekkerResponses(trekkers);
    }

    @PostMapping("/create")
    public TrekkerResponse createTrekker(@Valid @RequestBody TrekkerRequest trekkerRequest, Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }
        Trekker trekker = trekkerService.createTrekker(trekkerRequest);
        return new TrekkerResponse(trekker);

    }

    @PutMapping("/update/{id}")
    public TrekkerResponse updateTrekker(@PathVariable(name = "id") Long id, @Valid @RequestBody TrekkerRequest trekkerRequest
            , Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }

        try {
            Trekker trekker = trekkerService.updateTrekker(id, trekkerRequest);
            return new TrekkerResponse(trekker);
        } catch (TrekkerNotFoundException exception) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrekkerById(@PathVariable(name = "id") Long id) {
        try {
            return trekkerService.deleteTrekker(id);
        } catch (TrekkerNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }

    }

    @DeleteMapping("/deleteByFullName")
    public String deleteTrekkersByFullName(@RequestParam(name = "fullName") String fullName) {

        return trekkerService.deleteTrekkersByFullName(fullName) + " Trekker(s) deleted";
    }


    private List<TrekkerResponse> getTrekkerResponses(List<Trekker> trekkers) {
        List<TrekkerResponse> trekkerResponses = new ArrayList<>();
        trekkers.stream().forEach(trekker -> {
                    trekkerResponses.add(new TrekkerResponse(trekker));
                }
        );
        return trekkerResponses;
    }


}
