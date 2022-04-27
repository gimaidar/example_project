package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OfficeRepository;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.mapper.OfficeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.TEST_OFFICE_ID;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOffice;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOfficeListRequestDto;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOfficeSaveRequestDto;
import static com.gimaletdinov.exampleProject.Helper.OfficeTestHelper.getPopulateOfficeUpdateRequestDto;
import static com.gimaletdinov.exampleProject.Helper.OrganizationTestHelper.getPopulateOrganization;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class OfficeServiceImplTest {

    private Office newTestOffice = getPopulateOffice();

    private Organization newTestOrganization = getPopulateOrganization();

    @MockBean
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeMapper officeMapper;

    @MockBean
    private OrganizationService organizationService;

    @Autowired
    private OfficeService officeService = new OfficeServiceImpl(officeRepository, organizationService, officeMapper);

    @Test
    @Transactional
    void getAllOfficesByPredicat() {
        //Given
        List<Office> testOfficeList = new ArrayList<>();
        testOfficeList.add(newTestOffice);

        OfficeListRequestDto officeListRequestDto = getPopulateOfficeListRequestDto();
        when(officeRepository.findAll((Specification) any())).thenReturn(testOfficeList);
        when(organizationService.getOrganizationByIdFromRepository(officeListRequestDto.getOrgId())).thenReturn(newTestOrganization);

        //When
        List<OfficeListResponseDto> officeListResponseDtoFromRepository = officeService.getAllOfficesByPredicat(officeListRequestDto);

        //Then
        Office officeInService = officeMapper.toModel(officeListRequestDto);
        officeInService.setOrganization(newTestOrganization);
        verify(officeRepository).findAll((Specification) any());

        assertFalse(officeListResponseDtoFromRepository.isEmpty());
        assertEquals(newTestOffice.getId() , officeListResponseDtoFromRepository.get(0).getId());
    }

    @Test
    @Transactional
    void getOfficeById() {
        getOfficeByIdFromRepository();
    }

    @Test
    @Transactional
    void updateOffice() {
        OfficeUpdateRequestDto requestDto = getPopulateOfficeUpdateRequestDto();
        Office newOffice = getPopulateOffice();

        //Given
        when(officeRepository.findById(TEST_OFFICE_ID)).thenReturn(Optional.ofNullable(newOffice));

        //When
        officeService.updateOffice(requestDto);

        //Then
        officeMapper.updateModel(requestDto, newOffice);
        verify(officeRepository).save(newOffice);
    }

    @Test
    @Transactional
    void saveOffice() {
        OfficeSaveRequestDto officeSaveRequestDto = getPopulateOfficeSaveRequestDto();

        //Given
        when(organizationService.getOrganizationByIdFromRepository(officeSaveRequestDto.getOrgId())).thenReturn(newTestOrganization);
        when(officeRepository.save(newTestOffice)).thenReturn(newTestOffice);

        //When
        officeService.saveOffice(officeSaveRequestDto);

        //Then
        Office officeInService = officeMapper.toModel(officeSaveRequestDto);
        officeInService.setOrganization(newTestOrganization);
        verify(officeRepository).save(officeInService);
    }

    @Test
    @Transactional
    void getOfficeByIdFromRepository() {
        //Given
        when(officeRepository.findById(TEST_OFFICE_ID)).thenReturn(Optional.ofNullable(newTestOffice));

        //When
        Office officeFromService = officeService.getOfficeByIdFromRepository(TEST_OFFICE_ID);

        //Then
        verify(officeRepository).findById(TEST_OFFICE_ID);
        assertNotNull(officeFromService);
        assertEquals(officeFromService, officeFromService);
        }
}