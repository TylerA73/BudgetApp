FROM openjdk:14
WORKDIR /usr/app
COPY . .
EXPOSE 8080
WORKDIR /usr/app/budget
RUN chmod +x /usr/app/budget/gradlew
CMD [ "/usr/app/budget/gradlew", "bootRun" ]
