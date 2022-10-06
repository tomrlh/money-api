CREATE TABLE people (
    id bigint PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL,
    active boolean DEFAULT false
) ENGINE = InnoDB DEFAULT charset = UTF8;

INSERT INTO people (name, active) VALUES ('P1', true);
INSERT INTO people (name) VALUES ('P2');
INSERT INTO people (name, active) VALUES ('P3', false);
INSERT INTO people (name, active) VALUES ('P4', false);
INSERT INTO people (name, active) VALUES ('P5', true);