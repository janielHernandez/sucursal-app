package com.example.sucursalapp.services;


import com.example.sucursalapp.dtos.BranchOfficeDto;
import com.example.sucursalapp.entities.BranchOffice;
import com.example.sucursalapp.exceptions.BranchOfficeException;
import com.example.sucursalapp.repositories.BranchOfficeRepository;
import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BranchOfficeServiceTest {

    @Mock
    BranchOfficeRepository repository;

    @Mock
    DozerBeanMapper mapper;

    @InjectMocks
    BranchOfficeService service;

    BranchOffice branchOffice;

    @Before
    public void init() {
        branchOffice = new BranchOffice();
        branchOffice.setId(12L);
        branchOffice.setLatitude(-2.323);
        branchOffice.setLongitude(4.343);
    }

    @Test
    public void findAllBranchOffice() {
        when(repository.findAll() ).thenReturn(Arrays.asList(
                new BranchOffice[]{this.branchOffice }
        ) );
        assertEquals(1, service.list().size());
    }

    @Test
    public void findBranchOfficeByIdTest() throws BranchOfficeException {
        when(repository.findById(12L)).thenReturn(java.util.Optional.of(branchOffice));
        BranchOfficeDto dto = new BranchOfficeDto();
        dto.setId(branchOffice.getId());
        dto.setLatitude(branchOffice.getLatitude());
        dto.setLongitude(branchOffice.getLongitude());

        when(mapper.map(branchOffice,  BranchOfficeDto.class)).thenReturn(dto);

        BranchOfficeDto result = service.findById(12L);
        this.commonBranchOfficeTest(result);
    }

    @Test
    public void saveBranchOfficeTest(){
        when(repository.save( branchOffice )).thenReturn( branchOffice );
        BranchOfficeDto dto = mapper.map(branchOffice, BranchOfficeDto.class);
        BranchOfficeDto result = service.create( dto  );
        this.commonBranchOfficeTest(result);
    }

    private void commonBranchOfficeTest(BranchOfficeDto b){
        assertEquals(12L, b.getId().longValue() );
        assertEquals("-2.323", b.getLatitude().toString() );
        assertEquals("4.343", b.getLongitude().toString());
    }

}
