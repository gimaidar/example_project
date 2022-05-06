package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import com.gimaletdinov.exampleProject.dto.request.UserListRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;

import java.util.List;

/**
 * Маппер для сущности Пользователь
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Метод для маппинга запроса - userSaveRequestDto в User
     * @param userSaveRequestDto
     * @return User
     */
    @Mapping(source = "officeId", target = "office.id")
    @Mapping(source = "docNumber", target = "document.number")
    @Mapping(source = "docDate", target = "document.date")
    @Mapping(source = "docCode", target = "document.documentType.code")
    @Mapping(source = "docName", target = "document.documentType.name")
    @Mapping(source = "countryCode", target = "country.code")
    User toModel(UserSaveRequestDto userSaveRequestDto);

    /**
     * Метод для маппинга User в формат ответа UserResponseDto
     * @param user
     * @return UserResponseDto
     */
    @Mapping(source = "office.id", target = "officeId")
    @Mapping(source = "document.number", target = "docNumber")
    @Mapping(source = "document.date", target = "docDate")
    @Mapping(source = "document.documentType.name", target = "docName")
    @Mapping(source = "country.code", target = "countryCode")
    @Mapping(source = "country.name", target = "countryName")
    UserResponseDto toResponseDto(User user);

    /**
     * Метод для маппинга (обновления) User из запроса - UserUpdateRequestDto
     * @param userUpdateRequestDto
     * @param user
     */
    @Mapping(source = "officeId", target = "office.id")
    @Mapping(source = "docNumber", target = "document.number")
    @Mapping(source = "docDate", target = "document.date")
    @Mapping(source = "docName", target = "document.documentType.name")
    @Mapping(source = "countryCode", target = "country.code")
    void updateModel(UserUpdateRequestDto userUpdateRequestDto, @MappingTarget User user);

    /**
     * Метод для маппинга спсика User в формат ответа список UserListResponseDto
     * @param userList
     * @return спсиок UserListResponseDto
     */
    List<UserListResponseDto> toResponseDtoList(List<User> userList);
}
