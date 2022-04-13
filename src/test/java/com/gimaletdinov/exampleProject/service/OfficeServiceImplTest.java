package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OfficeServiceImplTest {

    @Autowired
    private OfficeService officeService;

    @Test
    @Transactional
    void getAllOfficesByPredicat() {
        OfficeListRequestDto requestDto = new OfficeListRequestDto();
        requestDto.setOrgId(1);

        List<OfficeListResponseDto> resultList = officeService.getAllOfficesByPredicat(requestDto);
        assertTrue(resultList.size() > 0);

        requestDto.setOrgId(1000);
        assertThrows(NoSuchObjectException.class, () -> officeService.getAllOfficesByPredicat(requestDto));
    }

    @Test
    @Transactional
    void getOfficeById() {
        this.getOfficeByIdFromRepository();
    }

    @Test
    @Transactional
    void updateOffice() {
        OfficeUpdateRequestDto requestDto = new OfficeUpdateRequestDto();
        requestDto.setId(1);
        requestDto.setName("update name");
        requestDto.setAddress("update address");
        requestDto.setPhone("99999999999");
        requestDto.setIsActive(false);

        officeService.updateOffice(requestDto);
        Office updatedOffice = officeService.getOfficeByIdFromRepository(1);

        assertNotNull(updatedOffice);
        assertEquals(requestDto.getName(), updatedOffice.getName());
        assertEquals(requestDto.getId(), updatedOffice.getId());
        assertEquals(requestDto.getAddress(), updatedOffice.getAddress());
        assertEquals(requestDto.getPhone(), updatedOffice.getPhone());
        assertEquals(requestDto.getIsActive(), updatedOffice.getIsActive());
    }

    @Test
    @Transactional
    void saveOffice() {
        OfficeSaveRequestDto requestDto = new OfficeSaveRequestDto();
        requestDto.setOrgId(1);
        requestDto.setName("save name");
        requestDto.setAddress("save address");
        requestDto.setPhone("99999999999");
        requestDto.setIsActive(false);

        assertThrows(NoSuchObjectException.class, () -> officeService.getOfficeByIdFromRepository(3));

        officeService.saveOffice(requestDto);
        Office savedOffice = officeService.getOfficeByIdFromRepository(3);

        assertNotNull(savedOffice);
        assertEquals(requestDto.getName(), savedOffice.getName());
        assertEquals(requestDto.getOrgId(), savedOffice.getOrganization().getId());
        assertEquals(requestDto.getAddress(), savedOffice.getAddress());
        assertEquals(requestDto.getPhone(), savedOffice.getPhone());
        assertEquals(requestDto.getIsActive(), savedOffice.getIsActive());
    }

    @Test
    @Transactional
    void getOfficeByIdFromRepository() {
        Office office = officeService.getOfficeByIdFromRepository(1);
        assertEquals(office.getId(), 1);
        assertNotNull(office);
    }
}