CREATE TABLE duck (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	name varchar(255) NOT NULL,
	sex bpchar(1) NOT NULL,
	mother_id uuid NULL,
	available boolean NOT NULL DEFAULT true,
	sale_value DECIMAL(10, 2) NULL,
	CONSTRAINT duck_pkey PRIMARY KEY (id),
	CONSTRAINT fk_mother FOREIGN KEY (mother_id) REFERENCES duck(id)
);

CREATE TABLE customer (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	name varchar(255) NOT NULL,
	has_discount bool NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);

CREATE TABLE sale (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_id UUID NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE sale_duck (
    sale_id UUID NOT NULL,
    duck_id UUID NOT NULL,
    PRIMARY KEY (sale_id, duck_id),
    CONSTRAINT fk_sale FOREIGN KEY (sale_id) REFERENCES sale (id),
    CONSTRAINT fk_duck FOREIGN KEY (duck_id) REFERENCES duck (id)
);