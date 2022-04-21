package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.controller.handler.exceptions.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.OfficeRepository;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.model.mapper.OfficeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.dao.OfficeSpecification.officeSpecification;

/**
 * Класс реализация интерфейса OfficeService. Реализация методов получения данных с БД и преобразования данных в формат ответа
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    private final OrganizationService organizationService;

    private final OfficeMapper officeMapper;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository, OrganizationService organizationService, OfficeMapper officeMapper) {
        this.officeRepository = officeRepository;
        this.organizationService = organizationService;
        this.officeMapper = officeMapper;
    }

    /**
     * @see OfficeService#getAllOfficesByPredicat(OfficeListRequestDto)
     * @param officeListRequestDto
     * @return
     */
    @Override
    @Transactional
    public List<OfficeListResponseDto> getAllOfficesByPredicat(OfficeListRequestDto officeListRequestDto) {
        //Создание организации для добавления в атрибуты офиса
        Organization organization = organizationService.getOrganizationByIdFromRepository(officeListRequestDto.getOrgId());

        //Получение списка офисов
        List<Office> officeList = officeRepository.findAll(officeSpecification(officeListRequestDto));

        if (officeList.isEmpty()){
            throw new NoSuchObjectException("Нет офисов с такими параметрами.");
        }

        //Преобразование в формат ответа и возврат
        List<OfficeListResponseDto> officeListResponseDtos = officeMapper.toResponseDtoList(officeList);
        return officeListResponseDtos;
    }

    /**
     * @see OfficeService#getOfficeById(int)
     * @param id
     * @return
     */
    @Override
    @Transactional
    public OfficeResponseDto getOfficeById(int id) {
        //Получение офиса
        Office office = getOfficeByIdFromRepository(id);

        //Преобразование в формат ответа и возврат
        OfficeResponseDto officeResponseDto = officeMapper.toResponseDto(office);
        return officeResponseDto;
    }

    /**
     * @see OfficeService#updateOffice(OfficeUpdateRequestDto)
     * @param officeUpdateRequestDto
     * @return
     */
    @Override
    @Transactional
    public void updateOffice(OfficeUpdateRequestDto officeUpdateRequestDto) {
        //получение entity office
        Office office = getOfficeByIdFromRepository(officeUpdateRequestDto.getId());

        //обновление данных у entity данными которые пришли
        officeMapper.updateModel(officeUpdateRequestDto, office);

        //сохранение entity
        officeRepository.save(office);
    }

    /**
     * @see OfficeService#saveOffice(OfficeSaveRequestDto)
     * @param officeSaveRequestDto
     * @return
     */
    @Override
    @Transactional
    public void saveOffice(OfficeSaveRequestDto officeSaveRequestDto) {
        //Создание организации для добавления в атрибуты офиса
        Organization organization = organizationService.getOrganizationByIdFromRepository(officeSaveRequestDto.getOrgId());

        //Преобразование из формата запроса в entity
        Office office = officeMapper.toModel(officeSaveRequestDto);
        office.setOrganization(organization);

        //сохронение новой записи
        officeRepository.save(office);
    }

    /**
     * @see OfficeService#getOfficeByIdFromRepository(int)
     * @param id
     * @throws NoSuchObjectException ("Нет офиса с id = " + id)
     * @return
     */
    @Override
    @Transactional
    public Office getOfficeByIdFromRepository(int id) {
        //Получение офиса
        Optional<Office> optionalOffice = officeRepository.findById(id);

        if (optionalOffice.isEmpty()){
            throw new NoSuchObjectException("Нет офиса с id = " + id);
        }
        return optionalOffice.get();
    }
}
