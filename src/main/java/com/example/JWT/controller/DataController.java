package com.example.JWT.controller;

import com.example.JWT.model.DataModel;
import com.example.JWT.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/getAllData")
    public List<DataModel> getAllData(){
        return dataService.getAllData();
    }
}
