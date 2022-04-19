package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.TEST_ORG_ID;
import static com.gimaletdinov.exampleProject.dao.OrganizationTestHelper.getPopulateOrganization;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OfficeTestHelper {
    // Проверь файл data.sql данные должны совпадать
    public final static Integer TEST_OFFICE_ID = 1;
    private final static String TEST_OFFICE_NAME = "test_office_name";
    private final static String TEST_OFFICE_ADDRESS = "test_office_address";
    private final static String TEST_OFFICE_PHONE = "99999999999";
    private final static boolean TEST_OFFICE_IS_ACTIVE = true;

    public final static Integer TEST_UPDATED_OFFICE_ID = 1;
    private final static String TEST_UPDATED_OFFICE_NAME = "test_updated_office_name";
    private final static String TEST_UPDATED_OFFICE_ADDRESS = "test_updated_office_address";
    private final static String TEST_UPDATED_OFFICE_PHONE = "88888888888";
    private final static boolean TEST_UPDATED_OFFICE_IS_ACTIVE = true;

    public static Office getPopulateOffice() {
        Office office = new Office();
        office.setId(TEST_OFFICE_ID);
        office.setName(TEST_OFFICE_NAME);
        office.setAddress(TEST_OFFICE_ADDRESS);
        office.setPhone(TEST_OFFICE_PHONE);
        office.setIsActive(TEST_OFFICE_IS_ACTIVE);

        Organization organization = getPopulateOrganization();
        office.setOrganization(organization);
        return office;
    }

    public static Office getOfficeForUpdate(Office office) {
        office.setId(TEST_UPDATED_OFFICE_ID);
        office.setName(TEST_UPDATED_OFFICE_NAME);
        office.setAddress(TEST_UPDATED_OFFICE_ADDRESS);
        office.setPhone(TEST_UPDATED_OFFICE_PHONE);
        office.setIsActive(TEST_UPDATED_OFFICE_IS_ACTIVE);

        return office;
    }
    
    public static void assertOfficesEquals(Office expected, Office actual){
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAddress(), actual.getAddress());
        assertEquals(expected.getPhone(), actual.getPhone());
        assertEquals(expected.getIsActive(), actual.getIsActive());
    }

    public static OfficeListRequestDto getPopulateOfficeListRequestDto() {
        OfficeListRequestDto officeListRequestDto = new OfficeListRequestDto();
        officeListRequestDto.setOrgId(TEST_ORG_ID);
        officeListRequestDto.setName(TEST_OFFICE_NAME);
        officeListRequestDto.setPhone(TEST_OFFICE_PHONE);
        officeListRequestDto.setIsActive(TEST_OFFICE_IS_ACTIVE);
        return officeListRequestDto;
    }

    public static OfficeUpdateRequestDto getPopulateOfficeUpdateRequestDto() {
        OfficeUpdateRequestDto officeUpdateRequestDto = new OfficeUpdateRequestDto();
        officeUpdateRequestDto.setId(TEST_OFFICE_ID);
        officeUpdateRequestDto.setName(TEST_OFFICE_NAME);
        officeUpdateRequestDto.setAddress(TEST_OFFICE_ADDRESS);
        officeUpdateRequestDto.setPhone(TEST_OFFICE_PHONE);
        officeUpdateRequestDto.setIsActive(TEST_OFFICE_IS_ACTIVE);
        return officeUpdateRequestDto;
    }

    public static OfficeSaveRequestDto getPopulateOfficeSaveRequestDto() {
        OfficeSaveRequestDto officeSaveRequestDto = new OfficeSaveRequestDto();
        officeSaveRequestDto.setOrgId(TEST_ORG_ID);
        officeSaveRequestDto.setName(TEST_OFFICE_NAME);
        officeSaveRequestDto.setAddress(TEST_OFFICE_ADDRESS);
        officeSaveRequestDto.setPhone(TEST_OFFICE_PHONE);
        officeSaveRequestDto.setIsActive(TEST_OFFICE_IS_ACTIVE);
        return officeSaveRequestDto;
    }

    public static List<OfficeListResponseDto> getPopulateOfficeListResponseDtoList() {
        OfficeListResponseDto officeListResponseDto = new OfficeListResponseDto();
        officeListResponseDto.setId(TEST_OFFICE_ID);
        officeListResponseDto.setName(TEST_OFFICE_NAME);
        officeListResponseDto.setIsActive(TEST_OFFICE_IS_ACTIVE);

        List<OfficeListResponseDto> officeListResponseDtoList = new ArrayList<>();
        officeListResponseDtoList.add(officeListResponseDto);
        return officeListResponseDtoList;
    }

    public static OfficeResponseDto getPopulateOfficeResponseDto() {
        OfficeResponseDto officeResponseDto = new OfficeResponseDto();
        officeResponseDto.setId(TEST_OFFICE_ID);
        officeResponseDto.setName(TEST_OFFICE_NAME);
        officeResponseDto.setAddress(TEST_OFFICE_ADDRESS);
        officeResponseDto.setPhone(TEST_OFFICE_PHONE);
        officeResponseDto.setIsActive(TEST_OFFICE_IS_ACTIVE);
        return officeResponseDto;
    }
}
