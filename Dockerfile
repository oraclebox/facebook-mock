FROM anapsix/alpine-java:8
RUN mkdir /application
RUN mkdir -p /application/logs
COPY build/libs/facebook-login-mock-SNAPSHOT.jar /application/application.jar
COPY application.yml /application/application.yml
RUN touch /application/created_me.json
RUN echo '{}' >> /application/created_me.json
RUN touch /application/user_images.json
RUN echo '{}' >> /application/user_images.json
COPY chinese.json /application/chinese.json
COPY japanese.json /application/japanese.json
COPY western.json /application/western.json
COPY sample /application/sample
WORKDIR /application
ENTRYPOINT ["java","-jar"]
CMD ["application.jar"]
EXPOSE 9800