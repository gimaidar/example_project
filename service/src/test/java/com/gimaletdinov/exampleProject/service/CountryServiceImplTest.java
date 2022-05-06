package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.CountryRepository;
import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.mapper.CountryMapperImpl;
import com.gimaletdinov.exampleProject.model.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.helper.CountryDtoTestHelper.TEST_COUNTRY_CODE;
import static com.gimaletdinov.exampleProject.helper.CountryDtoTestHelper.getPopulateCountry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CountryServiceImpl.class, CountryMapperImpl.class})
class CountryServiceImplTest {

    private Country newTestCountry = getPopulateCountry();

    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @Test
    @DisplayName("Тест: getAllCountries (Найти все страны)")
    void getAllCountries() {
        //Given
        Country newCountry = getPopulateCountry();
        List<Country> countryList = new ArrayList<>();
        countryList.add(newCountry);
        when(countryRepository.findAll()).thenReturn(countryList);

        //When
        List<CountryListResponseDto> resultList = countryService.getAllCountries();

        //Then
        assertThat(resultList).hasSizeGreaterThan(0);
    }

    @Test
    @DisplayName("Тест: getCountryByCode (Найти страну по коду)")
    void getCountryByCode() {
        //Given
        when(countryRepository.findByCode(TEST_COUNTRY_CODE)).thenReturn(Optional.ofNullable(newTestCountry));

        //When
        Country countryFromService = countryService.getCountryByCode(TEST_COUNTRY_CODE);

        //Then
        assertThat(countryFromService).isNotNull();
        assertThat(countryFromService).isEqualTo(newTestCountry);
    }
}