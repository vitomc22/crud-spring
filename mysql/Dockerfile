FROM mysql/mysql-server
LABEL authors="mysql-server-db"

ARG ROOT_PASSWORD=123
ENV MYSQL_ROOT_PASSWORD=${ROOT_PASSWORD}

ARG SETUP_DATABASE=CrudAngular
ENV MYSQL_DATABASE=${SETUP_DATABASE}

ARG SETUP_REMOTE_USERNAME=victor
ARG SETUP_REMOTE_PASSWORD=123


RUN echo "CREATE DATABASE ${SETUP_DATABASE}; CREATE USER '${SETUP_REMOTE_USERNAME}'@'localhost' IDENTIFIED BY '${SETUP_REMOTE_PASSWORD}';GRANT ALL PRIVILEGES ON *.* TO '${SETUP_REMOTE_USERNAME}'@'localhost' WITH GRANT OPTION; FLUSH PRIVILEGES;" > /docker-entrypoint-initdb.d/_grant_remote.sql

EXPOSE 3306

CMD ["mysqld"]