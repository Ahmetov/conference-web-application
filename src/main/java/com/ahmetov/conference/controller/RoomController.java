package com.ahmetov.conference.controller;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.services.PresentationService;
import com.ahmetov.conference.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * controller for list of rooms and presentations (allowed for all)
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    PresentationService presentationService;

    @Autowired
    RoomService roomService;

    @GetMapping
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("scedule");

        List<Room> rooms = (List<Room>) roomService.findAllRooms();
        modelAndView.addObject("rooms", rooms);
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView getPresentationsInRoom(@PathVariable String id) {
        Room room = roomService.findRoomById(id);
        List<Presentation> presentationList = (List<Presentation>) presentationService.findByPresentationRoom(room);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("presentationsByRoom");
        modelAndView.addObject("presentations", presentationList);

        return modelAndView;
    }

}
