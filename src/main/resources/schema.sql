CREATE TABLE IF NOT EXISTS Organization (
                                            id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
                                            name       VARCHAR(25) NOT NULL COMMENT 'Имя организации',
                                            full_name  VARCHAR(50) NOT NULL COMMENT 'Полное имя организации',
                                            inn        VARCHAR(12) NOT NULL COMMENT 'Номер ИНН',
                                            kpp        VARCHAR(12) NOT NULL COMMENT 'Номер КПП',
                                            adress     VARCHAR(50) NOT NULL COMMENT 'Адрес организации',
                                            phone      VARCHAR(11)          COMMENT 'Телефон организации',
                                            is_active  BOOLEAN     NOT NULL COMMENT 'Статус активности'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office (
                                      id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
                                      name       VARCHAR(25) NOT NULL COMMENT 'Имя офиса',
                                      adress     VARCHAR(50) NOT NULL COMMENT 'Адрес офиса',
                                      phone      VARCHAR(11)          COMMENT 'Телефон организации',
                                      is_active  BOOLEAN     NOT NULL COMMENT 'Статус активности',
                                      org_id     INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации'
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User (
                                    id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
                                    first_name      VARCHAR(25) NOT NULL COMMENT 'Имя пользователя',
                                    second_name     VARCHAR(25) NOT NULL COMMENT 'Фамилия пользователя',
                                    middle_name     VARCHAR(25) NOT NULL COMMENT 'Среднее имя пользователя',
                                    position        INTEGER     NOT NULL COMMENT 'Позиция пользователя',
                                    phone           VARCHAR(11)          COMMENT 'Телефон пользователя',
                                    is_identified   BOOLEAN     NOT NULL COMMENT 'Статус идентификации',
                                    office_id       INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
                                    doc_id          INTEGER     NOT NULL COMMENT 'Уникальный идентификатор документа'
);
COMMENT ON TABLE Office IS 'Пользователь';

CREATE INDEX IX_Organization_Id ON Organization (id);
CREATE INDEX IX_Office_Id ON Office (id);
CREATE INDEX IX_User_Id ON User (id);

ALTER TABLE Office  ADD FOREIGN KEY (org_id)     REFERENCES Organization(id);
ALTER TABLE User    ADD FOREIGN KEY (office_id)  REFERENCES Office(id);
-- ALTER TABLE User    ADD FOREIGN KEY (doc_id)     REFERENCES Document(id);