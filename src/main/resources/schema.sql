CREATE TABLE IF NOT EXISTS Organization (
        id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        name       VARCHAR(25) NOT NULL COMMENT 'Имя организации',
        full_name  VARCHAR(50) NOT NULL COMMENT 'Полное имя организации',
        inn        VARCHAR(12) NOT NULL COMMENT 'Номер ИНН',
        kpp        VARCHAR(12) NOT NULL COMMENT 'Номер КПП',
        adress     VARCHAR(50) NOT NULL COMMENT 'Адрес организации',
        phone      VARCHAR(11)          COMMENT 'Телефон организации',
        is_active  BOOLEAN              COMMENT 'Статус активности'
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Id ON Organization (id);

CREATE TABLE IF NOT EXISTS Office (
        id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        name       VARCHAR(25) NOT NULL COMMENT 'Имя офиса',
        adress     VARCHAR(50) NOT NULL COMMENT 'Адрес офиса',
        phone      VARCHAR(11)          COMMENT 'Телефон организации',
        is_active  BOOLEAN              COMMENT 'Статус активности',
        org_id     INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации'
);
COMMENT ON TABLE Office IS 'Офис';
CREATE INDEX IX_Office_Id ON Office (id);

CREATE TABLE IF NOT EXISTS User (
        id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version         INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        first_name      VARCHAR(25) NOT NULL COMMENT 'Имя пользователя',
        second_name     VARCHAR(25)          COMMENT 'Фамилия пользователя',
        middle_name     VARCHAR(25)          COMMENT 'Среднее имя пользователя',
        position        INTEGER     NOT NULL COMMENT 'Позиция пользователя',
        phone           VARCHAR(11)          COMMENT 'Телефон пользователя',
        is_identified   BOOLEAN              COMMENT 'Статус идентификации',
        office_id       INTEGER     NOT NULL COMMENT 'Уникальный идентификатор офиса',
        doc_id          INTEGER     NOT NULL COMMENT 'Уникальный идентификатор документа',
        citizenship_id  INTEGER     NOT NULL COMMENT 'Уникальный идентификатор гражданства'
);
COMMENT ON TABLE User IS 'Пользователь';
CREATE INDEX IX_User_Id ON User (id);

CREATE TABLE IF NOT EXISTS Document (
        id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version         INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        doc_name        VARCHAR(25) NOT NULL COMMENT 'Имя документа',
        doc_number      INTEGER     NOT NULL COMMENT 'Номер документа',
        doc_date        DATE        NOT NULL COMMENT 'Дата окончания действия документа'
);

COMMENT ON TABLE Document IS 'Документ';
CREATE INDEX IX_Document_Id ON Document (id);

CREATE TABLE IF NOT EXISTS Citizenship (
        id               INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version          INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        citizenship_name VARCHAR(25) NOT NULL COMMENT 'Название гражданства',
        citizenship_code INTEGER     NOT NULL COMMENT 'Номер гражданства'
);

COMMENT ON TABLE Citizenship IS 'Гражданство';
CREATE INDEX IX_Citizenship_Id ON Citizenship (id);

CREATE TABLE IF NOT EXISTS Country (
        id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version         INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        country_name    INTEGER     NOT NULL COMMENT 'Название страны',
        country_code    INTEGER     NOT NULL COMMENT 'Код страны'
);

COMMENT ON TABLE Country IS 'Страна';
CREATE INDEX IX_Country_Id ON Country (id);

ALTER TABLE Office  ADD FOREIGN KEY (org_id)     REFERENCES Organization(id);
ALTER TABLE User    ADD FOREIGN KEY (office_id)         REFERENCES Office(id);
ALTER TABLE User    ADD FOREIGN KEY (doc_id)            REFERENCES Document(id);
ALTER TABLE User    ADD FOREIGN KEY (citizenship_id)    REFERENCES Citizenship(id);