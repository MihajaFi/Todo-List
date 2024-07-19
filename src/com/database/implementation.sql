
<<<<<<< HEAD
-- Create table and database

=======
>>>>>>> 7b2a3a9641bb28b7e1a650247d6a68232c2409c2
DROP DATABASE IF EXISTS todo ;

CREATE DATABASE todo ;

\c todo ;

<<<<<<< HEAD
-- Create a table
=======
>>>>>>> 7b2a3a9641bb28b7e1a650247d6a68232c2409c2
CREATE TABLE todo (
  id INT PRIMARY KEY,
  title VARCHAR(255),
  description VARCHAR(255),
  deadline TIMESTAMP CHECK (CURRENT_TIMESTAMP < deadline),
  priority INT CHECK (priority >= 0 AND priority <= 10),
  done BOOLEAN DEFAULT TRUE CHECK (done IN (TRUE, FALSE))
);