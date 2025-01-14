CREATE TABLE duck (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	name varchar(255) NOT NULL,
	sex bpchar(1) NOT NULL,
	mother_id uuid NULL,
	CONSTRAINT duck_pkey PRIMARY KEY (id),
	CONSTRAINT fk_mother FOREIGN KEY (mother_id) REFERENCES duck(id)
);

CREATE TABLE customer (
	id uuid DEFAULT gen_random_uuid() NOT NULL,
	name varchar(255) NOT NULL,
	has_discount bool NOT NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id)
);


CREATE TABLE duck_children (
	parent_id uuid NOT NULL,
	child_id uuid NOT NULL,
	CONSTRAINT duck_children_pkey PRIMARY KEY (parent_id, child_id)
);

ALTER TABLE duck_children ADD CONSTRAINT fk_child FOREIGN KEY (child_id) REFERENCES duck(id);
ALTER TABLE duck_children ADD CONSTRAINT fk_parent FOREIGN KEY (parent_id) REFERENCES duck(id);