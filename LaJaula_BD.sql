CREATE DATABASE lajaula_db;

USE lajaula_db;

CREATE TABLE partides (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_jugador VARCHAR(100) NOT NULL,
    cintes_recollides INT DEFAULT 0,
    pilotes_recollides INT DEFAULT 0,
    sabatilles_recollides INT DEFAULT 0,
    vides_restants INT DEFAULT 0,
    data_hora DATETIME DEFAULT CURRENT_TIMESTAMP
);
select * from partides;