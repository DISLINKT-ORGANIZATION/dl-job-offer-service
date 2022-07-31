FROM adoptopenjdk:11-jre-hotspot
COPY "target/job-offer-service.jar" job-offer-service.jar
ENTRYPOINT ["java", "-jar", "job-offer-service.jar"]