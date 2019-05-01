package com.ahmetov.conference.controller;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.entities.Room;
import com.ahmetov.conference.services.PresentationService;
import com.ahmetov.conference.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
public class RoomController {

    @Autowired
    PresentationService presentationService;

    @Autowired
    RoomService roomService;

    @GetMapping
    public ModelAndView homePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("scedule");

        List<Room> rooms = (List<Room>) roomService.findAllRooms();
        modelAndView.addObject("rooms", rooms);
        return  modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView getPresentationsInRoom(@PathVariable String id){
        Room room = roomService.findRoomById(id);
        List<Presentation> presentationList = (List<Presentation>) presentationService.findByPresentationRoom(room);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("presentationsByRoom");
        modelAndView.addObject("presentations", presentationList);

        return  modelAndView;
    }
//
//    @GetMapping("/")
//    public String mainPage(Model model){
//        List<Presentation> presentations = (List<Presentation>) presentationService.findAll();
//        List<Room> rooms = (List<Room>) roomService.findAllRooms();
//
//        for(Presentation p : presentations){
//            System.out.println(p.getId());
//        }
//
//        model.addAttribute("rooms", rooms);
//        //model.addAttribute("presentations_room", presentations);
//
//        return "scedule";
//    }
}
