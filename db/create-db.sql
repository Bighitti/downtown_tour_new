CREATE DATABASE IF NOT EXISTS downtown_tour;
CREATE USER IF NOT EXISTS 'downtown_tour_app'@'%' IDENTIFIED BY 'downtown_tour_pas';
GRANT ALL PRIVILEGES ON downtown_tour.* TO 'downtown_tour_app'@'%';
