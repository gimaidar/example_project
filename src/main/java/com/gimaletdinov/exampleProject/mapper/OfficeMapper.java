package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.request.OfficeListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * Маппер для сущности Офис
 */
@Mapper(componentModel = "spring")
public interface OfficeMapper {

    /**
     * Метод для маппинга запроса - OfficeSaveRequestDto в Office
     * @param officeSaveRequestDto
     * @return Office
     */
    @Mapping(source = "officeSaveRequestDto.name", target = "name")
    @Mapping(source = "officeSaveRequestDto.address", target = "address")
    @Mapping(source = "officeSaveRequestDto.phone", target = "phone")
    @Mapping(source = "officeSaveRequestDto.isActive", target = "isActive")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Office toModel(OfficeSaveRequestDto officeSaveRequestDto);

    /**
     * Метод для маппинга запроса - OfficeListRequestDto в Office
     * @param officeListRequestDto
     * @return Office
     */
    @Mapping(source = "officeListRequestDto.name", target = "name")
    @Mapping(source = "officeListRequestDto.phone", target = "phone")
    @Mapping(source = "officeListRequestDto.isActive", target = "isActive")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "address", ignore = true)
    Office toModel(OfficeListRequestDto officeListRequestDto);

    /**
     * Метод для маппинга Office в формат ответа OfficeResponseDto
     * @param office
     * @return OfficeResponseDto
     */
    OfficeResponseDto toResponseDto(Office office);

    /**
     * Метод для маппинга (обновления) Office из запроса - OfficeUpdateRequestDto
     * @param officeUpdateRequestDto
     * @param office
     */
    void updateModel(OfficeUpdateRequestDto officeUpdateRequestDto, @MappingTarget Office office);

    /**
     * Метод для маппинга спсика Office в формат ответа список OfficeListResponseDto
     * @param officeList
     * @return спсиок OfficeListResponseDto
     */
    List<OfficeListResponseDto> toResponseDtoList(List<Office> officeList);
}
