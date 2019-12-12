CREATE TABLE user (
	id BIGINT(20) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permission (
	id BIGINT(20) PRIMARY KEY,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_permission (
	user_id BIGINT(20) NOT NULL,
	permission_id BIGINT(20) NOT NULL,
	PRIMARY KEY (user_id, permission_id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (permission_id) REFERENCES permission(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (id, name, email, password) values (1, 'Administrator', 'admin@moneyapi.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO user (id, name, email, password) values (2, 'Thiago Medeiros', 'maria@moneyapi.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permission (id, description) values (1, 'ROLE_REGISTER_CATEGORY');
INSERT INTO permission (id, description) values (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO permission (id, description) values (3, 'ROLE_REGISTER_PERSON');
INSERT INTO permission (id, description) values (4, 'ROLE_REMOVE_PERSON');
INSERT INTO permission (id, description) values (5, 'ROLE_SEARCH_PERSON');

INSERT INTO permission (id, description) values (6, 'ROLE_REGISTER_ENTRY');
INSERT INTO permission (id, description) values (7, 'ROLE_REMOVE_ENTRY');
INSERT INTO permission (id, description) values (8, 'ROLE_SEARCH_ENTRY');

-- Admin
INSERT INTO user_permission (user_id, permission_id) values (1, 1);
INSERT INTO user_permission (user_id, permission_id) values (1, 2);
INSERT INTO user_permission (user_id, permission_id) values (1, 3);
INSERT INTO user_permission (user_id, permission_id) values (1, 4);
INSERT INTO user_permission (user_id, permission_id) values (1, 5);
INSERT INTO user_permission (user_id, permission_id) values (1, 6);
INSERT INTO user_permission (user_id, permission_id) values (1, 7);
INSERT INTO user_permission (user_id, permission_id) values (1, 8);

-- Thiago
INSERT INTO user_permission (user_id, permission_id) values (2, 2);
INSERT INTO user_permission (user_id, permission_id) values (2, 5);
INSERT INTO user_permission (user_id, permission_id) values (2, 8);