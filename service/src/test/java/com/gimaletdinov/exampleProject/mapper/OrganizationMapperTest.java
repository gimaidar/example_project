package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganization;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationSaveRequestDto;
import static com.gimaletdinov.exampleProject.helper.OrganizationDtoTestHelper.getPopulateOrganizationUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(classes = OrganizationMapperImpl.class)
class OrganizationMapperTest {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Test
    @DisplayName("Тест: toModel (Маппинг из OrganizationSaveRequestDto -> Organization)")
    void toModel() {
        OrganizationSaveRequestDto organizationSaveRequestDto = getPopulateOrganizationSaveRequestDto();

        Organization organization = organizationMapper.toModel(organizationSaveRequestDto);

        assertAll(
                () -> assertThat(organization.getName()).isEqualTo(organizationSaveRequestDto.getName()),
                () -> assertThat(organization.getFullName()).isEqualTo(organizationSaveRequestDto.getFullName()),
                () -> assertThat(organization.getInn()).isEqualTo(organizationSaveRequestDto.getInn()),
                () -> assertThat(organization.getKpp()).isEqualTo(organizationSaveRequestDto.getKpp()),
                () -> assertThat(organization.getAddress()).isEqualTo(organizationSaveRequestDto.getAddress()),
                () -> assertThat(organization.getPhone()).isEqualTo(organizationSaveRequestDto.getPhone()),
                () -> assertThat(organization.getIsActive()).isEqualTo(organizationSaveRequestDto.getIsActive())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDto (Маппинг из Organization -> OrganizationResponseDto)")
    void toResponseDto() {
        Organization organization = getPopulateOrganization();
        OrganizationResponseDto organizationResponseDto = organizationMapper.toResponseDto(organization);
        assertAll(
                () -> assertThat(organizationResponseDto.getId()).isEqualTo(organization.getId()),
                () -> assertThat(organizationResponseDto.getName()).isEqualTo(organization.getName()),
                () -> assertThat(organizationResponseDto.getFullName()).isEqualTo(organization.getFullName()),
                () -> assertThat(organizationResponseDto.getInn()).isEqualTo(organization.getInn()),
                () -> assertThat(organizationResponseDto.getKpp()).isEqualTo(organization.getKpp()),
                () -> assertThat(organizationResponseDto.getAddress()).isEqualTo(organization.getAddress()),
                () -> assertThat(organizationResponseDto.getPhone()).isEqualTo(organization.getPhone()),
                () -> assertThat(organizationResponseDto.getIsActive()).isEqualTo(organization.getIsActive())
        );
    }

    @Test
    @DisplayName("Тест: updateModel (Маппинг из OrganizationUpdateRequestDto -> Organization)")
    void updateModel() {
        Organization organization = getPopulateOrganization();
        OrganizationUpdateRequestDto updateRequestDto = getPopulateOrganizationUpdateRequestDto();
        organizationMapper.updateModel(updateRequestDto, organization);
        assertAll(
                () -> assertThat(organization.getId()).isEqualTo(updateRequestDto.getId()),
                () -> assertThat(organization.getName()).isEqualTo(updateRequestDto.getName()),
                () -> assertThat(organization.getFullName()).isEqualTo(updateRequestDto.getFullName()),
                () -> assertThat(organization.getInn()).isEqualTo(updateRequestDto.getInn()),
                () -> assertThat(organization.getKpp()).isEqualTo(updateRequestDto.getKpp()),
                () -> assertThat(organization.getAddress()).isEqualTo(updateRequestDto.getAddress()),
                () -> assertThat(organization.getPhone()).isEqualTo(updateRequestDto.getPhone()),
                () -> assertThat(organization.getIsActive()).isEqualTo(updateRequestDto.getIsActive())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDtoList (Маппинг из List<Organization> ->  List<OrganizationListResponseDto>)")
    void toResponseDtoList() {
        Organization organization = getPopulateOrganization();
        List<Organization> organizationList = new ArrayList<>();
        organizationList.add(organization);
        
        List<OrganizationListResponseDto> organizationListResponseDtoList = organizationMapper.toResponseDtoList(organizationList);
        OrganizationListResponseDto organizationListResponseDto = organizationListResponseDtoList.get(0);

        assertAll(
                () -> assertThat(organizationListResponseDto.getId()).isEqualTo(organization.getId()),
                () -> assertThat(organizationListResponseDto.getName()).isEqualTo(organization.getName()),
                () -> assertThat(organizationListResponseDto.getIsActive()).isEqualTo(organization.getIsActive())
        );
    }
}