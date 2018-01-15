# booksearh

spring boot 사용
Java 1.8

사용 라이브러리 : lombok, jackson

lombok Plugin 설치 : Preferences -> Plugins -> search Lombok -> install -> restart Intellj

DB : H2 Database
JDBC URL : jdbc:h2:mem:kakao

1. Session관리를 위한 Redis 설치 후 redis server 시작 필요

Homepage Download 후 make 실행 or 아래 명령 실행

$ wget http://download.redis.io/releases/redis-4.0.6.tar.gz
$ tar xzf redis-4.0.6.tar.gz
$ cd redis-4.0.6
$ make

Start Redis Server
$ ./redis-server

2. gradle 설치 필요

설치 :

1. gradle homepage download file
2. gradle 설치된 경로 환경 변수 지정 - export PATH=$PATH:/opt/gradle/gradle-4.4.1/bin
3. 
프로젝트 실행 : 

$ gradle clean

주의 : redis server 시작 안되어있으면 Test case build Fail남.
$ gradle build

$ java -jar  -Dspring.profiles.active=localhost build/libs/booksearch-1.0.0.jar &

실행 :

url : http://localhost:8080

사용자 // 아이디 : user // password


