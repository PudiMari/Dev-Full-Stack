package com.mariana.os.resources;

import com.mariana.os.dtos.OSDTO;
import com.mariana.os.services.OSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/os")
public class OSResource {

    @Autowired
    private OSService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
        OSDTO obj = new OSDTO(service.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OSDTO> list = service.findAll().stream()
                .map(OSDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }
}
