FROM java:8-jre
MAINTAINER Muhammad Ali <mali@macrosoftinc.com>

ADD ./target/fizz-buzz-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/fizz-buzz-service.jar"]

EXPOSE 7000