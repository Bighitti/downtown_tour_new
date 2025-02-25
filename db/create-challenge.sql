USE downtown_tour;
CREATE TABLE IF NOT EXISTS challenges (
    id INT NOT NULL AUTO_INCREMENT,
    c_name VARCHAR(512) NOT NULL,
    description VARCHAR(512) NOT NULL,
    max_players INT NOT NULL,
    c_start TIMESTAMP NOT NULL,
    c_end TIMESTAMP NOT NULL,
    c_open BOOLEAN NOT NULL DEFAULT FALSE,
    entertainer_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (entertainer_id) REFERENCES users (id)
);
