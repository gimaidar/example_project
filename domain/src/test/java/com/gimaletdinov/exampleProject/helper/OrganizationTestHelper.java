package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.model.Organization;


public class OrganizationTestHelper {
    public final static Integer TEST_ORG_ID = 1;
    public final static String TEST_ORG_NAME = "test_org_name";
    public final static String TEST_ORG_FULL_NAME = "test_org_full_name";
    public final static String TEST_ORG_INN = "9999999999";
    public final static String TEST_ORG_KPP = "8888888888";
    public final static String TEST_ORG_ADDRESS = "test_org_address";
    public final static String TEST_ORG_PHONE = "99999999999";
    public final static boolean TEST_ORG_IS_ACTIVE = true;

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
}
