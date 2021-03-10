package org.home.trekOrganizer.service;

import org.home.trekOrganizer.exception.ItemNotFoundException;
import org.home.trekOrganizer.model.Manager;
import org.home.trekOrganizer.model.Trek;
import org.home.trekOrganizer.repository.ManagerRepository;
import org.home.trekOrganizer.request.ManagerRequest;
import org.home.trekOrganizer.request.TrekRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    ManagerRepository managerRepository;

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

        public Manager getManagerById(Long id) {
        Optional<Manager> optionalManager = managerRepository.findById(id);
        if (!optionalManager.isEmpty()) {

            return optionalManager.get();
        } else {
            throw new ItemNotFoundException("Manager", id);
        }
    }


    public Manager getManagerByLogin(String login) {
        return managerRepository.findByLogin(login);
    }

    public Manager createManager(ManagerRequest managerRequest) {

        Manager manager = new Manager(managerRequest);
        manager = managerRepository.save(manager);
        return manager;
    }


    public Manager updateManager(Long id, ManagerRequest managerRequest) {

        Optional<Manager> optionalManager = managerRepository.findById(id);

        if (!optionalManager.isEmpty()) {
            Manager manager = optionalManager.get();
            manager.setName(managerRequest.getName());
            manager.setLogin(managerRequest.getLogin());
            manager.setPassword(managerRequest.getPassword().toCharArray());
            manager.setEnabled(managerRequest.getEnabled());
            manager.setRole(managerRequest.getRole());

            manager = managerRepository.save(manager);

            return manager;
        } else {
            throw new ItemNotFoundException("Manager", id);
        }
    }

    public String deleteManager(Long id) {

        try {
            managerRepository.deleteById(id);
            return String.format("Manager with id = %s has been deleted successfully", id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ItemNotFoundException("Manager", id);
        }
    }

    public Integer deleteManagerByLogin(String login) {
        return managerRepository.deleteByLogin(login);
    }
}
