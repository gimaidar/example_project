package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.model.mapper.OrganizationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.TEST_ORG_ID;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.assertOrganizationsEquals;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganization;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganizationListRequestDto;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganizationSaveRequestDto;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganizationUpdateRequestDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
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
        when(organizationRepository.getAllOrganizationsByPredicat(organizationMapper.toModel(organizationListRequestDto))).thenReturn(testOrganizationList);

        //When
        List<OrganizationListResponseDto> organizationListResponseDtoFromService = organizationService.getAllOrganizationsByPredicat(organizationListRequestDto);

        //Then
        verify(organizationRepository).getAllOrganizationsByPredicat(organizationMapper.toModel(organizationListRequestDto));
        assertFalse(organizationListResponseDtoFromService.isEmpty());
        assertEquals(newTestOrganization.getId() , organizationListResponseDtoFromService.get(0).getId());
    }

    @Test
    void getOrganizationById() {
        this.getOrganizationByIdFromRepository();
    }

    @Test
    @Transactional
    void updateOrganization() {
        //Given
        OrganizationUpdateRequestDto organizationUpdateRequestDto = getPopulateOrganizationUpdateRequestDto();
        Organization organization = getPopulateOrganization();
        when(organizationRepository.getOrganizationById(TEST_ORG_ID)).thenReturn(organization);

        //When
        organizationService.updateOrganization(organizationUpdateRequestDto);

        //Then
        verify(organizationRepository).updateOrganization(organization);
    }

    @Test
    @Transactional
    void saveOrganization() {
        //Given
        OrganizationSaveRequestDto organizationSaveRequestDto = getPopulateOrganizationSaveRequestDto();

        //When
        organizationService.saveOrganization(organizationSaveRequestDto);

        //Then
        verify(organizationRepository).saveOrganization(organizationMapper.toModel(organizationSaveRequestDto));
    }

    @Test
    @Transactional
    void getOrganizationByIdFromRepository() {
        //Given
        when(organizationRepository.getOrganizationById(TEST_ORG_ID)).thenReturn(newTestOrganization);

        //When
        Organization organizationFromService = organizationService.getOrganizationByIdFromRepository(TEST_ORG_ID);

        //Then
        verify(organizationRepository).getOrganizationById(TEST_ORG_ID);
        assertNotNull(organizationFromService);
        assertOrganizationsEquals(newTestOrganization, organizationFromService);
    }
}