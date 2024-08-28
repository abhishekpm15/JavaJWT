package com.example.JWT.service;

import com.example.JWT.model.DataModel;
import com.example.JWT.repository.DataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private DataRepo dataRepo;

    public List<DataModel> getAllData(){
        return dataRepo.findAll();
    }

}
