package com.example.restdbapp.controllers;

import com.example.restdbapp.models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.restdbapp.repositories.ShowRepository;

@RestController
@RequestMapping("/shows")
public class ShowController {
    @Autowired
    ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody Show show) {
//        TODO validate if needed data is provided
        try {
            showRepository.saveShowToDatabase(show);
        } catch (RuntimeException e) {
            System.out.println("Ran into an error while registering show.");
            throw  new RuntimeException(e);
        }
        return "Successfully registered the show";
    }

    @PostMapping("/delete")
    public String delete(@RequestBody String id) {
//        TODO validation
        try {
            showRepository.deleteShowFromDatabase(id);
        } catch (RuntimeException e) {
            System.out.println("Ran into an error while registering show.");
            throw  new RuntimeException(e);
        }
        return "Success";
    }
}
