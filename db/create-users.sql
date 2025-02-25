USE downtown_tour;
CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(64) NOT NULL,
    lastname VARCHAR(64) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(320) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(25),
    address VARCHAR(255),
    fiscal_code VARCHAR(16) NOT NULL,
    privilege_csv VARCHAR(50),
    PRIMARY KEY (id),
    CONSTRAINT CONTRIBUTOR_UNIQUE_TAX UNIQUE (fiscal_code),
    CONSTRAINT CONTRIBUTOR_UNIQUE_EMAIL UNIQUE (email),
    CONSTRAINT CONTRIBUTOR_UNIQUE_PHONE UNIQUE (phone)
);

INSERT INTO `users` (`firstname`, `lastname`, `birthday`, `email`, `password`, `phone`, `address`, `fiscal_code`, `privilege_csv`)
VALUES ('ROOT', 'ROOT', '1970-01-01', 'ROOT@ROOT.ROOT', 'ROOT', NULL, NULL, 'ROOT', 'ADMIN');
