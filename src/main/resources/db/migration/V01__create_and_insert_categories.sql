CREATE TABLE categories (
    id bigint PRIMARY KEY auto_increment,
    name varchar(50) NOT NULL
) ENGINE = InnoDB DEFAULT charset = UTF8;

INSERT INTO categories (name) VALUES ('Leizure');
INSERT INTO categories (name) VALUES ('Food');
INSERT INTO categories (name) VALUES ('Market');
INSERT INTO categories (name) VALUES ('Pharmacy');
INSERT INTO categories (name) VALUES ('Other');