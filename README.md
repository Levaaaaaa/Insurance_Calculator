# Insurance_Calculator

Веб сервис на Spring Boot
Для запуска рекомендовано использовать Docker
1. ```git clone <repo-url>```
2. ```cd Insurance_Calculator```
3. Настройка окружения: 
   - сервиса insurance-calculator
       ```sh 
      echo "DATABASE_URL=jdbc:mysql://mysql:3306/java-real-practice-insurance?serverTimezone=Europe/Minsk 
         DATABASE_USERNAME=root 
         DATABASE_PASSWORD=root
         SPRING_PROFILES_ACTIVE=docker
         SPRING_RABBITMQ_HOST=message-broker
         SPRING_RABBITMQ_USERNAME=guest
         SPRING_RABBITMQ_PASSWORD=guest" > insurance-calculator-app/.env
      ```
   - базы данных
       ```sh 
      echo " MYSQL_DATABASE=java-real-practice-insurance
         MYSQL_PASSWORD=root
         MYSQL_ROOT_PASSWORD=root" > insurance-calculator/db.env
      ```
   - сервиса doc-generator
      ```sh 
     echo "SPRING_RABBITMQ_HOST=message-broker
     SPRING_RABBITMQ_USERNAME=guest
     SPRING_RABBITMQ_PASSWORD=guest" > doc-generator-app/doc-gen.env 
     ```
   
4. Убедитесь, что свободны следующие порты 8080 - insurance-calculator, 8081 - doc-generator, 5672, 15672 - RabbitMQ и 3306 - MySQL
   - Windows:
     ```sh
     netstat -aon | findstr ":8080"
     netstat -aon | findstr ":3306"
     netstat -aon | findstr ":3306"
     netstat -aon | findstr ":5672"
     netstat -aon | findstr ":15672"
     ```

   - Linux:
     ```sh
     netstat -tulnp | grep -E ':(8080|3306|8081|5672|15672)'
     ```

5. Запуск
```sh
   docker-compose up
```

6. Можно отправлять запросы (коллекция запросов Postman: ```cd postman```)