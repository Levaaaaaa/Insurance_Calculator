FROM eclipse-temurin:21
COPY *.jar insurance-calculator.jar
CMD ["java", "-jar", "insurance-calculator.jar"]
EXPOSE 8080
