package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.TEST_OFFICE_ID;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.assertOfficesEquals;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getOfficeForUpdate;
import static com.gimaletdinov.exampleProject.dao.OfficeTestHelper.getPopulateOffice;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.TEST_ORG_ID;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.assertOrganizationsEquals;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OfficeRepositoryImplTest {

    private Office newTestOffice = getPopulateOffice();

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Test
    @Transactional
    void getAllOfficesByPredicat() {
        Organization organization = organizationRepository.getOrganizationById(TEST_ORG_ID);
        newTestOffice.setOrganization(organization);

        List<Office> officesFtomBD = officeRepository.getAllOfficesByPredicat(newTestOffice);
        assertFalse(officesFtomBD.isEmpty());
        assertTrue(officesFtomBD.size() == 1);
        assertOfficesEquals(newTestOffice, officesFtomBD.get(0));
    }

    @Test
    @Transactional
    void getOfficeById() {
        Office officeFromBD = officeRepository.getOfficeById(TEST_OFFICE_ID);
        assertNotNull(officeFromBD);
        assertOfficesEquals(newTestOffice, officeFromBD);
    }

    @Test
    @Transactional
    void updateOffice() {
        Office officeFromBD = officeRepository.getOfficeById(TEST_OFFICE_ID);
        Office officeForUpdate = getOfficeForUpdate(officeFromBD);

        officeRepository.updateOffice(officeForUpdate);
        Office updatedOffice = officeRepository.getOfficeById(TEST_OFFICE_ID);

        assertNotNull(updatedOffice);
        assertOfficesEquals(officeForUpdate, updatedOffice);
    }

    @Test
    @Transactional
    void saveOffice() {
        Organization organization = organizationRepository.getOrganizationById(TEST_ORG_ID);
        newTestOffice.setOrganization(organization);

        officeRepository.saveOffice(newTestOffice);
        Office savedOffice = officeRepository.getOfficeById(TEST_OFFICE_ID + 1);

        assertNotNull(savedOffice);
        assertOfficesEquals(newTestOffice, savedOffice);
    }
}