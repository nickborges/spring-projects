FROM maven:3.8.5-openjdk-17 AS build
ARG WORK_DIR="/usr/src/app"
WORKDIR ${WORK_DIR}
COPY . ${WORK_DIR}
RUN ["mvn", "-DskipTests", "clean", "package"]

FROM openjdk:17-oracle
ARG WORK_DIR="/usr/src/app"
WORKDIR ${WORK_DIR}
COPY --from=build ${WORK_DIR}/target/*.jar ./app.jar
CMD java -jar app.jar
EXPOSE 8080