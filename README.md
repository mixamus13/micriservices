1. запуск docker compose --- docker compose up -d
2. pgAdmin http://localhost:5050/browser/ and set "password"
3. 

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