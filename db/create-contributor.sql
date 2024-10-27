USE downtown_tour;
CREATE TABLE IF NOT EXISTS contributor (
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(64) NOT NULL,
    lastname VARCHAR(64) NOT NULL,
    birthday DATE NOT NULL,
    email VARCHAR(320) NOT NULL,
    password VARCHAR(255),
    phone VARCHAR(25),
    address VARCHAR(255),
    fiscal_code VARCHAR(16) NOT NULL,
    privilege VARCHAR(50),
    curator BOOL NOT NULL DEFAULT FALSE,
    authorized_by INT,
    PRIMARY KEY (id),
    FOREIGN KEY (authorized_by) REFERENCES contributor (id),
    CONSTRAINT CONTRIBUTOR_UNIQUE_TAX UNIQUE (fiscal_code),
    CONSTRAINT CONTRIBUTOR_UNIQUE_EMAIL UNIQUE (email)
);
