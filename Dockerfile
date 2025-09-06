FROM gradle:8.9.0-jdk17 AS build
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew build -x test --no-daemon

FROM eclipse-temurin:17-jre
WORKDIR /distributed-lock
COPY --from=build /app/build/libs/*.jar /distributed-lock/distributed-lock.jar
ENTRYPOINT ["java","-jar","distributed-lock.jar"]
