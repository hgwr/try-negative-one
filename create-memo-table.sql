CREATE TABLE memo (
    id SERIAL PRIMARY KEY,
    category_id INTEGER NOT NULL,
    content TEXT NOT NULL
);