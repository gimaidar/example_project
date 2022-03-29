package com.gimaletdinov.exampleProject.model.mapper;

import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.model.Country;
import com.gimaletdinov.exampleProject.model.Document;
import com.gimaletdinov.exampleProject.model.Office;
import com.gimaletdinov.exampleProject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "officeId", target = "office.id")
    @Mapping(source = "docNumber", target = "document.number")
    @Mapping(source = "docDate", target = "document.date")
    @Mapping(source = "docCode", target = "document.documentType.id")
    @Mapping(source = "docName", target = "document.documentType.name")
    @Mapping(source = "countryCode", target = "country.id")
    User toModel(UserSaveRequestDto userSaveRequestDto);

    @Mapping(source = "officeId", target = "office.id")
    @Mapping(source = "docCode", target = "document.documentType.id")
    @Mapping(source = "countryCode", target = "country.id")
    User toModel(UserListRequestDto userListRequestDto);

    @Mapping(source = "office.id", target = "officeId")
    @Mapping(source = "document.number", target = "docNumber")
    @Mapping(source = "document.date", target = "docDate")
    @Mapping(source = "document.documentType.name", target = "docName")
    @Mapping(source = "country.id", target = "countryCode")
    @Mapping(source = "country.name", target = "countryName")
    UserResponseDto toResponseDto(User user);

    @Mapping(source = "officeId", target = "office.id")
    @Mapping(source = "docNumber", target = "document.number")
    @Mapping(source = "docDate", target = "document.date")
    @Mapping(source = "docName", target = "document.documentType.name")
    @Mapping(source = "countryCode", target = "country.id")
    void updateModel(UserUpdateRequestDto userUpdateRequestDto, @MappingTarget User user);

    List<UserListResponseDto> toResponseDtoList(List<User> userList);
}
