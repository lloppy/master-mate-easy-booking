# Installation Guide

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

---

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

---

## Swagger:
1. Можно найти по [ссылке](http://localhost:8080/swagger-ui/index.html).

   ✅ Готово!

---

## Конфигурация телефона Android на порт 8080:
1. В терминале Android Studio введите команду:
    ```bash
    adb reverse tcp:8080 tcp:8080
    ```
2. Внимание! Если вы случайно выдернули провод отладки из телефона, необходимо повторить предыдущий шаг заново.

   ✅ Готово!

---

### ✅ Настройка закончена!

---

