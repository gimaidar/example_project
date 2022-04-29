package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gimaletdinov.exampleProject.service.OfficeService;

import javax.validation.Valid;
import java.util.List;

/**
 *Контроллер для сущности Офис
 */
@RestController
@RequestMapping("api/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Метод возвращает список офисов, найденных в БД по определенному предикату
     * @return список офисов, найденных в БД по определенному придикату
     */
    @GetMapping("/list")
    public List<OfficeListResponseDto> getAllOfficesByPredicat(@Valid @RequestBody OfficeListRequestDto officeListRequestDto) {
        return officeService.getAllOfficesByPredicat(officeListRequestDto);
    }

    /**
     * Метод возвращает офис по id
     * @param id id офиса
     * @return офис
     */
    @GetMapping("/{id}")
    public OfficeResponseDto getOrganizationById(@PathVariable Integer id) {
        return officeService.getOfficeById(id);
    }

    /**
     * Метод для обновления данных офиса
     * @param officeUpdateRequestDto
     * @return ObjectSuccessResponseDto [result : success]
     */
    @PostMapping("/update")
    public ObjectSuccessResponseDto updateOrganization(@Valid @RequestBody OfficeUpdateRequestDto officeUpdateRequestDto) {
        officeService.updateOffice(officeUpdateRequestDto);
        return new ObjectSuccessResponseDto();
    }

    /**
     * Метод для сохранения нового офиса
     * @param officeSaveRequestDto
     * @return ObjectSuccessResponseDto [result : success]
     */
    @PostMapping("/save")
    public ObjectSuccessResponseDto saveOrganization(@Valid @RequestBody OfficeSaveRequestDto officeSaveRequestDto) {
        officeService.saveOffice(officeSaveRequestDto);
        return new ObjectSuccessResponseDto();
    }
}
