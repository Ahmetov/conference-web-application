package com.ahmetov.conference.controller;

import com.ahmetov.conference.entities.Presentation;
import com.ahmetov.conference.services.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    PresentationService presentationService;

    @GetMapping("/")
    public String mainPage(Model model){
        List<Presentation> presentations = (List<Presentation>) presentationService.findAll();
        model.addAttribute("presentations", presentations);
        model.addAttribute("presentation", new Presentation());
        return "index";
    }

    @RequestMapping(value = "/deletePresentation", method = RequestMethod.POST)
    private String deletePresentation(@RequestParam String id){
        System.out.println("pres id = " + id);
        presentationService.deletePresentationById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/addPresentation", method = RequestMethod.POST)
    private String addPresentation(@ModelAttribute Presentation presentation){
        presentationService.save(presentation);
        return "redirect:/";
    }

}
