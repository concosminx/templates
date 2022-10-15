FROM openjdk:11-jre

RUN mkdir /app

WORKDIR /app

ADD ./build/libs/ws_with_springboot.jar /app


EXPOSE 8080

CMD ["java", "-jar", "ws_with_springboot.jar"]