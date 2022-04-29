package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.mapper.OrganizationMapper;
import com.gimaletdinov.exampleProject.model.Organization;
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

import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.TEST_ORG_ID;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganization;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationListRequestDto;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationSaveRequestDto;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationUpdateRequestDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {OrganizationServiceImplTest.class, OrganizationService.class, OrganizationMapper.class})
class OrganizationServiceImplTest {

    private Organization newTestOrganization = getPopulateOrganization();

    @MockBean
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationService organizationService = new OrganizationServiceImpl(organizationRepository, organizationMapper);


    @Test
    @Transactional
    void getAllOrganizationsByPredicat() {
        List<Organization> testOrganizationList = new ArrayList<>();
        testOrganizationList.add(newTestOrganization);
        //Given
        OrganizationListRequestDto organizationListRequestDto = getPopulateOrganizationListRequestDto();
        when(organizationRepository.findAll((Specification) any())).thenReturn(testOrganizationList);

        //When
        List<OrganizationListResponseDto> organizationListResponseDtoFromService = organizationService.getAllOrganizationsByPredicat(organizationListRequestDto);

        //Then
        verify(organizationRepository).findAll((Specification) any());
        assertFalse(organizationListResponseDtoFromService.isEmpty());
        assertEquals(newTestOrganization.getId() , organizationListResponseDtoFromService.get(0).getId());
    }

    @Test
    void getOrganizationById() {
        getOrganizationByIdFromRepository();
    }

    @Test
    @Transactional
    void updateOrganization() {
        //Given
        Organization organizationForUpdate = getPopulateOrganization();
        when(organizationRepository.findById(TEST_ORG_ID)).thenReturn(Optional.ofNullable(organizationForUpdate));

        OrganizationUpdateRequestDto organizationUpdateRequestDto = getPopulateOrganizationUpdateRequestDto();
        organizationMapper.updateModel(organizationUpdateRequestDto, organizationForUpdate);
        when(organizationRepository.save(organizationForUpdate)).thenReturn(organizationForUpdate);

        //When
        organizationService.updateOrganization(organizationUpdateRequestDto);

        //Then
        verify(organizationRepository).save(organizationForUpdate);
    }

    @Test
    @Transactional
    void saveOrganization() {
        //Given
        OrganizationSaveRequestDto organizationSaveRequestDto = getPopulateOrganizationSaveRequestDto();
        when(organizationRepository.save(any())).thenReturn(newTestOrganization);

        //When
        organizationService.saveOrganization(organizationSaveRequestDto);

        //Then
        verify(organizationRepository).save(organizationMapper.toModel(organizationSaveRequestDto));
    }

    @Test
    @Transactional
    void getOrganizationByIdFromRepository() {
        //Given
        when(organizationRepository.findById(TEST_ORG_ID)).thenReturn(Optional.ofNullable(newTestOrganization));

        //When
        Organization organizationFromService = organizationService.getOrganizationByIdFromRepository(TEST_ORG_ID);

        //Then
        verify(organizationRepository).findById(TEST_ORG_ID);
        assertNotNull(organizationFromService);
//        assertOrganizationsEquals(newTestOrganization, organizationFromService);
    }
}