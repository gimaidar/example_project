

INSERT INTO Organization (name, full_name, inn, kpp, adress, phone, is_active) VALUES ('Бэлл Интегратор', 'ООО Бэлл Интегратор', '1234567891', '1119874563', 'г.Москва', '79171111111', true),
                                                                                      ('Рога и копыта', 'ООО Рога и копыта', '1234567891', '1119874563', 'г.Москва', '79171111111', true);

INSERT INTO Office (name, adress, phone, is_active, org_id) VALUES ('Бэлл Интегратор Уфа', 'г.Уфа ул. Свердлова 92', 79177481998, true, 1),
                                                                   ('Рога и копыта Уфа', 'г.Уфа ул. Свердлова 100', 79177481998, true, 2);

INSERT INTO User (first_name, second_name, middle_name, position, phone, is_identified, office_id, doc_id) VALUES ('Гималетдинов', 'Айдар', 'Ильмирович', 1, 79177481998, true, 1, 0),
                                                                                                                  ('Иванов', 'Иван', 'Иванович', 1, 79177481998, true, 2, 0);
