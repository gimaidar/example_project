INSERT INTO Country (id, version, country_name)
        VALUES (643, 1, 'Российская Федерация');

INSERT INTO Document_type (id, name)
        VALUES (21, 'Паспорт');

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
        VALUES (1, 1, 'test_org_name', 'test_org_full_name', '9999999999', '8888888888', 'test_org_address', '99999999999', true);

INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
        VALUES (1, 1, 'test_office_name', 'test_office_address', '99999999999', true, 1);

INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, is_identified, office_id, country_id)
        VALUES (1, 1, 'test_user_first_name', 'test_user_second_name', 'test_user_middle_name', 1, '99999999999', true, 1, 643);

INSERT INTO Document (id, version, doc_number, doc_date, doc_type_id)
        VALUES (1, 1, 111111, '2022-04-12', 21);
