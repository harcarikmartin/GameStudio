CREATE TABLE game (
  id INT NOT NULL PRIMARY KEY,
  name VARCHAR2(30)
);

CREATE TABLE player (
  id INT NOT NULL PRIMARY KEY,
  name VARCHAR2(30) NOT NULL 
);

CREATE TABLE score (
  id INT NOT NULL PRIMARY KEY,
  id_game INT, 
  id_player INT,
  FOREIGN KEY(id_game) REFERENCES game(id),
  FOREIGN KEY(id_player) REFERENCES player(id),
  score NUMBER(10) NOT NULL 
);

CREATE TABLE rating (
  id_game INT REFERENCES game(id), 
  id_player INT REFERENCES player(id),
  PRIMARY KEY (id_game, id_player),
  rating INT NOT NULL,
  CHECK (rating > 0 AND rating < 6)
);

CREATE TABLE comments (
  id INT NOT NULL PRIMARY KEY,
  id_game INT, 
  id_player INT,
  FOREIGN KEY(id_game) REFERENCES game(id),
  FOREIGN KEY(id_player) REFERENCES player(id),
  comments VARCHAR2(200)
);

CREATE SEQUENCE ids START WITH 1 INCREMENT BY 1;