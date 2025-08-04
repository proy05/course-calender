CREATE TABLE IF NOT EXISTS content (
    id INT AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,  -- @NotBlank: must have at least 1 non-whitespace char (validated in app layer)
    description TEXT,
    status VARCHAR(50) NOT NULL,
    content_type VARCHAR(50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url VARCHAR(500),
    primary key(id)
);

INSERT INTO content ( title, description, status, content_type, date_created, date_updated, url )
VALUES (
    'Spring Boot Guide Blog Post',
    'A comprehensive guide to Spring Boot features and setup.',
    'IDEA',
    'ARTICLE',
    CURRENT_TIMESTAMP,
    null,
    'https://example.com/spring-boot-guide'
);
