package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.model.mapper.OrganizationListMapper;
import com.gimaletdinov.exampleProject.model.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrganizationServiceImpl implements  OrganizationService{

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    private final OrganizationListMapper organizationListMapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, OrganizationListMapper organizationListMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.organizationListMapper = organizationListMapper;
    }

    @Override
    @Transactional
    public List<OrganizationListResponseDto> getAllOrganizationByPredicat(OrganizationListRequestDto organizationListRequestDto) {
        //Получение списка организаций
        List<Organization> organizationList = organizationRepository.getAllOrganizationByPredicat(organizationMapper.toModel(organizationListRequestDto));

        //Преобразование в формат ответа и возврат
        List<OrganizationListResponseDto> organizationListResponseDtoList = organizationListMapper.toResponseDtoList(organizationList);
        return organizationListResponseDtoList;
    }

    @Override
    @Transactional
    public OrganizationResponseDto getOrganizationById(int id) {
        //Получение организации
        Organization organization = organizationRepository.getOrganizationById(id);

        //Преобразование в формат ответа и возврат
        OrganizationResponseDto organizationResponseDto = organizationMapper.toResponseDto(organization);
        return organizationResponseDto;
    }

    @Override
    @Transactional
    public void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        //получение entity organization
        Organization organization = organizationRepository.getOrganizationById(organizationUpdateRequestDto.getId());

        //обновление данных у entity данными которые пришли
        organizationMapper.updateModel(organizationUpdateRequestDto, organization);

        //сохранение entity
        organizationRepository.updateOrganization(organization);
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationSaveRequestDto organizationSaveRequestDto) {
        //Преобразование из формата запроса в entity
        Organization organization = organizationMapper.toModel(organizationSaveRequestDto);

        //сохронение новой записи
        organizationRepository.saveOrganization(organization);
    }
}
