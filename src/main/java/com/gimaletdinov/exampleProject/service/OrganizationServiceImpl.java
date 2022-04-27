package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.exception.NoSuchObjectException;
import com.gimaletdinov.exampleProject.dao.OrganizationRepository;
import com.gimaletdinov.exampleProject.dto.request.OrganizationListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OrganizationUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OrganizationResponseDto;
import com.gimaletdinov.exampleProject.model.Organization;
import com.gimaletdinov.exampleProject.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static com.gimaletdinov.exampleProject.dao.OrganizationSpecification.organizationSpecification;

/**
 * Класс реализация интерфейса OrganizationService. Реализация методов получения данных с БД и преобразования данных в формат ответа
 */
@Service
public class OrganizationServiceImpl implements  OrganizationService{

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    /**
     * {@link OrganizationService#getAllOrganizationsByPredicat(OrganizationListRequestDto)}
     * @param organizationListRequestDto
     * @return
     */
    @Override
    @Transactional
    public List<OrganizationListResponseDto> getAllOrganizationsByPredicat(OrganizationListRequestDto organizationListRequestDto) {
        //Получение списка организаций
        List<Organization> organizationList = organizationRepository.findAll(organizationSpecification(organizationListRequestDto));

        if (organizationList.isEmpty()){
            throw new NoSuchObjectException("Нет организаций с такими параметрами.");
        }

        //Преобразование в формат ответа и возврат
        List<OrganizationListResponseDto> organizationListResponseDtoList = organizationMapper.toResponseDtoList(organizationList);
        return organizationListResponseDtoList;
    }

    /**
     * {@link OrganizationService#getOrganizationById(int)}
     * @param id
     * @return
     */
    @Override
    @Transactional
    public OrganizationResponseDto getOrganizationById(int id) {
        //Получение организации
        Organization organization = getOrganizationByIdFromRepository(id);

        //Преобразование в формат ответа и возврат
        OrganizationResponseDto organizationResponseDto = organizationMapper.toResponseDto(organization);
        return organizationResponseDto;
    }

    /**
     * {@link OrganizationService#updateOrganization(OrganizationUpdateRequestDto)}
     * @param organizationUpdateRequestDto
     * @return
     */
    @Override
    @Transactional
    public void updateOrganization(OrganizationUpdateRequestDto organizationUpdateRequestDto) {
        //получение entity organization
        Organization organization = getOrganizationByIdFromRepository(organizationUpdateRequestDto.getId());

        //обновление данных у entity данными которые пришли
        organizationMapper.updateModel(organizationUpdateRequestDto, organization);

        //сохранение entity
        organizationRepository.save(organization);
    }

    /**
     * {@link OrganizationService#saveOrganization(OrganizationSaveRequestDto)}
     * @param organizationSaveRequestDto
     * @return
     */
    @Override
    @Transactional
    public void saveOrganization(OrganizationSaveRequestDto organizationSaveRequestDto) {
        //Преобразование из формата запроса в entity
        Organization organization = organizationMapper.toModel(organizationSaveRequestDto);

        //сохронение новой записи
        organizationRepository.save(organization);
    }

    /**
     * {@link OrganizationService#getOrganizationByIdFromRepository(Integer)}
     * @param id
     * @throws NoSuchObjectException ("Нет организации с id = " + id)
     * @return
     */
    @Override
    @Transactional
    public Organization getOrganizationByIdFromRepository(Integer id) {
        //Получение организации
        Optional<Organization> organizationOptional = organizationRepository.findById(id);

        if (organizationOptional.isEmpty()){
            throw new NoSuchObjectException("Нет организации с id = " + id);
        }

        return organizationOptional.get();
    }
}
