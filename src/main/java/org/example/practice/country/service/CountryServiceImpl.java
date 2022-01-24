package org.example.practice.country.service;

import org.example.practice.country.dao.CountryDao;
import org.example.practice.country.dto.CountryDto;
import org.example.practice.country.entity.Country;
import org.example.practice.mapper.EntityToDtoMapperImpl;
import org.example.practice.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryDao dao;
    private final Mapper mapper;

    @Autowired
    public CountryServiceImpl(CountryDao dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(CountryDto countryDto) {
        dao.save(mapper.map(countryDto , Country.class));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryDto> countries() {
        List<Country> countries = dao.all();
        return mapper.mapAll(countries, CountryDto.class);
    }
}
