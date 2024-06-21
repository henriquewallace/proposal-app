FROM openjdk:17

COPY target/proposal-app-0.0.1-SNAPSHOT.jar proposal.jar

ENTRYPOINT ["java", "-jar", "proposal.jar"]