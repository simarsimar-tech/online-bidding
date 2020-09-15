DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS item;

CREATE TABLE item (
  item_code VARCHAR(250) PRIMARY KEY,
  base_price DOUBLE NOT NULL,
  step_rate DOUBLE NOT NULL,
  current_price DOUBLE,
  status VARCHAR(250) DEFAULT NULL,
  created_date date DEFAULT CURRENT_DATE,
  updated_date date DEFAULT CURRENT_DATE
);

CREATE TABLE events (
  id INT(11) unsigned NOT NULL AUTO_INCREMENT,
  user VARCHAR(250) NOT NULL,
  status VARCHAR(250) NOT NULL,
  item_code VARCHAR(250) NOT NULL,
  created_date date DEFAULT CURRENT_DATE,
  PRIMARY KEY (id),
  FOREIGN KEY (item_code) REFERENCES item(item_code)
);

INSERT INTO item (item_code, base_price, step_rate, status) VALUES
  ('CODE_01', 1000, 250, 'running'),
  ('CODE_02', 1200, 500, 'running'),
  ('CODE_03', 4000, 1000, 'running'),
  ('CODE_04', 8000, 2000, 'over');