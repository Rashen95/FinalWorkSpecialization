-- В подключенном MySQL репозитории создать базу данных “Друзья человека”
DROP DATABASE IF EXISTS humanFriends;
CREATE DATABASE humanFriends;
USE humanFriends;


-- Создать таблицы с иерархией из диаграммы в БД
DROP TABLE IF EXISTS cats;
CREATE TABLE cats (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animalName VARCHAR(20),
    commands VARCHAR(500),
    birthday DATE
);

DROP TABLE IF EXISTS dogs;
CREATE TABLE dogs (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animalName VARCHAR(20),
    commands VARCHAR(500),
    birthday DATE
);

DROP TABLE IF EXISTS humsters;
CREATE TABLE humsters (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animalName VARCHAR(20),
    commands VARCHAR(500),
    birthday DATE
);

DROP TABLE IF EXISTS horses;
CREATE TABLE horses (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animalName VARCHAR(20),
    commands VARCHAR(500),
    birthday DATE
);

DROP TABLE IF EXISTS camels;
CREATE TABLE camels (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animalName VARCHAR(20),
    commands VARCHAR(500),
    birthday DATE
);

DROP TABLE IF EXISTS donkeys;
CREATE TABLE donkeys (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    animalName VARCHAR(20),
    commands VARCHAR(500),
    birthday DATE
);


-- Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения
INSERT INTO cats (animalName, commands, birthday) values
('Барсик', 'Кушать', '2015-11-10'),
('Рыжик', 'Рыться', '2018-12-08'),
('Бельчик', 'Мяукать', '2010-06-03');

INSERT INTO dogs (animalName, commands, birthday) values
('Мафин', 'Сидеть', '2012-10-11'),
('Рекс', 'Рядом', '2022-05-07'),
('Мухтар', 'Сидеть', '2021-03-03');

INSERT INTO humsters (animalName, commands, birthday) values
('Рыжий', 'Грызть', '2012-11-12'),
('Толстяк', 'Грызть', '2019-01-07'),
('Крош', 'Грызть', '2023-04-03');

INSERT INTO horses (animalName, commands, birthday) values
('Милан', 'Но', '2015-11-12'),
('Грэй', 'Но', '2010-01-07'),
('Сэдрик', 'Бежать', '2020-05-07');

INSERT INTO camels (animalName, commands, birthday) values
('Самец', 'Плевать', '2012-10-09'),
('Широкий', 'Плевать', '2017-01-08'),
('Силач', 'Плевать', '2018-05-10');

INSERT INTO donkeys (animalName, commands, birthday) values
('Ослик', 'Грустить', '2012-11-08'),
('Осел', 'Говорить', '2022-05-09'),
('Мэрс', 'Идти', '2022-02-02');


-- Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. 
DELETE FROM camels WHERE id > 0;


-- Объединить таблицы лошади, и ослы в одну таблицу.
DROP TABLE IF EXISTS donkeyAndHorses; 
CREATE TABLE donkeysAndHorses(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
	SELECT animalName, commands, birthday, 'Лошадь' as type FROM horses
    UNION
    SELECT animalName, commands, birthday, 'Осел' as type FROM donkeys;
  
  
-- Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
DROP TABLE IF EXISTS youngAnimals;
CREATE TABLE youngAnimals(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
	SELECT 
		animalName,
        commands,
        birthday,
        TIMESTAMPDIFF(MONTH, birthday, NOW()) as Age_in_months
	FROM cats
    WHERE TIMESTAMPDIFF(MONTH, birthday, NOW()) > 12 and TIMESTAMPDIFF(MONTH, birthday, NOW()) < 36
    UNION
    SELECT 
		animalName,
        commands,
        birthday,
        TIMESTAMPDIFF(MONTH, birthday, NOW()) as Age_in_months
	FROM dogs
    WHERE TIMESTAMPDIFF(MONTH, birthday, NOW()) > 12 and TIMESTAMPDIFF(MONTH, birthday, NOW()) < 36
    UNION
    SELECT 
		animalName,
        commands,
        birthday,
        TIMESTAMPDIFF(MONTH, birthday, NOW()) as Age_in_months
	FROM humsters
    WHERE TIMESTAMPDIFF(MONTH, birthday, NOW()) > 12 and TIMESTAMPDIFF(MONTH, birthday, NOW()) < 36
    UNION
    SELECT 
		animalName,
        commands,
        birthday,
        TIMESTAMPDIFF(MONTH, birthday, NOW()) as Age_in_months
	FROM horses
    WHERE TIMESTAMPDIFF(MONTH, birthday, NOW()) > 12 and TIMESTAMPDIFF(MONTH, birthday, NOW()) < 36
    UNION
    SELECT 
		animalName,
        commands,
        birthday,
        TIMESTAMPDIFF(MONTH, birthday, NOW()) as Age_in_months
	FROM donkeys
    WHERE TIMESTAMPDIFF(MONTH, birthday, NOW()) > 12 and TIMESTAMPDIFF(MONTH, birthday, NOW()) < 36;

select * from youngAnimals;

-- Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам. 
DROP TABLE IF EXISTS humanFriends;
CREATE TABLE humanFriends(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY)
SELECT
	animalName,
    commands,
    birthday,
    id as idfromOldTable,
    'cats' as typeFromOldTable
FROM cats
UNION
SELECT
	animalName,
    commands,
    birthday,
    id as idfromOldTable,
    'dogs' as typeFromOldTable
FROM dogs
UNION
SELECT
	animalName,
    commands,
    birthday,
    id as idfromOldTable,
    'humsters' as typeFromOldTable
FROM humsters
UNION
SELECT
	animalName,
    commands,
    birthday,
    id as idfromOldTable,
    'horses' as typeFromOldTable
FROM horses
UNION
SELECT
	animalName,
    commands,
    birthday,
    id as idfromOldTable,
    'donkeys' as typeFromOldTable
FROM donkeys;

select * from humanFriends;