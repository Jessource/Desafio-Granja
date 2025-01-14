BEGIN;

BEGIN;

-- Inserção de patos (Duck)
INSERT INTO public."Duck" (id, name, children, price, mother_id, sex) VALUES
('b1d3a900-6f2c-4a45-8b2e-1a28b1ec0eab', 'Pato 1', ARRAY['df3a2b50-7cfc-4c2d-b8b3-e6a21198c7a7']::uuid[], 50.00, NULL, 'M'),
('df3a2b50-7cfc-4c2d-b8b3-e6a21198c7a7', 'Pato 2', ARRAY['72cbdd93-d781-40c1-98d1-70b2fc4d0e10', 'a4f87007-91fa-4668-b5b7-c358cb15852a']::uuid[], 25.00, 'b1d3a900-6f2c-4a45-8b2e-1a28b1ec0eab', 'F'),
('72cbdd93-d781-40c1-98d1-70b2fc4d0e10', 'Pato 3', NULL, 70.00, 'df3a2b50-7cfc-4c2d-b8b3-e6a21198c7a7', 'M'),
('a4f87007-91fa-4668-b5b7-c358cb15852a', 'Pato 4', NULL, 70.00, 'df3a2b50-7cfc-4c2d-b8b3-e6a21198c7a7', 'F');

COMMIT;

-- Inserção de clientes (Customer)
INSERT INTO public." Customer" (id, name, eligible_discount) VALUES
('1e52d16a-8d1a-4528-8c9c-9127b2943a02', 'Cliente A', true),
('e8177d67-a7a5-4ba5-90c2-84b2f1ccfc83', 'Cliente B', false);

-- Inserção de vendas (Sale)
INSERT INTO public."Sale" (id, sales_date, total_value, costumer_id) VALUES
('2c3b65c9-4b63-431e-904e-64b3d1e5983f', NOW(), 75.00, '1e52d16a-8d1a-4528-8c9c-9127b2943a02'),
('b34594ea-fc36-4b08-bf27-2f96a68e0879', NOW(), 70.00, 'e8177d67-a7a5-4ba5-90c2-84b2f1ccfc83');

-- Inserção de relação pato-venda (Duck_Sale)
INSERT INTO public."Duck_Sale" (id, sale_id, duck_id) VALUES
('91ac2d92-50b9-4e3f-a76b-8ebc42c836be', '2c3b65c9-4b63-431e-904e-64b3d1e5983f', 'b1d3a900-6f2c-4a45-8b2e-1a28b1ec0eab'),
('1b8a8d1e-fc88-4dc2-9ef5-6d8263a53276', '2c3b65c9-4b63-431e-904e-64b3d1e5983f', 'df3a2b50-7cfc-4c2d-b8b3-e6a21198c7a7'),
('c6ad3c2a-0da4-4c24-83f3-604218c6d20a', 'b34594ea-fc36-4b08-bf27-2f96a68e0879', '72cbdd93-d781-40c1-98d1-70b2fc4d0e10');

COMMIT;