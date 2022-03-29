package com.gimaletdinov.exampleProject.controller;

import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.ObjectSuccessResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/list")
    public List<OfficeListResponseDto> getAllOfficesByPredicat(@Valid @RequestBody OfficeListRequestDto officeListRequestDto) {
        return officeService.getAllOfficesByPredicat(officeListRequestDto);
    }

    @GetMapping("/{id}")
    public OfficeResponseDto getOrganizationById(@PathVariable Integer id) {
        return officeService.getOfficeById(id);
    }

    @PostMapping("/update")
    public ObjectSuccessResponseDto updateOrganization(@Valid @RequestBody OfficeUpdateRequestDto officeUpdateRequestDto) {
        officeService.updateOffice(officeUpdateRequestDto);
        return new ObjectSuccessResponseDto();
    }

    @PostMapping("/save")
    public ObjectSuccessResponseDto saveOrganization(@Valid @RequestBody OfficeSaveRequestDto officeSaveRequestDto) {
        officeService.saveOffice(officeSaveRequestDto);
        return new ObjectSuccessResponseDto();
    }
}
