package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OfficeRepositoryImplTest {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Test
    @Transactional
    void getAllOfficesByPredicat() {
        Organization organization = organizationRepository.getOrganizationById(1);
        
        Office office = new Office();
        office.setOrganization(organization);

        List<Office> resultList = officeRepository.getAllOfficesByPredicat(office);
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getOfficeById() {
        Office office = officeRepository.getOfficeById(1);
        assertEquals(office.getId(), 1);
        assertNotNull(office);
    }

    @Test
    @Transactional
    void updateOffice() {
        Office office = officeRepository.getOfficeById(1);
        office.setName("update name");
        office.setAddress("update address");
        office.setPhone("99999999999");
        office.setIsActive(false);

        officeRepository.updateOffice(office);
        Office updatedOffice = officeRepository.getOfficeById(1);

        assertNotNull(updatedOffice);
        assertEquals(office.getName(), updatedOffice.getName());
        assertEquals(office.getId(), updatedOffice.getId());
        assertEquals(office.getAddress(), updatedOffice.getAddress());
        assertEquals(office.getPhone(), updatedOffice.getPhone());
        assertEquals(office.getIsActive(), updatedOffice.getIsActive());
    }

    @Test
    @Transactional
    void saveOffice() {
        Organization organization = organizationRepository.getOrganizationById(1);

        Office office = new Office();
        office.setOrganization(organization);
        office.setName("save name");
        office.setAddress("save address");
        office.setPhone("99999999999");
        office.setIsActive(false);

        officeRepository.saveOffice(office);
        Office savedOffice = officeRepository.getOfficeById(3);

        assertNotNull(savedOffice);
        assertEquals(office.getName(), savedOffice.getName());
        assertEquals(office.getOrganization(), savedOffice.getOrganization());
        assertEquals(office.getAddress(), savedOffice.getAddress());
        assertEquals(office.getPhone(), savedOffice.getPhone());
        assertEquals(office.getIsActive(), savedOffice.getIsActive());
    }
}