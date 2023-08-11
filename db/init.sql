-- CREATE USER zaki WITH PASSWORD 'zaki';
-- CREATE DATABASE zaki;
-- GRANT ALL PRIVILEGES ON DATABASE zaki TO zaki;

-- \c zaki

-- CREATE TYPE
DROP TYPE IF EXISTS user_status;
CREATE TYPE user_status AS ENUM (
    'ACTIVE',
    'INACTIVE'
);

-- CREATE TABLE
DROP TABLE IF EXISTS tbl_user;
CREATE TABLE tbl_user (
    id SERIAL PRIMARY KEY,
    nama_lengkap VARCHAR(255),
    username VARCHAR(255),
    password VARCHAR(255),
    status user_status,
);