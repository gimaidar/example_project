package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gimaletdinov.exampleProject.service.CountryService;

import java.util.List;

/**
 *Контроллер для сущности Страна
 */
@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Метод возвращает список стран, найденных в БД по определенному предикату
     * @return список стран, найденных в БД по определенному придикату
     */
    @GetMapping()
    public List<CountryListResponseDto> getAllOrganizationsByPredicat() {
        return countryService.getAllCountries();
    }
}
