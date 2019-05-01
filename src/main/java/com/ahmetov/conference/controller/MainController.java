package com.ahmetov.conference.controller;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.services.PresentationService;
import com.ahmetov.conference.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PresentationService presentationService;

    @Autowired
    RoomService roomService;

    @GetMapping("/admin")
    public String mainPage(Model model){
        List<Presentation> presentations = (List<Presentation>) presentationService.findAll();
        List<Room> rooms = (List<Room>) roomService.findAllRooms();

        Presentation presentation = new Presentation();
        presentation.setPresentationRoom(new Room());

        model.addAttribute("rooms", rooms);
        model.addAttribute("presentations", presentations);
        model.addAttribute("presentation", presentation);
        return "index";
    }

    @RequestMapping(value = "/deletePresentation", method = RequestMethod.POST)
    private String deletePresentation(@RequestParam String id){
        presentationService.deletePresentationById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/addPresentation", method = RequestMethod.POST)
    private String addPresentation(@ModelAttribute Presentation presentation){
        presentationService.save(presentation);
        return "redirect:/";
    }

}
