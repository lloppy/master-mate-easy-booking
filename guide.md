# Installation guide 

1. Настройка бекенда:
   - Скачайте репозиторий бекенда по [ссылке](https://github.com/ASUKA-LANGLEY-SOHRYU/web-registration/tree/main)
   - Добавьте в папку web-registration-main\src\main\resources два файла: application.properties и application-local.properties (файлы не публичные, поэтому их нужно запросить у mishaprosvirninmail@yandex.ru <mishaprosvirninmail@yandex.ru>)
   - Настройте докер, для этого добавьте в настройки Docker китайские зеркала:
     Настройки -> Docker Engine -> Вставлять под "experimental":
   
   ``` 
       "registry-mirrors": [
       "https://mirror.gcr.io",
       "https://daocloud.io",
       "https://c.163.com/",
       "https://registry.docker-cn.com"
      ]
   ```
   
   - Запустите из деректории \web-registration-main командную стоку и введите команду:  
   ```
     docker compose up --build (внимательнее, нужно --, а не —)
   ```
   - ✅ Готово!


2. Настройка pgAdmina:
   - Для входа логин/пароль: admin@pgadmin.com / password

   - Теперь нужно добавить новый сервер. Host name/address - это ваш айпи (нужно его найти), для этого:
     - Открываем консоль cmd
     - Вводим команду 
     ```
       docker network ls
     ```
     - Ищем там webregistration_webreg-net, слева от webregistration_webreg-net у нас написано значение, его нужно запомнить (***)
     - Далее вводим команду: 
     ```
     - docker network inspect (***)
     ```
     , где (***) какой-то набор цифр и букв найденных на прошлом шаге
   
     - Ищем значение "webreg_db", там же находится айпи, который нам нужен (вствляем без маски - без косой черты и цифр после нее)
   - Вернемся в pgAdmin, вставим в host name/address найденный айпи
   - Номер порта - 5432
   - Для бд логин(username)/пароль(password): postgres/postgres
   - ✅ Готово!


3. Swagger:
   - Можно найти по [ссылке](http://localhost:8080/swagger-ui/index.html)
   - ✅ Готово!


4. Конфигурация телефона android на порт 8080:
   - В терминале android studio ввожу команду:  
   ```
   adb reverse tcp:8080 tcp:8080
   ```
   - Внимание! Если вы случайно выдернули провод отладки из телефона, то нужно повторить предыдущий шаг заново!
   - ✅ Готово!




### ✅ Настройка закончена!
