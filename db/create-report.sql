USE downtown_tour;
CREATE TABLE IF NOT EXISTS reports (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    content_id INT NOT NULL,
    solved BOOLEAN NOT NULL DEFAULT FALSE,
    creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (content_id) REFERENCES contents (id)
);
