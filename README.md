# Content of Project
* [General info](#general-info)
* [Technologies](#technologies)
* [More detailed information about modules](#more-detailed-information-about-modules)

## General info

Public repository for testing CI/CD application development with external hosting service - Render. This will be backbone of my next project, currency exchange web application with REST API.

## Technologies

Java 17, Spring 3.1.0, PosgreSQL 15, Maven, Docker, GitHub, Render.

## More detailed information about project

Every push action to the main repository triggers Render's deploy process. The service uses docker to build and deploy Spring application. All sensitive data like database connection credential are stored in local enviroment variables and Render's web service secrets.
