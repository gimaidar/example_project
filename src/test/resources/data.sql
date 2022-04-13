INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active) VALUES  (1, 1, 'test org name', 'test_org full_name', '1111111111', '2222222222', 'test_org adress', '71111111111', true),
                                                                                                 (2, 1, 'test_org name2', 'test_org full_name2', '3333333333', '4444444444', 'test_org adress2', '72222222222', true);

INSERT INTO Office (id, version, name, address, phone, is_active, org_id) VALUES (1, 1, 'test_office name', 'test_office address', '73333333333', true, 1),
                                                                                 (2, 1, 'test_office name2', 'test_office address2', '74444444444', true, 2);

INSERT INTO Country (id, version, country_name) VALUES    (643, 1, 'Российская Федерация');



INSERT INTO User (version, first_name, second_name, middle_name, position, phone, is_identified, office_id, country_id) VALUES  (1, 'test_user first_name', 'test_user second_name', 'test_user middle_name', 1, '75555555555', true, 1, 643),
                                                                                                                                (1, 'test_user first_name2', 'test_user second_name2', 'test_user middle_name2', 1, '75555555555', true, 2, 643);
INSERT INTO Document_type (id, name) VALUES    (21, 'Паспорт');

INSERT INTO Document (id, version, doc_number, doc_date, doc_type_id) VALUES    (1, 1, 111111, '2022-04-12', 21),
                                                                                (2, 1, 222222, '2022-04-13', 21);
