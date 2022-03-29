CREATE TABLE IF NOT EXISTS Organization (
        id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
        version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
        name       VARCHAR(25) NOT NULL COMMENT 'Имя организации',
        full_name  VARCHAR(50) NOT NULL COMMENT 'Полное имя организации',
        inn        VARCHAR(12) NOT NULL COMMENT 'Номер ИНН',
        kpp        VARCHAR(12) NOT NULL COMMENT 'Номер КПП',
        address     VARCHAR(50) NOT NULL COMMENT 'Адрес организации',
        phone      VARCHAR(11)          COMMENT 'Телефон организации',
        is_active  BOOLEAN              COMMENT 'Статус активности'
);
COMMENT ON TABLE Organization IS 'Организация';
CREATE INDEX IX_Organization_Id         ON Organization (id);
CREATE INDEX IX_Organization_Inn        ON Organization (inn);
CREATE INDEX IX_Organization_Is_active  ON Organization (is_active);

CREATE TABLE IF NOT EXISTS Office (
      id         INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
      version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
      name       VARCHAR(25) NOT NULL COMMENT 'Имя офиса',
      address     VARCHAR(50) NOT NULL COMMENT 'Адрес офиса',
      phone      VARCHAR(11)          COMMENT 'Телефон организации',
      is_active  BOOLEAN              COMMENT 'Статус активности',
      org_id     INTEGER     NOT NULL COMMENT 'Уникальный идентификатор организации'
);
COMMENT ON TABLE Office IS 'Офис';
ALTER TABLE Office  ADD FOREIGN KEY (org_id)     REFERENCES Organization(id);
CREATE INDEX IX_Office_Id           ON Office (id);
CREATE INDEX IX_Office_Name         ON Office (name);
CREATE INDEX IX_Office_Phone        ON Office (phone);
CREATE INDEX IX_Office_Is_Active    ON Office (is_active);

CREATE TABLE IF NOT EXISTS Document_type (
     id     INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
     name   VARCHAR(25) NOT NULL COMMENT 'Имя документа'
);
COMMENT ON TABLE Document_type IS 'Тип документа';
CREATE INDEX IX_Document_type_ID ON Document_type (id);

CREATE TABLE IF NOT EXISTS Document (
    id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
    version         INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    doc_number      INTEGER     NOT NULL COMMENT 'Номер документа',
    doc_date        DATE        NOT NULL COMMENT 'Дата окончания действия документа',
    doc_type_id     INTEGER(25) NOT NULL COMMENT 'Тип документа'
);

COMMENT ON TABLE Document IS 'Документ';
ALTER TABLE Document  ADD FOREIGN KEY(doc_type_id)     REFERENCES Document_type(id);
CREATE INDEX IX_Document_Id ON Document (id);

CREATE TABLE IF NOT EXISTS Country (
   id              INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT ,
   version         INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
   country_name    VARCHAR(25) NOT NULL COMMENT 'Название страны'
);

COMMENT ON TABLE Country IS 'Страна';
CREATE INDEX IX_Country_Id ON Country (id);

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
--         doc_id          INTEGER     NOT NULL COMMENT 'Уникальный идентификатор документа',
    country_id      INTEGER     NOT NULL COMMENT 'Уникальный идентификатор гражданства'
);
COMMENT ON TABLE User IS 'Пользователь';
ALTER TABLE User    ADD FOREIGN KEY (office_id)         REFERENCES Office(id);
-- ALTER TABLE User    ADD FOREIGN KEY (doc_id)            REFERENCES Document(id);
ALTER TABLE User    ADD FOREIGN KEY (country_id)    REFERENCES Country(id);
CREATE INDEX IX_User_Id             ON User (id);
CREATE INDEX IX_User_First_name     ON User (first_name);
CREATE INDEX IX_User_Second_name    ON User (second_name);
CREATE INDEX IX_User_Middle_name    ON User (middle_name);
CREATE INDEX IX_User_Position       ON User (position);
-- CREATE INDEX UX_User_Doc_id         ON User (id, doc_id);
-- CREATE INDEX UX_User_Citizenship_id ON User (id, citizenship_id);