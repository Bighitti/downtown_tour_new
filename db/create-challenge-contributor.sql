USE downtown_tour;
CREATE TABLE IF NOT EXISTS challenge_contributor (
    challenge_id INT NOT NULL,
    contributor_id INT NOT NULL,
    PRIMARY KEY (challenge_id, contributor_id),
    FOREIGN KEY (challenge_id) REFERENCES challenges (id),
    FOREIGN KEY (contributor_id) REFERENCES contributors (id)
);
