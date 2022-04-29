package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.exception.NoSuchObjectException;
import com.gimaletdinov.exampleProject.mapper.CountryMapper;
import com.gimaletdinov.exampleProject.dao.CountryRepository;
import com.gimaletdinov.exampleProject.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gimaletdinov.exampleProject.dto.response.CountryListResponseDto;

import java.util.List;
import java.util.Optional;

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
     * @see CountryService#getCountryByCode(String)
     * @param code
     * @throws NoSuchObjectException ("Нет страны с code = " + id);
     * @return
     *
     */
    @Override
    public Country getCountryByCode(String code) {
        Optional<Country> countryOptional = countryRepository.findByCode(code);
        if (countryOptional.isEmpty()){
            throw new NoSuchObjectException("Нет страны с code = " + code);
        }

        return countryOptional.get();
    }
}
