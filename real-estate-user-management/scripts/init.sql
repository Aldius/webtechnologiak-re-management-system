CREATE TABLE users 
(
	id INT,
	email VARCHAR(255),
	username VARCHAR(255),
	password VARCHAR(255),
	full_name VARCHAR(255)
);

CREATE TABLE roles
(
	id INT,
	name VARCHAR(255),
	display_name VARCHAR(255)
);

INSERT INTO roles values(1, 'ROLE1', 'ROLE1');
INSERT INTO roles values(2, 'ROLE2', 'ROLE2');
INSERT INTO roles values(3, 'ROLE3', 'ROLE3');

CREATE TABLE assigned_roles
(
	id INT,
	user_id INT,
	role_id INT
);