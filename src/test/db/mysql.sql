CREATE TABLE users (
  id         INTEGER      NOT NULL AUTO_INCREMENT,
  name       VARCHAR(255) NOT NULL,
  birth_date DATE         NOT NULL,
  nickname   VARCHAR(32)  NULL,
  male       BIT(1)       NULL,
  PRIMARY KEY (id)
)
  ENGINE = innodb
  CHARACTER SET utf8
  COLLATE utf8_bin;

CREATE TABLE addresses (
  id       INTEGER      NOT NULL AUTO_INCREMENT,
  street   VARCHAR(255) NOT NULL,
  state    VARCHAR(255) NOT NULL,
  zipcode  VARCHAR(255) NOT NULL,
  users_id INTEGER      NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT user_addresses_fk_1 FOREIGN KEY (users_id) REFERENCES users (id)
)
  ENGINE = innodb
  CHARACTER SET utf8
  COLLATE utf8_bin;