CREATE TABLE category(

    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50),

    CONSTRAINT uk_category_name UNIQUE (name)
)