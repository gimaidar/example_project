package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.CountryRepository;
import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.TEST_COUNTRY_ID;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateCountry;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class CountryServiceImplTest {

    private Country newTestCountry = getPopulateCountry();

    @MockBean
    private CountryRepository countryRepository;

    @Autowired
    private CountryService countryService;

    @Test
    @Transactional
    void getAllCountries() {
        //Given
        Country newCountry = getPopulateCountry();
        List<Country> countryList = new ArrayList<>();
        countryList.add(newCountry);
        when(countryRepository.findAll()).thenReturn(countryList);

        //When
        List<CountryListResponseDto> resultList = countryService.getAllCountries();

        //Then
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getCountryById() {
        //Given
        when(countryRepository.findById(TEST_COUNTRY_ID)).thenReturn(Optional.ofNullable(newTestCountry));

        //When
        Country countryFromService = countryService.getCountryById(TEST_COUNTRY_ID);

        //Then
        assertEquals(TEST_COUNTRY_ID, countryFromService.getId());
        assertNotNull(countryFromService);
    }
}