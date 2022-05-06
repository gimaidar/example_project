package com.gimaletdinov.exampleProject.mapper;

import com.gimaletdinov.exampleProject.dto.request.OfficeSaveRequestDto;
import com.gimaletdinov.exampleProject.dto.request.OfficeUpdateRequestDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeListResponseDto;
import com.gimaletdinov.exampleProject.dto.response.OfficeResponseDto;
import com.gimaletdinov.exampleProject.model.Office;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.gimaletdinov.exampleProject.helper.OfficeDtoTestHelper.getPopulateOffice;
import static com.gimaletdinov.exampleProject.helper.OfficeDtoTestHelper.getPopulateOfficeSaveRequestDto;
import static com.gimaletdinov.exampleProject.helper.OfficeDtoTestHelper.getPopulateOfficeUpdateRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OfficeMapperImpl.class)
class OfficeMapperTest {

    @Autowired
    private OfficeMapper officeMapper;

    @Test
    @DisplayName("Тест: toModel (Маппинг из OfficeSaveRequestDto -> Office)")
    void toModel() {
        OfficeSaveRequestDto officeSaveRequestDto = getPopulateOfficeSaveRequestDto();
        Office office = officeMapper.toModel(officeSaveRequestDto);

        assertAll(
                () -> assertThat(office.getName()).isEqualTo(officeSaveRequestDto.getName()),
                () -> assertThat(office.getAddress()).isEqualTo(officeSaveRequestDto.getAddress()),
                () -> assertThat(office.getPhone()).isEqualTo(officeSaveRequestDto.getPhone()),
                () -> assertThat(office.getIsActive()).isEqualTo(officeSaveRequestDto.getIsActive())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDto (Маппинг из Office -> OfficeResponseDto)")
    void toResponseDto() {
        Office office = getPopulateOffice();
        OfficeResponseDto officeResponseDto = officeMapper.toResponseDto(office);
        assertAll(
                () -> assertThat(officeResponseDto.getId()).isEqualTo(office.getId()),
                () -> assertThat(officeResponseDto.getName()).isEqualTo(office.getName()),
                () -> assertThat(officeResponseDto.getAddress()).isEqualTo(office.getAddress()),
                () -> assertThat(officeResponseDto.getPhone()).isEqualTo(office.getPhone()),
                () -> assertThat(officeResponseDto.getIsActive()).isEqualTo(office.getIsActive())
        );
    }

    @Test
    @DisplayName("Тест: updateModel (Маппинг из OfficeUpdateRequestDto -> Office)")
    void updateModel() {
        Office office = getPopulateOffice();
        OfficeUpdateRequestDto officeUpdateRequestDto = getPopulateOfficeUpdateRequestDto();
        officeMapper.updateModel(officeUpdateRequestDto, office);
        assertAll(
                () -> assertThat(office.getId()).isEqualTo(officeUpdateRequestDto.getId()),
                () -> assertThat(office.getName()).isEqualTo(officeUpdateRequestDto.getName()),
                () -> assertThat(office.getAddress()).isEqualTo(officeUpdateRequestDto.getAddress()),
                () -> assertThat(office.getPhone()).isEqualTo(officeUpdateRequestDto.getPhone()),
                () -> assertThat(office.getIsActive()).isEqualTo(officeUpdateRequestDto.getIsActive())
        );
    }

    @Test
    @DisplayName("Тест: toResponseDtoList (Маппинг из List<Office> ->  List<OfficeListResponseDto>)")
    void toResponseDtoList() {
        Office office = getPopulateOffice();
        List<Office> officeList = new ArrayList<>();
        officeList.add(office);
        
        List<OfficeListResponseDto> officeListResponseDtoList = officeMapper.toResponseDtoList(officeList);
        OfficeListResponseDto officeListResponseDto = officeListResponseDtoList.get(0);
        assertAll(
                () -> assertThat(officeListResponseDto.getId()).isEqualTo(office.getId()),
                () -> assertThat(officeListResponseDto.getName()).isEqualTo(office.getName()),
                () -> assertThat(officeListResponseDto.getIsActive()).isEqualTo(office.getIsActive())
        );
    }
}