CREATE TABLE IF NOT EXISTS User
(
    id          INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version     INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    first_name  VARCHAR(20) NOT NULL COMMENT 'Имя',
    last_name   VARCHAR(20)          COMMENT 'Фамилия',
    middle_name VARCHAR(20)          COMMENT 'Отчество',
    position    VARCHAR(30) NOT NULL COMMENT 'Должность',
    phone       VARCHAR(20)          COMMENT 'Телефон',
    office_id   INTEGER     NOT NULL COMMENT 'Идентификатор офиса',
    country_id  INTEGER     NOT NULL COMMENT 'Идентификатор гражданства',
    identified  BOOLEAN
);
COMMENT ON TABLE User IS 'Пользователь';

CREATE TABLE IF NOT EXISTS Office
(
    id        INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name      VARCHAR(20) NOT NULL COMMENT 'Название офиса',
    address   VARCHAR(50) NOT NULL COMMENT 'Адрес офиса',
    phone     VARCHAR(20)          COMMENT 'Телефон офиса',
    org_id    INTEGER     NOT NULL COMMENT 'Идентификатор организации',
    active    BOOLEAN
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS Organization
(
    id        INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name      VARCHAR(20) NOT NULL COMMENT 'Название организации',
    full_name VARCHAR(20) NOT NULL COMMENT 'Полное название организации',
    inn       VARCHAR(10) NOT NULL COMMENT 'ИНН',
    kpp       VARCHAR(9)  NOT NULL COMMENT 'КПП',
    address   VARCHAR(50) NOT NULL COMMENT 'Адрес организации',
    phone     VARCHAR(20)          COMMENT 'Телефон организации',
    active    BOOLEAN
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Countries
(
    id      INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name    VARCHAR(30) NOT NULL COMMENT 'Название страны',
    code    VARCHAR(10) NOT NULL COMMENT 'Код страны'
);
COMMENT ON TABLE Countries IS 'Гражданство';

CREATE TABLE IF NOT EXISTS Docs
(
    user_id INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY,
    version INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    number  VARCHAR(15) NOT NULL COMMENT 'Номер документа',
    date    VARCHAR(15) NOT NULL COMMENT 'Дата документа',
    type_id INTEGER     NOT NULL COMMENT 'Идентификатор типа документа'

);
COMMENT ON TABLE Docs IS 'Документ';

CREATE TABLE IF NOT EXISTS Types_of_docs
(
    id      INTEGER              COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    name    VARCHAR(50) NOT NULL COMMENT 'Название документа',
    code    VARCHAR(10) NOT NULL COMMENT 'Код документа'
);
COMMENT ON TABLE Types_of_docs IS 'Тип документа';

-- Создание внешнего ключа, который является первичным для связи один к одному таблиц User и Docs
ALTER TABLE Docs
    ADD FOREIGN KEY (user_id) REFERENCES User (id);

-- Создание внешнего ключа для связи один ко многим таблиц Docs и Type_of_docs
-- CREATE INDEX IX_Doc_Type_Id ON Docs (type_id);
ALTER TABLE Docs
    ADD FOREIGN KEY (type_id) REFERENCES Types_of_docs (id);

-- Создание внешнего ключа для связи один ко многим таблиц User и Office
-- CREATE INDEX IX_User_Office_Id ON User (office_id);
ALTER TABLE User
    ADD FOREIGN KEY (office_id) REFERENCES Office (id);

-- Создание внешнего ключа для связи один ко многим таблиц User и Countries
-- CREATE INDEX IX_User_Country_Id ON User (country_id);
ALTER TABLE User
    ADD FOREIGN KEY (country_id) REFERENCES Countries (id);

-- Создание внешнего ключа для связи один ко многим таблиц Office и Organization
-- CREATE INDEX IX_Office_Organization_Id ON Office (org_id);
ALTER TABLE Office
    ADD FOREIGN KEY (org_id) REFERENCES Organization (id);
