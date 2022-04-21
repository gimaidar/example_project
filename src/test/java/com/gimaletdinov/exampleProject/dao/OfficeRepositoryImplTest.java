package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.model.Office;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOfficeListRequestDto;
import static com.gimaletdinov.exampleProject.dao.OfficeSpecification.officeSpecification;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.assertOfficesEquals;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getOfficeForUpdate;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOffice;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OfficeRepositoryImplTest {

    private Office testOfficeInBD;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @BeforeEach
    void saveTestOfficeInBD(){
        testOfficeInBD = officeRepository.save(getPopulateOffice());
        System.out.println(testOfficeInBD);
    }

    @Test
    @Transactional
    void getAllOfficesByPredicat() {
        OfficeListRequestDto officeListRequestDto = getPopulateOfficeListRequestDto();
        List<Office> officesFtomBD = officeRepository.findAll(officeSpecification(officeListRequestDto));
        assertFalse(officesFtomBD.isEmpty());
        assertTrue(officesFtomBD.size() == 1);
        assertOfficesEquals(testOfficeInBD, officesFtomBD.get(0));
    }

    @Test
    @Transactional
    void getOfficeById() {
        Office officeFromBD = officeRepository.findById(testOfficeInBD.getId()).get();
        assertNotNull(officeFromBD);
        assertOfficesEquals(testOfficeInBD, officeFromBD);
    }

    @Test
    @Transactional
    void updateOffice() {
        Office officeForUpdate = getOfficeForUpdate(testOfficeInBD);

        Office updatedOffice = officeRepository.save(officeForUpdate);

        assertNotNull(updatedOffice);
        assertOfficesEquals(officeForUpdate, updatedOffice);
    }

    @Test
    @Transactional
    void saveOffice() {
        Office officeForSave = getPopulateOffice();

        Office savedOffice = officeRepository.save(officeForSave);

        assertNotNull(savedOffice);
        assertOfficesEquals(officeForSave, savedOffice);
    }
}