INSERT INTO duck (id, name, sex, mother_id)
VALUES
    ('db06dc5b-ab36-45b1-9774-53a6cce3b291', 'Grandpa duck', 'M', null),
    ('74b58487-9f2a-429d-b69e-f7d3afe2a3d9', 'Grandma duck', 'F', null),
    ('06a1861c-7b84-436b-a0ff-f8898c117a3d', 'Mother duck', 'F', '74b58487-9f2a-429d-b69e-f7d3afe2a3d9'),
    ('9531a2ee-5ec7-4dea-a59a-9ce4323d8f66', 'Father duck', 'M', '74b58487-9f2a-429d-b69e-f7d3afe2a3d9');

INSERT INTO duck_children (parent_id, child_id)
VALUES
    ('db06dc5b-ab36-45b1-9774-53a6cce3b291', '06a1861c-7b84-436b-a0ff-f8898c117a3d'),
    ('db06dc5b-ab36-45b1-9774-53a6cce3b291', '9531a2ee-5ec7-4dea-a59a-9ce4323d8f66'),
    ('74b58487-9f2a-429d-b69e-f7d3afe2a3d9', '06a1861c-7b84-436b-a0ff-f8898c117a3d'),
    ('74b58487-9f2a-429d-b69e-f7d3afe2a3d9', '9531a2ee-5ec7-4dea-a59a-9ce4323d8f66');