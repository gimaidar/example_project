package com.gimaletdinov.exampleProject.service;

import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;

import java.util.List;

public interface OfficeService {

    List<OfficeListResponseDto> getAllOfficesByPredicat(OfficeListRequestDto officeListRequestDto);

    OfficeResponseDto getOfficeById(int id);

    void updateOffice(OfficeUpdateRequestDto officeUpdateRequestDto);

    void saveOffice(OfficeSaveRequestDto officeSaveRequestDto);
}
