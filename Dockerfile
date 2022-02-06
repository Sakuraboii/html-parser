FROM openjdk:11

COPY ./target/html-parser-1.0-SNAPSHOT.jar /opt/html-parser/app.jar

ENTRYPOINT ["java","-jar","/opt/html-parser/app.jar"]