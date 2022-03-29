package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OfficeRepository;
import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
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

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeRepository officeRepository;

    private final OrganizationRepository organizationRepository;

    private final OfficeMapper officeMapper;

    @Autowired
    public OfficeServiceImpl(OfficeRepository officeRepository, OrganizationRepository organizationRepository, OfficeMapper officeMapper) {
        this.officeRepository = officeRepository;
        this.organizationRepository = organizationRepository;
        this.officeMapper = officeMapper;
    }

    @Override
    @Transactional
    public List<OfficeListResponseDto> getAllOfficesByPredicat(OfficeListRequestDto officeListRequestDto) {
        //Создание организации для добавления в атрибуты офиса
        Organization organization = organizationRepository.getOrganizationById(officeListRequestDto.getOrgId());

        //Получение списка офисов
        List<Office> officeList = officeRepository.getAllOfficesByPredicat(officeMapper.toModel(officeListRequestDto, organization));

        //Преобразование в формат ответа и возврат
        List<OfficeListResponseDto> officeListResponseDtos = officeMapper.toResponseDtoList(officeList);
        return officeListResponseDtos;
    }

    @Override
    @Transactional
    public OfficeResponseDto getOfficeById(int id) {
        //Получение офиса
        Office office = officeRepository.getOfficeById(id);

        //Преобразование в формат ответа и возврат
        OfficeResponseDto officeResponseDto = officeMapper.toResponseDto(office);
        return officeResponseDto;
    }

    @Override
    @Transactional
    public void updateOffice(OfficeUpdateRequestDto officeUpdateRequestDto) {
        //получение entity office
        Office office = officeRepository.getOfficeById(officeUpdateRequestDto.getId());

        //обновление данных у entity данными которые пришли
        officeMapper.updateModel(officeUpdateRequestDto, office);

        //сохранение entity
        officeRepository.updateOffice(office);
    }

    @Override
    @Transactional
    public void saveOffice(OfficeSaveRequestDto officeSaveRequestDto) {
        //Создание организации для добавления в атрибуты офиса
        Organization organization = organizationRepository.getOrganizationById(officeSaveRequestDto.getOrgId());

        //Преобразование из формата запроса в entity
        Office office = officeMapper.toModel(officeSaveRequestDto, organization);

        //сохронение новой записи
        officeRepository.saveOffice(office);
    }
}
