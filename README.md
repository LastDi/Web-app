# Bell Integrator study project
Данный проект выполнен в рамках онлайн-стажировки в компании "Bell Integrator". 
Проект представляет собой API web-сервис для сохранения, обновления и получения списка пользователей, офисов и организаций.
Проект написан с использованием Spring Boot, JDBC, JPA, REST API.
В качестве базы даннных исользуется H2.
Сборка проекта и управление зависимостями осуществляется с помощью Maven.
Версия Java - 17.

# Запуск проекта
Для того, чтобы запустить данный проект, необходимо скопировать репозиторий к себе на компьютер, 
следующей командой в терминале.
# Command
 git clone https://github.com/LastDi/bell
Открыть проект при помощи Intellij IDEA и запустить, выбрав конфигурацию Spring Boot.

# Примеры некоторых запросов и ответов
GET запрос для получения пользователя по id:
http://localhost:8080/api/user/3
Ответ:
![img.png](img.png)

POST запрос для получения списка пользователей по фильтру:
http://localhost:8080/api/user/list
![img_1.png](img_1.png)
Ответ:
![img_2.png](img_2.png)

POST запрос для сохранения офиса
http://localhost:8080/api/office/save
![img_3.png](img_3.png)
Ответ:
![img_4.png](img_4.png)

POST запрос для обновления данных организации
http://localhost:8080/api/organization/update
![img_5.png](img_5.png)
Ответ:
![img_6.png](img_6.png)


