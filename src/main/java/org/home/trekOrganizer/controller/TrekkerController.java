package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.model.Trekker;
import org.home.trekOrganizer.request.TrekkerRequest;
import org.home.trekOrganizer.response.TrekkerResponse;
import org.home.trekOrganizer.service.TrekkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return getTrekkerResponses(trekkers);
    }

    @GetMapping("/{id}")
    public TrekkerResponse getTrekkerById(@PathVariable(name = "id") Long id) {
        Trekker trekker = trekkerService.getTrekkerById(id);
        return new TrekkerResponse(trekker);
    }

    @GetMapping("/search")
    public List<TrekkerResponse> getTrekkersByFullNameOrEmailContaining(
            @RequestParam(name = "query") String query) {

        List<Trekker> trekkers = trekkerService.getTrekkersByFullNameOrEmailContaining(query);
        return getTrekkerResponses(trekkers);
    }

    @PostMapping ("/create")
    public TrekkerResponse createTrekker(@Valid @RequestBody TrekkerRequest trekkerRequest) {

        Trekker trekker = trekkerService.createTrekker(trekkerRequest);

        return new TrekkerResponse(trekker);
    }

    @PutMapping("/update/{id}")
    public TrekkerResponse updateTrekker(@PathVariable(name = "id") Long id, @Valid @RequestBody TrekkerRequest trekkerRequest){
        Trekker trekker = trekkerService.updateTrekker(id, trekkerRequest);
        return new TrekkerResponse(trekker);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTrekkerById(@PathVariable(name = "id") Long id) {

        return trekkerService.deleteTrekker(id);
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
