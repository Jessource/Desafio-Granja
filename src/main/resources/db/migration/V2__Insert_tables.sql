INSERT INTO duck (id, name, sex, mother_id)
VALUES
    ('db06dc5b-ab36-45b1-9774-53a6cce3b291', 'Pato avô', 'M', null),
    ('74b58487-9f2a-429d-b69e-f7d3afe2a3d9', 'Pata avó', 'F', null),
    ('06a1861c-7b84-436b-a0ff-f8898c117a3d', 'Pata mãe', 'F', '74b58487-9f2a-429d-b69e-f7d3afe2a3d9'),
    ('9531a2ee-5ec7-4dea-a59a-9ce4323d8f66', 'Pato pai', 'M', '74b58487-9f2a-429d-b69e-f7d3afe2a3d9');

INSERT INTO customer (name, has_discount) VALUES
    ('João Silva', true),
    ('Maria Oliveira', false),
    ('Carlos Santos', true),
    ('Ana Costa', false);