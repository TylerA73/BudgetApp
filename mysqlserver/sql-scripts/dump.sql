ALTER USER 'root' IDENTIFIED WITH mysql_native_password BY 'password2020';
CREATE USER 'dev' IDENTIFIED WITH mysql_native_password BY 'password2020';
GRANT ALL PRIVILEGES ON budgetapp.* TO 'dev';
FLUSH PRIVILEGES;

CREATE TABLE categories (
	category_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	description varchar(200) NOT NULL
);

CREATE TABLE transactions (
	transaction_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	transaction_description varchar(2000),
	transaction_date date,
	transaction_amount decimal(10, 2),
	category_id int NOT NULL,
	FOREIGN KEY (category_id) 
		REFERENCES categories (category_id)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

INSERT INTO categories (description) values ('Income'), ('Essential'), ('Non-Essential');

