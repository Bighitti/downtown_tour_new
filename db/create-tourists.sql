USE downtown_tour;
CREATE TABLE IF NOT EXISTS tourists (
    id INT NOT NULL AUTO_INCREMENT,
    uuid VARCHAR(36) NOT NULL,
    last_coord_x DOUBLE,
    last_coord_y DOUBLE,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT TOURIST_UNIQUE_UUID UNIQUE (uuid)
);
