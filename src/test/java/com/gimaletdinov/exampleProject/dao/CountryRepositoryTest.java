package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.assertUsersEquals;
import static com.gimaletdinov.exampleProject.Helper.UserTestHelper.getPopulateCountry;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class CountryRepositoryTest {

    private Country testCountryInBD;

    @Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    void saveTestCountryInBD(){
        testCountryInBD = countryRepository.save(getPopulateCountry());
    }

    @Test
    @Transactional
    void findAll(){
        List<Country>  countriesFromBd = countryRepository.findAll();
        assertFalse(countriesFromBd.isEmpty());
        assertTrue(countriesFromBd.size() == 1);
        assertEquals(testCountryInBD, countriesFromBd.get(0));
    }

    @Test
    @Transactional
    void findById(){
        Country countryFromBd = countryRepository.findById(testCountryInBD.getId()).get();
        assertNotNull(countryFromBd);
        assertEquals(testCountryInBD, countryFromBd);
    }

}