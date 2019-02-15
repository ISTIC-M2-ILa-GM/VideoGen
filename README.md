# VideoGen

## Binômes
* Gwénolé LE HENAF
* Hugo BANNIER

## Technologies
* Spring (back)
* Angular (front)
* docker

## Projet

[Sujet](https://docs.google.com/document/d/1_PBrBHf9irX9g8LcRIlRNAVC08lXCZNp3w8dWrpFIPg/edit#)

## Démarrer le projet

### Pré-requis
* [JDK 8](https://openjdk.java.net/)
* [Maven 3](https://maven.apache.org/)
* [NodeJS 10](https://nodejs.org/en/)
* Npm 6

## Simuler la prod

```bash
npm install -g @angular/cli@7.2.3
git clone https://github.com/ISTIC-M2-ILa-GM/VideoGen.git
cd VideoGen/back
# Build le back (spring-boot)
mvn clean package
cd ../front
# Installe l'utilitaire angular-cli
npm install -g @angular/cli@7.2.3
# Récupère les dépendances
npm install 
# Build le front (Angular)
ng build --prod
cd ..
# Démarre le back et le front
docker-compose up

```

L'application est disponible à l'url : [http://localhost](http://localhost)

