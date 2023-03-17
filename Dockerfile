FROM maven:3.5-jdk-8 AS build  
WORKDIR /usr/src/app
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
COPY testng.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install -DskipTests
EXPOSE 8084
CMD ["mvn", "test","allure:serve"]