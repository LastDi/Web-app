# Bell Integrator study project
Данный проект выполнен в рамках онлайн-стажировки в компании "Bell Integrator". <br/>
Проект представляет собой API web-сервис для сохранения, обновления и получения списка пользователей, офисов и организаций. <br/>
Проект написан с использованием Spring Boot, JDBC, JPA, REST API. <br/>
В качестве базы даннных исользуется H2. <br/>
Сборка проекта и управление зависимостями осуществляется с помощью Maven. <br/>
Версия Java - 17. <br/>

### Запуск проекта
Для того, чтобы запустить данный проект, необходимо скопировать репозиторий к себе на компьютер, 
следующей командой в терминале.
##### Command
```bash
git clone https://github.com/LastDi/bell
```

### Примеры некоторых запросов и ответов
GET запрос для получения пользователя по id (Например id = 3):
```bash
http://localhost:8080/api/user/3
```
Ответ: 
<br/>
<p align="center">
  <img src="https://i.ibb.co/mqsyC8V/img.png" width="50%" height="5`0%"/>
</p>

POST запрос для получения списка пользователей по фильтру:
```bash
http://localhost:8080/api/user/list
```
<p align="center">
  <img src="https://i.ibb.co/RTC31hP/img-1.png" width="50%" height="50%"/>
</p>
<br/>
Ответ: 
<br/>
<p align="center">
  <img src="https://i.ibb.co/RSsfqXB/img-2.png" width="50%" height="50%"/>
</p>

POST запрос для сохранения офиса
```bash
http://localhost:8080/api/office/save
```
<p align="center">
  <img src="https://i.ibb.co/9tNY646/img-3.png" width="50%" height="50%"/>
</p>
<br/>
Ответ: 
<br/>
<p align="center">
  <img src="https://i.ibb.co/BzXWm95/img-4.png" width="50%" height="50%"/>
</p>

POST запрос для обновления данных организации
```bash
http://localhost:8080/api/organization/update
```
<p align="center">
  <img src="https://i.ibb.co/vhZyhD8/img-5.png" width="50%" height="50%"/>
</p>
<br/>
Ответ:
<br/>
<p align="center">
  <img src="https://i.ibb.co/nL0sRqL/img-6.png" width="50%" height="50%"/>
</p>
<br/>

Далее перечислены все доступные запросы: <br/>
### GET запросы
##### для получения элемента по его id (вместо {id} указать нужное значение id)
```bash
http://localhost:8080/api/user/\{id\}
```
```bash
http://localhost:8080/api/office/\{id\}
```
```bash
http://localhost:8080/api/organization/\{id\}
```
```bash
http://localhost:8080/api/docs
```
```bash
http://localhost:8080/api/countries
```

### POST запросы
##### для получения списка по фильтру
```bash
http://localhost:8080/api/user/list
```
```bash
http://localhost:8080/api/office/list
```
```bash
http://localhost:8080/api/organization/list
```
##### для сохранения элемента в базе
```bash
http://localhost:8080/api/user/save
```
```bash
http://localhost:8080/api/office/save
```
```bash
http://localhost:8080/api/organization/save
```
##### для обновления данных
```bash
http://localhost:8080/api/user/update
```
```bash
http://localhost:8080/api/office/update
```
```bash
http://localhost:8080/api/organization/update
```