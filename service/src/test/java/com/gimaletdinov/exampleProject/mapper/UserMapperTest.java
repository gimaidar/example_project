package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.request.UserSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.UserUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.UserListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.UserResponseDto;
import com.gimaletdinov.exampleProject.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUser;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUserSaveRequestDto;
import static com.gimaletdinov.exampleProject.helper.UserDtoTestHelper.getPopulateUserUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserMapperImpl.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Тест: toModel (Маппинг из UserSaveRequestDto -> User)")
    void toModel() {
        UserSaveRequestDto userSaveRequestDto = getPopulateUserSaveRequestDto();

        User user = userMapper.toModel(userSaveRequestDto);

        assertAll(
                () -> assertThat(user.getFirstName()).isEqualTo(userSaveRequestDto.getFirstName()),
                () -> assertThat(user.getSecondName()).isEqualTo(userSaveRequestDto.getSecondName()),
                () -> assertThat(user.getMiddleName()).isEqualTo(userSaveRequestDto.getMiddleName()),
                () -> assertThat(user.getPosition()).isEqualTo(userSaveRequestDto.getPosition()),
                () -> assertThat(user.getPhone()).isEqualTo(userSaveRequestDto.getPhone()),
                () -> assertThat(user.getIsIdentified()).isEqualTo(userSaveRequestDto.getIsIdentified()),
                () -> assertThat(user.getDocument().getDocumentType().getCode()).isEqualTo(userSaveRequestDto.getDocCode()),
                () -> assertThat(user.getDocument().getDocumentType().getName()).isEqualTo(userSaveRequestDto.getDocName()),
                () -> assertThat(user.getDocument().getNumber()).isEqualTo(userSaveRequestDto.getDocNumber()),
                () -> assertThat(user.getDocument().getDate()).isEqualTo(userSaveRequestDto.getDocDate()),
                () -> assertThat(user.getCountry().getCode()).isEqualTo(userSaveRequestDto.getCountryCode()),
                () -> assertThat(user.getCountry().getName()).isEqualTo(userSaveRequestDto.getCountryName()),
                () -> assertThat(user.getOffice().getId()).isEqualTo(userSaveRequestDto.getOfficeId())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDto (Маппинг из User -> UserResponseDto)")
    void toResponseDto() {
        User user = getPopulateUser();
        UserResponseDto userResponseDto = userMapper.toResponseDto(user);
        assertAll(
                () -> assertThat(userResponseDto.getFirstName()).isEqualTo(user.getFirstName()),
                () -> assertThat(userResponseDto.getSecondName()).isEqualTo(user.getSecondName()),
                () -> assertThat(userResponseDto.getMiddleName()).isEqualTo(user.getMiddleName()),
                () -> assertThat(userResponseDto.getPosition()).isEqualTo(user.getPosition()),
                () -> assertThat(userResponseDto.getPhone()).isEqualTo(user.getPhone()),
                () -> assertThat(userResponseDto.getIsIdentified()).isEqualTo(user.getIsIdentified()),
                () -> assertThat(userResponseDto.getDocName()).isEqualTo(user.getDocument().getDocumentType().getName()),
                () -> assertThat(userResponseDto.getDocNumber()).isEqualTo(user.getDocument().getNumber()),
                () -> assertThat(userResponseDto.getDocDate()).isEqualTo(user.getDocument().getDate()),
                () -> assertThat(userResponseDto.getCountryCode()).isEqualTo(user.getCountry().getCode()),
                () -> assertThat(userResponseDto.getCountryName()).isEqualTo(user.getCountry().getName()),
                () -> assertThat(userResponseDto.getOfficeId()).isEqualTo(user.getOffice().getId())
        );
    }

    @Test
    @DisplayName("Тест: updateModel (Маппинг из UserUpdateRequestDto -> User)")
    void updateModel() {
        User user = getPopulateUser();
        UserUpdateRequestDto userUpdateRequestDto = getPopulateUserUpdateRequestDto();
        userMapper.updateModel(userUpdateRequestDto, user);
        assertAll(
                () -> assertThat(user.getId()).isEqualTo(userUpdateRequestDto.getId()),
                () -> assertThat(user.getFirstName()).isEqualTo(userUpdateRequestDto.getFirstName()),
                () -> assertThat(user.getSecondName()).isEqualTo(userUpdateRequestDto.getSecondName()),
                () -> assertThat(user.getMiddleName()).isEqualTo(userUpdateRequestDto.getMiddleName()),
                () -> assertThat(user.getPosition()).isEqualTo(userUpdateRequestDto.getPosition()),
                () -> assertThat(user.getPhone()).isEqualTo(userUpdateRequestDto.getPhone()),
                () -> assertThat(user.getIsIdentified()).isEqualTo(userUpdateRequestDto.getIsIdentified()),
                () -> assertThat(user.getDocument().getDocumentType().getName()).isEqualTo(userUpdateRequestDto.getDocName()),
                () -> assertThat(user.getDocument().getNumber()).isEqualTo(userUpdateRequestDto.getDocNumber()),
                () -> assertThat(user.getDocument().getDate()).isEqualTo(userUpdateRequestDto.getDocDate()),
                () -> assertThat(user.getCountry().getCode()).isEqualTo(userUpdateRequestDto.getCountryCode()),
                () -> assertThat(user.getCountry().getName()).isEqualTo(userUpdateRequestDto.getCountryName()),
                () -> assertThat(user.getOffice().getId()).isEqualTo(userUpdateRequestDto.getOfficeId())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDtoList (Маппинг из List<User> ->  List<UserListResponseDto>)")
    void toResponseDtoList() {
        User user = getPopulateUser();
        List<User> userList = new ArrayList<>();
        userList.add(user);

        List<UserListResponseDto> userListResponseDtoList = userMapper.toResponseDtoList(userList);
        UserListResponseDto userListResponseDto = userListResponseDtoList.get(0);

        assertAll(
                () -> assertThat(userListResponseDto.getId()).isEqualTo(user.getId()),
                () -> assertThat(userListResponseDto.getFirstName()).isEqualTo(user.getFirstName()),
                () -> assertThat(userListResponseDto.getSecondName()).isEqualTo(user.getSecondName()),
                () -> assertThat(userListResponseDto.getMiddleName()).isEqualTo(user.getMiddleName()),
                () -> assertThat(userListResponseDto.getPosition()).isEqualTo(user.getPosition())
        );
    }
}