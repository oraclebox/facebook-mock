FROM anapsix/alpine-java:8
RUN mkdir /application
RUN mkdir -p /application/logs
COPY build/libs/facebook-login-mock-SNAPSHOT.jar /application/application.jar
COPY application.yml /application/application.yml
COPY created_me.json /application/created_me.json
COPY chinese.json /application/chinese.json
COPY japanese.json /application/japanese.json
COPY western.json /application/western.json
WORKDIR /application
ENTRYPOINT ["java","-jar"]
CMD ["application.jar"]
EXPOSE 9800