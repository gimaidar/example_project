package com.gimaletdinov.exampleProject.helper;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryDtoTestHelper {

    public final static String TEST_COUNTRY_CODE = "643";
    public final static String TEST_COUNTRY_NAME = "Российская Федерация";

    public static Country getPopulateCountry(){
        Country country = new Country();
        country.setCode(TEST_COUNTRY_CODE);
        country.setName(TEST_COUNTRY_NAME);
        return country;
    }

    public static List<CountryListResponseDto> getPopulateCountryListResponseDto(){
        CountryListResponseDto countryListResponseDto = new CountryListResponseDto();
        countryListResponseDto.setCode(TEST_COUNTRY_CODE);
        countryListResponseDto.setName(TEST_COUNTRY_NAME);

        List<CountryListResponseDto> countryListResponseDtoList = new ArrayList<>();
        countryListResponseDtoList.add(countryListResponseDto);
        return countryListResponseDtoList;
    }
}
