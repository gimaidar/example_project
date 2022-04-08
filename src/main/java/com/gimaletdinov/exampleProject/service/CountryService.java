package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;

import java.util.List;

public interface CountryService {

    List<CountryListResponseDto> getAllCountries();

    Country getCountryById(int id);
}
