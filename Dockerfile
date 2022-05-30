FROM hub.intra.doublefs.com/sys/baseimage/maven:3.8.1-jdk-11 as build
WORKDIR /app
COPY quality-service-data/pom.xml quality-service-data/pom.xml
COPY quality-service-feign/pom.xml quality-service-feign/pom.xml
COPY quality-service-service/pom.xml quality-service-service/pom.xml
COPY quality-service-web/pom.xml quality-service-web/pom.xml
RUN mvn -f quality-service-data/pom.xml dependency:go-offline --fn && \
    mvn -f quality-service-feign/pom.xml dependency:go-offline --fn && \
    mvn -f quality-service-service/pom.xml dependency:go-offline --fn && \
    mvn -f quality-service-web/pom.xml dependency:go-offline --fn
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

FROM hub.intra.doublefs.com/sys/baseimage/openjdk:11-jre
COPY --from=build /app/quality-service-web/target/application.jar /
ENTRYPOINT ["java", "-jar", "/application.jar"]
