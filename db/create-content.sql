USE downtown_tour;
CREATE TABLE IF NOT EXISTS contents (
    id INT NOT NULL AUTO_INCREMENT,
    text VARCHAR(512) NOT NULL,
    challenge_id INT,
    publication TIMESTAMP NOT NULL,
    contributor_id INT NOT NULL,
    curator_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (challenge_id) REFERENCES challenge (id),
    FOREIGN KEY (contributor_id) REFERENCES contributors (id),
    FOREIGN KEY (curator_id) REFERENCES contributors (id)
);
