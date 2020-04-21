package com.example.sucursalapp.services;

import com.example.sucursalapp.components.DistanceCalculator;
import com.example.sucursalapp.dtos.BranchOfficeDto;
import com.example.sucursalapp.entities.BranchOffice;
import com.example.sucursalapp.entities.PointVO;
import com.example.sucursalapp.exceptions.BranchOfficeException;
import com.example.sucursalapp.repositories.BranchOfficeRepository;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class BranchOfficeService implements IBranchOfficeService {

    public static final int NEARLY_DISTANCE = 1;
    private final BranchOfficeRepository repository;
    private final DozerBeanMapper mapper;
    private final DistanceCalculator distanceCalculator;

    private Logger log = LoggerFactory.getLogger(BranchOfficeService.class);

    @Autowired
    public BranchOfficeService(BranchOfficeRepository repository,  DozerBeanMapper mapper,
                               DistanceCalculator distanceCalculator){
        this.repository = repository;
        this.distanceCalculator = distanceCalculator;
        this.mapper = mapper;
    }

    public BranchOfficeDto findById(Long id) throws BranchOfficeException {

        try {
            BranchOffice  entity = repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Branch office not exist", 1));
            BranchOfficeDto dto = mapper.map(entity, BranchOfficeDto.class);
            log.info("branch office was encounter: "+ dto.toString() );
            return dto;
        } catch (Exception ex){
            var error = "Error looking branch office with id " + id;
            log.error(error);
            throw new BranchOfficeException(error);
        }
    }

    public List<BranchOfficeDto> list(){
        List<BranchOffice> offices = (List<BranchOffice>)repository.findAll();

        List<BranchOfficeDto> officeDto = offices.stream().map(
                office -> mapper.map(office, BranchOfficeDto.class)
        ).peek(of-> {
            log.info("Branch Office: " + of);
        }).collect(Collectors.toList());

        return officeDto;
    }

    @Override
    public BranchOfficeDto create(BranchOfficeDto officeDto) {
        BranchOffice entity = mapper.map(officeDto, BranchOffice.class);
        entity = this.repository.save(entity);
        officeDto.setId(entity.getId());

        log.info("branch office was persisted " + officeDto);
        return officeDto;
    }

    @Override
    public void deleteById(Long id) throws  BranchOfficeException{
        try {
            this.repository.deleteById(id);
            log.info("The branch office was deleted");
        }catch (Exception ex){
            var error = "Error deleting branch office with id " + id;
            log.error(error);
            throw new BranchOfficeException(error);
        }
    }

    @Override
    public BranchOfficeDto update(Long id, BranchOfficeDto officeDto) throws BranchOfficeException{
        try {
            BranchOffice branchOffice = this.repository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Branch office not exist", 1));
            mapper.map(officeDto, branchOffice);
            this.repository.save(branchOffice);
            log.info("The branch office with id " + id + " was updated");
            return officeDto;
        }catch (Exception ex){
            var error = "update failed. id " + id + " no exist.";
            log.error(error);
            throw new BranchOfficeException(error);
        }
    }

    @Override
    public List<BranchOfficeDto> lookNearly(double latitude, double longitude, Integer distance) {
        List<BranchOfficeDto> branches = this.list();
        PointVO pa = new PointVO(latitude, longitude);
        int d = distance != null? distance: NEARLY_DISTANCE;

        List<BranchOfficeDto> nearlies = branches.stream().filter(
                branch -> this.distanceCalculator.calculateDistance(pa, branch) < d
        ).peek(b -> log.info("branch: " + b)).collect(Collectors.toList());

        return nearlies;
    }
}
