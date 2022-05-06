package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;

import static com.gimaletdinov.exampleProject.helper.OrganizationTestHelper.getPopulateOrganization;


public class OfficeTestHelper {
    public final static Integer TEST_OFFICE_ID = 1;
    public final static String TEST_OFFICE_NAME = "test_office_name";
    private final static String TEST_OFFICE_ADDRESS = "test_office_address";
    public final static String TEST_OFFICE_PHONE = "99999999999";
    public final static boolean TEST_OFFICE_IS_ACTIVE = true;

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
}
