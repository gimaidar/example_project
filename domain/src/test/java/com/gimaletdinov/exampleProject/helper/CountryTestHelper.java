package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.model.Country;


public class CountryTestHelper {

    public final static Integer TEST_COUNTRY_ID= 1;
    public final static String TEST_COUNTRY_CODE = "643";
    public final static String TEST_COUNTRY_NAME = "Российская Федерация";

    public static Country getPopulateCountry(){
        Country country = new Country();
        country.setId(TEST_COUNTRY_ID);
        country.setCode(TEST_COUNTRY_CODE);
        country.setName(TEST_COUNTRY_NAME);
        return country;
    }
}
