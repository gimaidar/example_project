package com.gimaletdinov.exampleProject.model.mapper;

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

@Mapper(componentModel = "spring")
public interface OfficeMapper {

    @Mapping(source = "officeSaveRequestDto.name", target = "name")
    @Mapping(source = "officeSaveRequestDto.address", target = "address")
    @Mapping(source = "officeSaveRequestDto.phone", target = "phone")
    @Mapping(source = "officeSaveRequestDto.isActive", target = "isActive")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    Office toModel(OfficeSaveRequestDto officeSaveRequestDto);

    @Mapping(source = "officeListRequestDto.name", target = "name")
    @Mapping(source = "officeListRequestDto.phone", target = "phone")
    @Mapping(source = "officeListRequestDto.isActive", target = "isActive")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "address", ignore = true)
    Office toModel(OfficeListRequestDto officeListRequestDto);

    OfficeResponseDto toResponseDto(Office office);

    void updateModel(OfficeUpdateRequestDto officeUpdateRequestDto, @MappingTarget Office office);

    List<OfficeListResponseDto> toResponseDtoList(List<Office> officeList);
}
