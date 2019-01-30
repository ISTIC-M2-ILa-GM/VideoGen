FROM ubuntu:18.04

# install openjdk et ffmpeg
RUN apt-get update && apt-get install openjdk-8-jdk-headless ffmpeg -y

# on ajoute le jar préalablement
ADD ./target/VideoGen-*.jar /VideoGen.jar

# on ouvre le port 8080 vers l'extérieur
EXPOSE 8080

# on lance l'app
ENTRYPOINT exec java -jar /VideoGen.jar
