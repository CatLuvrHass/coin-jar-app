FROM openjdk:8-jre-alpine
COPY target/roundup-1.0-SNAPSHOT-jar-with-dependencies.jar /roundup-1.0.jar
CMD /usr/bin/java -jar /roundup-1.0.jar