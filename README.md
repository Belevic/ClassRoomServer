# ClassRoomServer
Server side of application classroom
Developed with using Java 17, Spring Boot, PostgreSQL as database provider, Maven as build tool and WebSockets.

Steps for project setup:
1) Download source code from Github repository.
2) Open project in IDE.
3) Open query tool of PostgreSQL and start script of creating database that is located in folder recourses/script in project.
4) Change application.yaml properties for your own need (by default port for spring app: 8080) set for example your PostgreSQL port, username and password.
5) Run mamven clean install for downloading external dependencies that are not located on your local .m2 folder.
6) Configure JDK in project settings (17 used in project).
7) Start appliction in IDE.

Extrenl libraries that are used in project are:
1) Starter for Jpa for database provider work.
2) Starter for Tomcat for deployment on embedded Tomcat.
3) Starter for web for recieving http request.
4) Starter for websockets.
5) PostgreSQL as database provider.
6) Lombok for convinient work with POJO.

All project classes are covered with JavaDoc comments that you cn check in source code.
