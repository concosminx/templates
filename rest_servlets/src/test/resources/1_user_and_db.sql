CREATE USER todouser WITH PASSWORD 'todopass';

CREATE DATABASE todoapi OWNER todouser;

GRANT ALL PRIVILEGES ON DATABASE todoapi TO todouser;