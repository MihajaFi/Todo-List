
// Create table and database

DROP DATABASE IF EXISTS todo ;

CREATE DATABASE todo ;

\c todo ;

//Create a table
CREATE TABLE todo (
  id INT PRIMARY KEY,
  title VARCHAR(255),
  description VARCHAR(255),
  deadline TIMESTAMP CHECK (CURRENT_TIMESTAMP < deadline),
  priority INT CHECK (priority >= 0 AND priority <= 10),
  done BOOLEAN DEFAULT TRUE CHECK (done IN (TRUE, FALSE))
);