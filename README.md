# Bell Integrator study project
Данный проект выполнен в рамках онлайн-стажировки в компании "Bell Integrator". <br/>
Проект представляет собой API web-сервис для сохранения, обновления и получения списка пользователей, офисов и организаций. <br/>
Проект написан с использованием Spring Boot, JDBC, JPA, REST API. <br/>
В качестве базы даннных исользуется H2. <br/>
Сборка проекта и управление зависимостями осуществляется с помощью Maven. <br/>
Версия Java - 17. <br/>

## Запуск проекта
Для того, чтобы запустить данный проект, необходимо скопировать репозиторий к себе на компьютер, 
следующей командой в терминале.
### Command
```bash
git clone https://github.com/LastDi/bell
```

# Примеры некоторых запросов и ответов
GET запрос для получения пользователя по id (Например id = 3):
```bash
http://localhost:8080/api/user/3
```
Ответ: 
<br/>

<p align="center">
  <img src="img.png" width="50%" height="50%"/>
</p>

![img.png](img.png)

POST запрос для получения списка пользователей по фильтру:
```bash
http://localhost:8080/api/user/list
```
![img_1.png](img_1.png)
<br/>
Ответ: 
<br/>
![img_2.png](img_2.png)

POST запрос для сохранения офиса
```bash
http://localhost:8080/api/office/save
```
![img_3.png](img_3.png)
<br/>
Ответ: 
<br/>
![img_4.png](img_4.png)

POST запрос для обновления данных организации
```bash
http://localhost:8080/api/organization/update
```
![img_5.png](img_5.png)
<br/>
Ответ:
<br/>
![img_6.png](img_6.png)


