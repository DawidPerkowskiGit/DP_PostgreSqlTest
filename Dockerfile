# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
RUN mvn -f /app/pom.xml dependency:resolve
COPY src ./src

FROM base as test
CMD ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=prod", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]

FROM base as build
#CMD ["./mvnw", "clean" , "package", "-Dmaven.test.skip"]
#RUN ./mvnw clean package -Dmaven.test.skip
RUN mvn clean package -Dmaven.test.skip

FROM eclipse-temurin:17-jre as production
EXPOSE 8080
COPY --from=build /app/target/PostgreSqlTest-0.0.1-SNAPSHOT.jar /PostgreSqlTest.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/PostgreSqlTest.jar"]
