package com.gimaletdinov.exampleProject.Helper;

import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrganizationTestHelper {
    // Проверь файл data.sql данные должны совпадать
    public final static Integer TEST_ORG_ID = 1;
    private final static String TEST_ORG_NAME = "test_org_name";
    private final static String TEST_ORG_FULL_NAME = "test_org_full_name";
    private final static String TEST_ORG_INN = "9999999999";
    private final static String TEST_ORG_KPP = "8888888888";
    private final static String TEST_ORG_ADDRESS = "test_org_address";
    private final static String TEST_ORG_PHONE = "99999999999";
    private final static boolean TEST_ORG_IS_ACTIVE = true;

    private final static String TEST_UPDATED_ORG_NAME = "test_updated_org_name";
    private final static String TEST_UPDATED_ORG_FULL_NAME = "test_updated_org_full_name";
    private final static String TEST_UPDATED_ORG_INN = "1111111111";
    private final static String TEST_UPDATED_ORG_KPP = "2222222222";
    private final static String TEST_UPDATED_ORG_ADDRESS = "test_updated_org_address";
    private final static String TEST_UPDATED_ORG_PHONE = "33333333333";
    private final static boolean TEST_UPDATED_ORG_IS_ACTIVE = true;

    public static Organization getPopulateOrganization() {
        Organization organization = new Organization();
        organization.setId(TEST_ORG_ID);
        organization.setName(TEST_ORG_NAME);
        organization.setFullName(TEST_ORG_FULL_NAME);
        organization.setInn(TEST_ORG_INN);
        organization.setKpp(TEST_ORG_KPP);
        organization.setAddress(TEST_ORG_ADDRESS);
        organization.setPhone(TEST_ORG_PHONE);
        organization.setIsActive(TEST_ORG_IS_ACTIVE);
        return organization;
    }

    public static Organization getOrganizationForUpdate(Organization organization) {
        organization.setName(TEST_UPDATED_ORG_NAME);
        organization.setFullName(TEST_UPDATED_ORG_FULL_NAME);
        organization.setInn(TEST_UPDATED_ORG_INN);
        organization.setKpp(TEST_UPDATED_ORG_KPP);
        organization.setAddress(TEST_UPDATED_ORG_ADDRESS);
        organization.setPhone(TEST_UPDATED_ORG_PHONE);
        organization.setIsActive(TEST_UPDATED_ORG_IS_ACTIVE);
        return organization;
    }
    
    public static void assertOrganizationsEquals(Organization expected, Organization actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getFullName(), actual.getFullName());
        assertEquals(expected.getInn(), actual.getInn());
        assertEquals(expected.getKpp(), actual.getKpp());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getPhone(), actual.getPhone());
        assertEquals(expected.getIsActive(), actual.getIsActive());
    }

    public static OrganizationListRequestDto getPopulateOrganizationListRequestDto() {
        OrganizationListRequestDto organizationListRequestDto = new OrganizationListRequestDto();
        organizationListRequestDto.setName(TEST_ORG_NAME);
        organizationListRequestDto.setInn(TEST_ORG_INN);
        organizationListRequestDto.setIsActive(TEST_ORG_IS_ACTIVE);
        return organizationListRequestDto;
    }

    public static OrganizationUpdateRequestDto getPopulateOrganizationUpdateRequestDto() {
        OrganizationUpdateRequestDto organizationUpdateRequestDto = new OrganizationUpdateRequestDto();
        organizationUpdateRequestDto.setId(TEST_ORG_ID);
        organizationUpdateRequestDto.setName(TEST_ORG_NAME);
        organizationUpdateRequestDto.setFullName(TEST_ORG_FULL_NAME);
        organizationUpdateRequestDto.setInn(TEST_ORG_INN);
        organizationUpdateRequestDto.setKpp(TEST_ORG_KPP);
        organizationUpdateRequestDto.setAddress(TEST_ORG_ADDRESS);
        organizationUpdateRequestDto.setPhone(TEST_ORG_PHONE);
        organizationUpdateRequestDto.setIsActive(TEST_ORG_IS_ACTIVE);
        return organizationUpdateRequestDto;
    }

    public static OrganizationSaveRequestDto getPopulateOrganizationSaveRequestDto() {
        OrganizationSaveRequestDto organizationSaveRequestDto = new OrganizationSaveRequestDto();
        organizationSaveRequestDto.setName(TEST_ORG_NAME);
        organizationSaveRequestDto.setFullName(TEST_ORG_FULL_NAME);
        organizationSaveRequestDto.setInn(TEST_ORG_INN);
        organizationSaveRequestDto.setKpp(TEST_ORG_KPP);
        organizationSaveRequestDto.setAddress(TEST_ORG_ADDRESS);
        organizationSaveRequestDto.setPhone(TEST_ORG_PHONE);
        organizationSaveRequestDto.setIsActive(TEST_ORG_IS_ACTIVE);
        return organizationSaveRequestDto;
    }

    public static OrganizationResponseDto getPopulateOrganizationResponseDto() {
        OrganizationResponseDto organizationResponseDto = new OrganizationResponseDto();
        organizationResponseDto.setId(TEST_ORG_ID);
        organizationResponseDto.setName(TEST_ORG_NAME);
        organizationResponseDto.setFullName(TEST_ORG_FULL_NAME);
        organizationResponseDto.setInn(TEST_ORG_INN);
        organizationResponseDto.setKpp(TEST_ORG_KPP);
        organizationResponseDto.setAddress(TEST_ORG_ADDRESS);
        organizationResponseDto.setPhone(TEST_ORG_PHONE);
        organizationResponseDto.setIsActive(TEST_ORG_IS_ACTIVE);
        return organizationResponseDto;
    }

    public static List<OrganizationListResponseDto> getPopulateOrganizationListResponseDtoList() {
        OrganizationListResponseDto organizationListResponseDto = new OrganizationListResponseDto();
        organizationListResponseDto.setId(TEST_ORG_ID);
        organizationListResponseDto.setName(TEST_ORG_NAME);
        organizationListResponseDto.setIsActive(TEST_ORG_IS_ACTIVE);
        List<OrganizationListResponseDto> organizationListResponseDtoList = new ArrayList<>();
        organizationListResponseDtoList.add(organizationListResponseDto);
        return organizationListResponseDtoList;
    }
}
