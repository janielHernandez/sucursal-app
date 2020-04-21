package com.example.sucursalapp.services;

import com.example.sucursalapp.dtos.BranchOfficeDto;
import com.example.sucursalapp.exceptions.BranchOfficeException;

import java.util.List;
public interface IBranchOfficeService {

    BranchOfficeDto findById(Long id) throws BranchOfficeException;
    List<BranchOfficeDto> list();
    BranchOfficeDto create(BranchOfficeDto officeDto);
    void deleteById(Long id) throws BranchOfficeException;
    BranchOfficeDto update(Long id, BranchOfficeDto officeDto) throws BranchOfficeException;
    List<BranchOfficeDto> lookNearly(double longitude, double latitude, Integer distance);
}
