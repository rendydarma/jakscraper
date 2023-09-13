CREATE TABLE t_file(
	id serial,
	file_content text NOT NULL,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE t_store(
	id serial,
	store_name varchar(30) NOT NULL,
	url text NOT NULL,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE t_supplier(
	id serial,
	supplier_name varchar(30) NOT NULL,
	url text NOT NULL,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE (supplier_name)
);

CREATE TABLE t_product(
	id serial,
	supplier_id int NOT NULL,
	product_name varchar NOT NULL,
	sku varchar NOT NULL,
	price int NOT NULL,
	in_stock boolean NOT NULL,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (supplier_id)
		REFERENCES t_supplier (id),
	UNIQUE (sku)
);

CREATE TABLE t_image(
	id serial,
	product_id int,
	file_id int,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (product_id)
		REFERENCES t_product (id),
	FOREIGN KEY (file_id)
		REFERENCES t_file (id)
);

CREATE TABLE t_invoice(
	id serial,
	code varchar(30) NOT NULL,
	store_id int NOT NULL,
	buyer_name varchar(30) NOT NULL,
	buyer_address text NOT NULL,
	grand_total int NOT NULL,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (store_id)
		REFERENCES t_store (id),
	UNIQUE (code)
);

CREATE TABLE t_invoice_detail(
	id serial,
	invoice_id int NOT NULL,
	product_id int NOT NULL,
	qty int NOT NULL,
	price int NOT NULL,
	total_price int NOT NULL,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (invoice_id)
		REFERENCES t_invoice (id),
	FOREIGN KEY (product_id)
		REFERENCES t_product (id),
	UNIQUE (invoice_id, product_id)
);

CREATE TABLE t_user(
	id serial,
	email varchar(30),
	pass text,
	created_at timestamp NOT NULL,
	created_by int NOT NULL,
	updated_at timestamp,
	updated_by int,
	is_active bool NOT NULL,
	ver int NOT NULL
);

INSERT INTO t_user(email,pass,created_at,created_by,is_active,ver) VALUES ('rdarma2000@gmail.com', '123', NOW(), 1, TRUE, 0);