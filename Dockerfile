FROM maven:3.9.1-eclipse-temurin-17
COPY pom.xml /app/
COPY src /app/src/
COPY frontend/public /app/frontend/public
COPY frontend/src /app/frontend/src
COPY frontend/package.json /app/frontend/package.json
COPY frontend/package-lock.json /app/frontend/package-lock.json
WORKDIR /app/
ENTRYPOINT ["mvn","spring-boot:run"]