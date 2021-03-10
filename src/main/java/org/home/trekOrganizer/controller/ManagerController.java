package org.home.trekOrganizer.controller;

import org.home.trekOrganizer.exception.ErrorsConverter;
import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Manager;
import org.home.trekOrganizer.request.ManagerRequest;
import org.home.trekOrganizer.response.ManagerResponse;
import org.home.trekOrganizer.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping("/")
    public List<ManagerResponse> getAllManagers() {
        List<Manager> managers = managerService.getAllManagers();
        if (managers.size() == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Managers not found");
        return getManagerResponses(managers);
    }

    @GetMapping("/{id}")
    public ManagerResponse getManagerById(@PathVariable Long id) {

        try {
            Manager manager = managerService.getManagerById(id);
            return new ManagerResponse(manager);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/search")
    public ManagerResponse getManagerByLogin(
            @RequestParam(name = "login") String login) {

        if (login.isBlank()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "login is required" );
        Manager manager = managerService.getManagerByLogin(login);
        if (manager == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manager not found with login = " + login); //Yoda style))
        return new ManagerResponse(manager);
    }

    @PostMapping("/create")
    public ManagerResponse createManager(@RequestBody @Valid ManagerRequest managerRequest, Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }

        if (managerService.getManagerByLogin(managerRequest.getLogin()) == null) {
            Manager manager = managerService.createManager(managerRequest);
            return new ManagerResponse(manager);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Manager exists with login "+ managerRequest.getLogin());
        }
    }

    @PutMapping("/update/{id}")
    public ManagerResponse updateManager(@PathVariable Long id, @RequestBody @Valid ManagerRequest managerRequest, Errors errors) {

        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ErrorsConverter.getMessage(errors));
        }

        try {
            Manager manager = managerService.updateManager(id, managerRequest);
            return new ManagerResponse(manager);
        } catch (ItemNotFoundException exception) {

            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteManager(@PathVariable Long id) {
        try {
            return managerService.deleteManager(id);
        } catch (ItemNotFoundException exception) {
            exception.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/deleteByLogin")
    public String deleteManagerByLogin(@RequestParam(name = "login") String login) {

        return managerService.deleteManagerByLogin(login) + " Manager deleted";
    }

    private List<ManagerResponse> getManagerResponses(List<Manager> managers) {

        List<ManagerResponse> managerResponses = new ArrayList<>();
        managers.stream().forEach(manager -> {
                    managerResponses.add(new ManagerResponse(manager));
                }
        );
        return managerResponses;
    }

}
