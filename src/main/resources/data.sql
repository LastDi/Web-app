----------------------------------------- Добавляем типы документов-----------------------------------------------------

INSERT INTO Types_of_docs (id, version, name, code) VALUES (1, 0, 'Свидетельство о рождении', '03');

INSERT INTO Types_of_docs (id, version, name, code) VALUES (2, 0, 'Военный билет', '07');

INSERT INTO Types_of_docs (id, version, name, code) VALUES (3, 0, 'Паспорт иностранного гражданина', '10');

INSERT INTO Types_of_docs (id, version, name, code) VALUES (4, 0, 'Паспорт гражданина Российской Федерации', '21');

INSERT INTO Types_of_docs (id, version, name, code) VALUES (5, 0, 'Иные документы', '91');

----------------------------------------- Добавляем гражданства---------------------------------------------------------

INSERT INTO Countries (id, version, name, code) VALUES (1, 0, 'Российская Федерация', '501');

INSERT INTO Countries (id, version, name, code) VALUES (2, 0, 'США', '170');

INSERT INTO Countries (id, version, name, code) VALUES (3, 0, 'Украина', '222');

INSERT INTO Countries (id, version, name, code) VALUES (4, 0, 'Австралия', '333');

----------------------------------------- Добавляем организации---------------------------------------------------------

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, active) VALUES (1, 0, 'X5', 'X5 Retail', '11111111', '12121212', 'ул. Центральная, 99', '+74951313243', TRUE);

INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, active) VALUES (2, 0, 'BBK', 'BBK Group', '33333333', '34343434', 'First street, 25', TRUE);

----------------------------------------- Добавляем офисы---------------------------------------------------------------

INSERT INTO Office (id, version, name, address, phone, org_id, active) VALUES (1, 0, 'Пятерочка', 'ул. Ленина, 23', '+79118099121', 1, true);

INSERT INTO Office (id, version, name, address, org_id, active) VALUES (2, 0, 'Realme', 'Highway street, 20', 2, true);

----------------------------------------- Добавляем пользователей-------------------------------------------------------

INSERT INTO User (id, version, first_name, middle_name, position, phone, office_id, country_id, identified) VALUES (1, 0, 'Иван', 'Иванович', 'Продавец', '79019209899', 1, 1, TRUE);

INSERT INTO User (id, version, first_name, last_name, position, office_id, country_id, identified) VALUES (2, 0, 'Петр', 'Петров', 'Менеджер', 2, 1, TRUE);

INSERT INTO User (id, version, first_name, position, office_id, country_id, identified) VALUES (3, 0, 'Дмитрий', 'Директор', 1, 2, TRUE);

INSERT INTO User (id, version, first_name, position, office_id, country_id, identified) VALUES (4, 0, 'Иван', 'Менеджер', 1, 2, TRUE);

INSERT INTO User (id, version, first_name, position, office_id, country_id, identified) VALUES (5, 0, 'Алексей', 'Консультант', 2, 2, TRUE);

----------------------------------------- Добавляем документы-----------------------------------------------------------

INSERT INTO Docs (user_id, version, number, date, type_id) VALUES (1, 0, '8198 675466', '2017-12-01', 1);

INSERT INTO Docs (user_id, version, number, date, type_id) VALUES (2, 0, '9871243254', '2010-01-04', 2);

INSERT INTO Docs (user_id, version, number, date, type_id) VALUES (3, 0, '1234 347232', '2012-11-11', 1);

INSERT INTO Docs (user_id, version, number, date, type_id) VALUES (4, 0, '1234 111111', '2012-11-11', 2);

INSERT INTO Docs (user_id, version, number, date, type_id) VALUES (5, 0, '1234 222222', '2012-11-11', 1);







