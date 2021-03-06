package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.mapper.OrganizationMapper;
import com.gimaletdinov.exampleProject.mapper.OrganizationMapperImpl;
import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.TEST_ORG_ID;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganization;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationListRequestDto;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationSaveRequestDto;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {OrganizationServiceImpl.class, OrganizationMapperImpl.class})
class OrganizationServiceImplTest {

    private Organization newTestOrganization = getPopulateOrganization();

    @MockBean
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationService organizationService;


    @Test
    @DisplayName("????????: getAllOrganizationsByPredicat (?????????? ?????????????????????? ???? ??????????????????)")
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
        assertThat(organizationListResponseDtoFromService).isNotEmpty();
        assertThat(organizationListResponseDtoFromService.get(0).getId()).isEqualTo(newTestOrganization.getId());
        assertThat(organizationListResponseDtoFromService.get(0).getName()).isEqualTo(newTestOrganization.getName());
        assertThat(organizationListResponseDtoFromService.get(0).getIsActive()).isEqualTo(newTestOrganization.getIsActive());
    }

    @Test
    @DisplayName("????????: getOrganizationByIdFromRepository (?????????? ?????????????????????? ???? id)")
    void getOrganizationById() {
        getOrganizationByIdFromRepository();
    }

    @Test
    @DisplayName("????????: updateOrganization (???????????????? ???????????? ??????????????????????)")
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
    @DisplayName("????????: save (?????????????????? ?????????? ??????????????????????)")
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
    @DisplayName("????????: getOrganizationByIdFromRepository (?????????? ?????????????????????? ???? id ???? ??????????????????????)")
    void getOrganizationByIdFromRepository() {
        //Given
        when(organizationRepository.findById(TEST_ORG_ID)).thenReturn(Optional.ofNullable(newTestOrganization));

        //When
        Organization organizationFromService = organizationService.getOrganizationByIdFromRepository(TEST_ORG_ID);

        //Then
        verify(organizationRepository).findById(TEST_ORG_ID);
        assertThat(organizationFromService).isNotNull();
        assertThat(organizationFromService).isEqualTo(newTestOrganization);
    }
}