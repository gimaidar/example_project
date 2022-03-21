INSERT INTO Organization (version, name, full_name, inn, kpp, address, phone, is_active) VALUES  (1, 'Бэлл Интегратор', 'ООО Бэлл Интегратор', '1234567891', '1119874563', 'г.Москва', '79171111111', true),
                                                                                                (1,'Рога и копыта', 'ООО Рога и копыта', '1234567891', '1119874563', 'г.Москва', '79171111111', true);

INSERT INTO Office (version, name, address, phone, is_active, org_id) VALUES (1, 'Бэлл Интегратор Уфа', 'г.Уфа ул. Свердлова 92', '79177481998', true, 1),
                                                                            (1, 'Рога и копыта Уфа', 'г.Уфа ул. Свердлова 100', '79177481998', true, 2);

INSERT INTO Citizenship (version, citizenship_name, citizenship_code) VALUES    (1, 'Гражданин', 1);

INSERT INTO Document (version, doc_name, doc_number, doc_date) VALUES    (1, 'Паспорт', 1, CURRENT_DATE());

INSERT INTO User (version, first_name, second_name, middle_name, position, phone, is_identified, office_id, doc_id, citizenship_id) VALUES  (1, 'Гималетдинов', 'Айдар', 'Ильмирович', 1, '79177481998', true, 1, 1, 1),
                                                                                                                                            (1, 'Иванов', 'Иван', 'Иванович', 1, '79177481998', true, 2, 1, 1);
