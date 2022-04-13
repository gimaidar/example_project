package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.DocumentTypeListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.DocumentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class CountryServiceImplTest {

    @Autowired
    private CountryService countryService;

    @Test
    @Transactional
    void getAllCountries() {
        List<CountryListResponseDto> resultList = countryService.getAllCountries();
        assertTrue(resultList.size() > 0);
    }

    @Test
    @Transactional
    void getCountryById() {
        Country country = countryService.getCountryById(643);
        assertEquals(country.getId(), 643);
        assertNotNull(country);
    }
}