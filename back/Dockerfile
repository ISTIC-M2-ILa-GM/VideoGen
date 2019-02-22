FROM ubuntu:18.04

# install openjdk et ffmpeg
RUN apt-get update && apt-get install openjdk-8-jdk-headless ffmpeg -y

# on ajoute le jar préalablement
ADD ./target/back-*.jar /VideoGen.jar

# on ouvre le port 8080 vers l'extérieur
EXPOSE 8080

# le répertoire du videogen
VOLUME /tmp/videogen_dir

# on lance l'app
ENTRYPOINT exec java -jar /VideoGen.jar
