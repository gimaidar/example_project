package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;

import java.util.List;

public interface CountryService {

    List<CountryListResponseDto> getAllCountries();
}
