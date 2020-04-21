package com.example.sucursalapp.controllers;


import com.example.sucursalapp.dtos.BranchOfficeDto;
import com.example.sucursalapp.exceptions.BranchOfficeException;
import com.example.sucursalapp.services.IBranchOfficeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BranchOfficeController.URL)
public class BranchOfficeController {

    public static final String URL = "/branch-office";
    private final IBranchOfficeService service;

    public BranchOfficeController(IBranchOfficeService service){
        this.service = service;
    }

    @GetMapping
    public List<BranchOfficeDto> findAll(){
        return this.service.list();
    }

    @GetMapping("/{id}")
    public BranchOfficeDto findById(@PathVariable Long id) throws BranchOfficeException {
        return this.service.findById( id );
    }

    @GetMapping("/look-nearly")
    public List<BranchOfficeDto> findById(@RequestParam double  latitude , @RequestParam double longitude,
                                          @RequestParam(required = false) Integer distance) throws BranchOfficeException {
        return this.service.lookNearly(latitude, longitude, distance);
    }

    @PostMapping
    public BranchOfficeDto create(@RequestBody BranchOfficeDto officeDto){
        return this.service.create( officeDto );
    }

    @DeleteMapping("/{id}")
    public void deletedById(@PathVariable Long id) throws BranchOfficeException {
        this.service.deleteById( id );
    }

    @PutMapping("/{id}")
    public BranchOfficeDto update(@PathVariable Long id, @RequestBody BranchOfficeDto officeDto) throws BranchOfficeException{
        return this.service.update(id, officeDto);
    }
}
