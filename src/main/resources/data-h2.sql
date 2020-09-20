DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS roles;


CREATE TABLE item (
  item_code VARCHAR(250) PRIMARY KEY,
  base_price DOUBLE NOT NULL,
  step_rate DOUBLE NOT NULL,
  current_price DOUBLE,
  status VARCHAR(250) DEFAULT NULL,
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE event (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL,
  item_code VARCHAR(250) NOT NULL,
  bidding_price DOUBLE NOT NULL,
  created_date DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (item_code) REFERENCES item(item_code)
);

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) NOT NULL,
  token VARCHAR(250) DEFAULT ''
);

INSERT INTO item (item_code, base_price, step_rate, status) VALUES
  ('CODE_01', 1000, 250, 'running'),
  ('CODE_02', 1200, 500, 'running'),
  ('CODE_03', 4000, 1000, 'running'),
  ('CODE_04', 8000, 2000, 'over');


INSERT INTO user (user_name, password, role) VALUES
  ('admin', 'admin', 'admin'),
  ('simarjeet', 'simarjeet', 'bidder'),
  ('tanvi', 'tanvi', 'bidder');
