package com.novaBankpractice.controller;

import com.novaBankpractice.service.DataResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataResetService dataResetService;

    public DataController(DataResetService dataResetService) {
        this.dataResetService = dataResetService;
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetData() {
        dataResetService.resetAll();
        return ResponseEntity.ok("Base de datos reseteada con éxito.");
    }
}
