USE downtown_tour;
CREATE TABLE IF NOT EXISTS contributors (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    curator_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (curator_id) REFERENCES contributors (id),
    CONSTRAINT CONTRIBUTOR_UNIQUE_USER UNIQUE (user_id)
);
