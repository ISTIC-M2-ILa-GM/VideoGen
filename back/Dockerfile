FROM openjdk:8-jre-alpine

# on ajoute le jar préalablement
ADD ./target/VideoGen-*.jar /VideoGen.jar

# on ouvre le port 8080 vers l'extérieur
EXPOSE 8080

# on lance l'app
ENTRYPOINT exec java -jar /VideoGen.jar
