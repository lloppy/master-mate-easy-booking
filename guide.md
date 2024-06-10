# 🚀 Installation Guide - English Below

## Настройка бекенда:

1. Скачайте репозиторий бекенда по [ссылке](https://github.com/ASUKA-LANGLEY-SOHRYU/web-registration/tree/main).
2. Добавьте в папку `web-registration-main\src\main\resources` два файла: `application.properties` и `application-local.properties`. Файлы не публичные, поэтому их нужно запросить у <mishaprosvirninmail@yandex.ru>.
3. Настройте докер. Для этого добавьте в настройки Docker китайские зеркала:  
   **Настройки** -> **Docker Engine** -> Вставьте под `"experimental"`:

    ```json
    "registry-mirrors": [
         "https://mirror.gcr.io",
         "https://daocloud.io",
         "https://c.163.com/",
         "https://registry.docker-cn.com"
    ]
    ```

4. Запустите командную строку из директории `\web-registration-main` и введите команду:
    ```bash
    docker compose up --build  # Внимательнее! Нужно использовать "--", а не "—"
    ```

   ✅ Готово!

<br>

## Настройка pgAdmin:

1. Для входа используйте логин/пароль: `admin@pgadmin.com` / `password`.

2. Теперь нужно добавить новый сервер. **Host name/address** — это ваш IP (нужно его найти), для этого:
   - Откройте консоль `cmd`.
   - Введите команду:
     ```bash
     docker network ls
     ```
   - Найдите там `webregistration_webreg-net`, слева от этого значения находится нужное значение (***), его нужно запомнить.
   - Затем введите команду, где (***) — это набор цифр и букв, найденных на предыдущем шаге:
     ```bash
     docker network inspect (***)
     ```
   - Найдите значение `"webreg_db"`, там же находится IP, который нам нужен (вставляем без маски — без косой черты и цифр после нее).

3. Вернитесь в pgAdmin, вставьте в **Host name/address** найденный IP.
4. Номер порта — `5432`.
5. Для базы данных логин(username)/пароль(password): `postgres`/`postgres`.
6. Основные команды для работы с БД:
    ```sql
    select * from schedule;
    select * from activation_code;
    select * from usr;
    select * from master;
    select * from client;
    select * from image;
    select * from service;
    select * from category;
    select * from record;
    ```

   ✅ Готово!

<br>

## Swagger:

1. Можно найти по [ссылке](http://localhost:8080/swagger-ui/index.html).

   ✅ Готово!

<br>

## Конфигурация телефона Android на порт 8080:

1. В терминале Android Studio введите команду:
    ```bash
    adb reverse tcp:8080 tcp:8080
    ```
2. Внимание! Если вы случайно выдернули провод отладки из телефона, необходимо повторить предыдущий шаг заново.

   ✅ Готово!

<br>

### ✅ Настройка закончена!

<br>

# Installation Guide

## Backend Setup:

1. Download the backend repository from [this link](https://github.com/ASUKA-LANGLEY-SOHRYU/web-registration/tree/main).
2. Add two files: `application.properties` and `application-local.properties` to the folder `web-registration-main\src\main\resources`. These files are private and need to be requested from <mishaprosvirninmail@yandex.ru>.
3. Configure Docker by adding Chinese mirrors:  
   **Settings** -> **Docker Engine** -> Insert under `"experimental"`:

    ```json
    "registry-mirrors": [
         "https://mirror.gcr.io",
         "https://daocloud.io",
         "https://c.163.com/",
         "https://registry.docker-cn.com"
    ]
    ```

4. Open the command prompt from the `\web-registration-main` directory and enter the command:
    ```bash
    docker compose up --build  # Be careful! Use "--" not "—"
    ```

   ✅ Done!

<br>

## Setting up pgAdmin:

1. Use the login/password for access: `admin@pgadmin.com` / `password`.

2. You now need to add a new server. **Host name/address** — this is your IP (you need to find it), to do this:
   - Open the `cmd` console.
   - Enter the command:
     ```bash
     docker network ls
     ```
   - Look for `webregistration_webreg-net`, to the left of this value is the needed value (***) that needs to be noted.
   - Then, enter the command where (***) is the set of numbers and letters found in the previous step:
     ```bash
     docker network inspect (***)
     ```
   - Look for the value `"webreg_db"`, and the IP we need will be there (insert without the mask — without the slash and the numbers after it).

3. Return to pgAdmin and insert the found IP into **Host name/address**.
4. Port number — `5432`.
5. For the database login(username)/password(password): `postgres`/`postgres`.
6. Main commands for working with the database:
    ```sql
    select * from schedule;
    select * from activation_code;
    select * from usr;
    select * from master;
    select * from client;
    select * from image;
    select * from service;
    select * from category;
    select * from record;
    ```

   ✅ Done!

<br>

## Swagger:

1. It can be found at [this link](http://localhost:8080/swagger-ui/index.html).

   ✅ Done!

<br>

## Configuring Android phone on port 8080:

1. In the Android Studio terminal, enter the command:
    ```bash
    adb reverse tcp:8080 tcp:8080
    ```
2. Attention! If you accidentally unplug the debugging cable from the phone, you need to repeat the previous step again.

   ✅ Done!

<br>

### ✅ Setup completed!