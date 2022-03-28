1. запуск **docker compose** --- docker compose up -d
2. **pgAdmin** http://localhost:5050/browser/ and set "password"
3. start **ZIPKIN** https://zipkin.io/pages/quickstart.html
4. rabbitmq management http://localhost:15672/ login:pass -- guest
5. **Build Life Cycle** ---> https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html
6. запуск **jar file** -> java -jar eureka-server/target/eureka-server-1.0-SNAPSHOT.jar в root папке
   1. **DOCKER** --> ᐅ cd .docker
   LINKS: https://docs.spring.io/spring-boot/docs/current/reference/html/container-images.html
          https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#build-image
          https://github.com/GoogleContainerTools/jib
          https://hub.docker.com/_/eclipse-temurin

      ᐅ **STEP:** _docker build_ -> _docker run_ -> _docker pull_
      ᐅ как закидывать image в dockerhub: 1) docker logout 
                                          2) docker login 
                                          3) войти в microservice и выполнить команду равноценную package == mvn clean package -P build-docker-image
                                          4) чтобы все docker image сделать из Root проекта, вкл. Profiles maven == mvn clean package -P build-docker-image
                                          5) единственное, прописываем запуск image в docker-compose.yaml || docker compose up -d || docker compose stop
                                          6) проверить логи **docker logs apigw** || docker ps --format=$FORMAT
7. minikube start --nodes=4
   ᐅ minikube start --memory=4g ||| minikube start --driver=hyperkit --memory=4g ||| minikube set --driver=hyperkit
   ᐅ minikube ip  ------ ip master node
   ᐅ https://kubernetes.io/docs/home/
   ᐅ kubectl exec -it postgres-0 -- psql -U amigoscode --- вход в postgresql
   ᐅ kubectl get all
   ᐅ kubectl get pods -w
   ᐅ minikube tunnel  || откроет у services с типом LoadBalancer порт EXTERNAL-IP
   ᐅ ---------- установить Profile Spring - SPRING_PROFILES_ACTIVE=default
   ᐅ kubectl describe pod customer
   ᐅ kubectl logs customer -f
   ᐅ kubectl get svc
   ᐅ
   ᐅ
   ᐅ
   ᐅ





















---------------------------- для запуска postgres первичный вариант ---------------------------
services:
postgres:
container_name: postgres
image: postgres
environment:
POSTGRES_USER: amigoscode
POSTGRES_PASSWORD: password
PGDATA: /data/postgres
volumes:
- postgres:/data/postgres
ports:
- "5432:5432"
networks:
- postgres
restart: unless-stopped

pgadmin:
container_name: pgadmin
image: dpage/pgadmin4
environment:
PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}
PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-admin}
PGADMIN_CONFIG_SERVER_MODE: 'False'
volumes:
- pgadmin:/var/lib/pgadmin
ports:
- "5050:80"
networks:
- postgres
restart: unless-stopped

networks:
postgres:
driver: bridge

volumes:
postgres:
pgadmin:
-------------------------------------------------------------------------------------------