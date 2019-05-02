package com.ahmetov.conference.controller;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.entities.User;
import com.ahmetov.conference.repository.UserRepository;
import com.ahmetov.conference.services.PresentationService;
import com.ahmetov.conference.services.RoomService;
import com.ahmetov.conference.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class MainController {

    @Autowired
    PresentationService presentationService;

    @Autowired
    RoomService roomService;


    @GetMapping()
    public String mainPage(Model model){
        List<Presentation> presentations = (List<Presentation>) presentationService.findAll();
        List<Room> rooms = (List<Room>) roomService.findAllRooms();

        Presentation presentation = new Presentation();
        presentation.setPresentationRoom(new Room());

        model.addAttribute("rooms", rooms);
        model.addAttribute("presentations", presentations);
        model.addAttribute("presentation", presentation);
        return "admin";
    }




    @PostMapping(value = "deletePresentation")
    public String deletePresentation(@RequestParam String id){
        presentationService.deletePresentationById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "addPresentation")
    public String addPresentation(@ModelAttribute Presentation presentation){
        presentationService.save(presentation);
        return "redirect:/admin";
    }

}
