package com.gimaletdinov.exampleProject.dao;

import com.gimaletdinov.exampleProject.model.Office;

import java.util.List;

public interface OfficeRepository {

    List<Office> getAllOfficesByPredicat(Office office);

    Office getOfficeById(int id);

    void updateOffice(Office office);

    void saveOffice(Office office);
}
