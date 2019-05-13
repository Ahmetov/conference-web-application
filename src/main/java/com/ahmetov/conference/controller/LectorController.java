package com.ahmetov.conference.controller;

import com.ahmetov.conference.dto.PresentationDto;
import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.services.PresentationService;
import com.ahmetov.conference.services.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller for lector page (allowed for admin and lector)
 */
@Controller
@PreAuthorize("hasAuthority('PRESENTER')")
public class LectorController {
    private PresentationService presentationService;

    private RoomService roomService;

    @Autowired
    public LectorController(PresentationService presentationService, RoomService roomService) {
        this.presentationService = presentationService;
        this.roomService = roomService;
    }

    @GetMapping("/lector")
    public String mainPage(Model model) {
        List<Presentation> presentations = (List<Presentation>) presentationService.findAll();
        List<Room> rooms = (List<Room>) roomService.findAllRooms();
        PresentationDto presentation = new PresentationDto();
        presentation.setPresentationRoom(new Room());
        model.addAttribute("rooms", rooms);
        model.addAttribute("presentations", presentations);
        model.addAttribute("presentation", presentation);
        return "lector";
    }

    @PostMapping(value = "lector/deletePresentation")
    public String deletePresentation(@RequestParam String id) {
        presentationService.deletePresentationById(id);
        return "redirect:/lector";
    }

    @PostMapping(value = "lector/addPresentation")
    public String addPresentation(@ModelAttribute PresentationDto presentationDto) {
        Presentation presentation = new Presentation();
        BeanUtils.copyProperties(presentationDto, presentation);
        presentationService.save(presentation);
        return "redirect:/lector";
    }

    @RequestMapping("lector/{id}")
    public String updatePresentation(@PathVariable("id") String id, Model model) {
        PresentationDto presentationDto = new PresentationDto();
        BeanUtils.copyProperties(presentationService.findPresentationById(id), presentationDto);
        model.addAttribute("presentation", presentationDto);
        List<Presentation> presentations = (List<Presentation>) presentationService.findAll();
        List<Room> rooms = (List<Room>) roomService.findAllRooms();
        model.addAttribute("rooms", rooms);
        model.addAttribute("presentations", presentations);
        return "lector";
    }

}
