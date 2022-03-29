INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active) VALUES  (1, 'Бэлл Интегратор', 'ООО Бэлл Интегратор', '1234567891', '1119874563', 'г.Москва', '79171111111', true),
                                                                                                (1,'Рога и копыта', 'ООО Рога и копыта', '1234567891', '1119874563', 'г.Москва', '79171111111', true);

INSERT INTO Office (version, name, address, phone, is_active, org_id) VALUES (1, 'Бэлл Интегратор Уфа', 'г.Уфа ул. Свердлова 92', '79177481998', true, 1),
                                                                            (1, 'Рога и копыта Уфа', 'г.Уфа ул. Свердлова 100', '79177481998', true, 2);

INSERT INTO Country (id, version, country_name) VALUES    (643, 1, 'Российская Федерация');

INSERT INTO Document_type (id, name) VALUES    (21, 'Паспорт');

INSERT INTO Document (id, version, doc_number, doc_date, doc_type_id) VALUES    (1, 1, 123456, CURRENT_DATE(), 21),
                                                                                (2, 1, 123456, CURRENT_DATE(), 21);

INSERT INTO User (version, first_name, second_name, middle_name, position, phone, is_identified, office_id, country_id) VALUES  (1, 'Гималетдинов', 'Айдар', 'Ильмирович', 1, '79177481998', true, 1, 643),
                                                                                                                                (1, 'Иванов', 'Иван', 'Иванович', 1, '79177481998', true, 2, 643);
