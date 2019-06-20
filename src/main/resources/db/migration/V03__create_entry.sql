CREATE TABLE entry (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	value DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	category_id BIGINT(20) NOT NULL,
	person_id BIGINT(20) NOT NULL,
	FOREIGN KEY (category_id) REFERENCES category(id),
	FOREIGN KEY (person_id) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO entry (description, due_date, payment_date, value, note, type, category_id, person_id) values ('Monthly Payment', '2017-06-10', null, 6500.00, 'Distribution de lucros', 'INCOME', 1, 1);
INSERT INTO entry (description, due_date, payment_date, value, note, type, category_id, person_id) values ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'OUTCOME', 2, 2);
INSERT INTO entry (description, due_date, payment_date, value, note, type, category_id, person_id) values ('Top Club', '2017-06-10', null, 120, null, 'INCOME', 3, 3);
INSERT INTO entry (description, due_date, payment_date, value, note, type, category_id, person_id) values ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'INCOME', 3, 4);
INSERT INTO entry (description, due_date, payment_date, value, note, type, category_id, person_id) values ('DMAE', '2017-06-10', null, 200.30, null, 'OUTCOME', 3, 5);