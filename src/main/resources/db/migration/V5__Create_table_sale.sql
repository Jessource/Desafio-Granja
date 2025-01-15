
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
