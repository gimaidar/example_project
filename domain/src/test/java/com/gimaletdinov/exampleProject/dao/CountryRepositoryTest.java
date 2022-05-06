package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gimaletdinov.exampleProject.helper.CountryTestHelper.getPopulateCountry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    @DisplayName("Тест: findAll (Найти все страны)")
    void findAll(){
        List<Country>  countriesFromBd = countryRepository.findAll();
        assertThat(countriesFromBd).isNotEmpty();
        assertThat(countriesFromBd).hasSize(1);
        assertThat(countriesFromBd.get(0)).isEqualTo(testCountryInBD);
    }

    @Test
    @Transactional
    @DisplayName("Тест: findByCode (Найти страну по коду)")
    void findByCode(){
        Country countryFromBd = countryRepository.findByCode(testCountryInBD.getCode()).get();
        assertThat(countryFromBd).isNotNull();
        assertThat(countryFromBd).isEqualTo(testCountryInBD);
    }

}