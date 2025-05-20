# Free-Proyect
#  La Jaula - Juego 2D en Java Swing

**La Jaula** es un videojuego arcade hecho en **Java** usando **Swing**, donde el jugador debe moverse por un mapa, evitar enemigos y recoger objetos deportivos. El progreso de la partida se guarda automáticamente en una base de datos MySQL.

---

##  Características del juego

- Introducción del nombre del jugador
- Enemigos que se mueven automáticamente (horizontal o vertical)
-  Recolección de objetos:
  - Cintas de pelo
  - Pelotas
  - Zapatillas
-  Sistema de vidas con corazones visuales
-  Registro de partidas en base de datos MySQL
-  Pantalla de victoria y de game over
-  Conexión a base de datos: guarda nombre, objetos recogidos, vidas y fecha/hora

---

## 🧑 Tecnologías utilizadas

- Lenguaje: **Java**
- Librería gráfica: **Java Swing**
- Base de datos: **MySQL**
- Conexión DB: **JDBC (mysql-connector-j.jar)**

---

## Estructura del proyecto


##  Configuración de la base de datos

### Script de creación de la tabla `partides`:

```sql
CREATE DATABASE lajaula_db;

USE lajaula_db;

CREATE TABLE partides (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom_jugador VARCHAR(100),
  cintas_recollides INT,
  pilotes_recollides INT,
  sabatilles_recollides INT,
  vides_restants INT,
  data_hora TIMESTAMP
);