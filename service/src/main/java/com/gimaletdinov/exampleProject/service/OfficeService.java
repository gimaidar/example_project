package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;

import java.util.List;

/**
 * Интерфейс сервиса для сущности Офис
 */
public interface OfficeService {

    /**
     * Метод возвращает список офисов, найденных в БД по определенному предикату
     * @see com.gimaletdinov.exampleProject.dao.OfficeRepository#getAllOfficesByPredicat(Office)
     * @param officeListRequestDto
     * @return список офисов
     */
    List<OfficeListResponseDto> getAllOfficesByPredicat(OfficeListRequestDto officeListRequestDto);

    /**
     * Метод возвращает офис по id и преобразует в формат ответа
     * @see com.gimaletdinov.exampleProject.dao.OfficeRepository#getOfficeById(int)
     * @param id
     * @return офис
     */
    OfficeResponseDto getOfficeById(int id);

    /**
     * Метод для обновления данных офиса
     * @see com.gimaletdinov.exampleProject.dao.OfficeRepository#saveOffice(Office)
     * @param officeUpdateRequestDto
     */
    void updateOffice(OfficeUpdateRequestDto officeUpdateRequestDto);

    /**
     * Метод для сохранения нового офиса
     * @see com.gimaletdinov.exampleProject.dao.OfficeRepository#updateOffice(Office)
     * @param officeSaveRequestDto
     */
    void saveOffice(OfficeSaveRequestDto officeSaveRequestDto);

    /**
     * Метод возвращает офис по id
     * @param id
     * @return офис
     */
    Office getOfficeByIdFromRepository(int id);
}
