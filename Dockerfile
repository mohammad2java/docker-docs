FROM circleci/jdk8:0.1.1
COPY target/*.jar /home/
EXPOSE 8080
CMD java -jar /home/service-web-0.0.1-SNAPSHOT.jar


