package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.CountryRepository;
import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.mapper.CountryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Класс реализация интерфейса CountryService. Реализация методов получения данных с БД и преобразования данных в формат ответа
 */
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    /**
     * @see CountryService#getAllCountries()
     * @return
     */
    @Override
    public List<CountryListResponseDto> getAllCountries() {
        List<Country> countryList = countryRepository.findAll();

        return countryMapper.toResponseDtoList(countryList);
    }

    /**
     * @see CountryService#getCountryById(int)
     * @param id
     * @throws NoSuchObjectException ("Нет страны с code = " + id);
     * @return
     *
     */
    @Override
    public Country getCountryById(int id) {
        Country country = countryRepository.getById(id);
        if (country == null){
            throw new NoSuchObjectException("Нет страны с code = " + id);
        }

        return country;
    }
}
