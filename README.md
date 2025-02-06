# Insurance_Calculator

Веб сервис на Spring Boot
Для запуска рекомендовано использовать Docker
1. ```git clone <repo-url>```
2. ```cd Insurance_Calculator```
3. Настройка окружения: 
    ```sh 
   echo "DATABASE_URL=jdbc:mysql://mysql:3306/java-real-practice-insurance?serverTimezone=Europe/Minsk
   DATABASE_USERNAME=root
   DATABASE_PASSWORD=root" > .env
   ```
4. Убедитесь, что порты 8080 и 3306 свободны
   Windows:
       ```netstat -aon | findstr ":8080"
          netstat -aon | findstr ":3306"```
    Linux:
        ```netstat -tulnp | grep -E ':(8080|3306)'```
5. Запуск
    ```
   docker-compose up
   ```
6. Можно отправлять запросы (коллекция запросов Postman: ```cd postman```)