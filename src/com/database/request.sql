//Add a todo

INSERT INTO todo (id , title , description , deadline , priority , done) VALUES
(?  ,? ,? ,?,?,? ) ;

//Show a todo by id
SELECT * FROM todo WHERE id = ? ;

//Show all todo
SELECT * FROM todo  ;

//UPDATE a todo
UPDATE todo SET title = ?, description = ?, deadline = ?, priority = ?, done = ? WHERE id = ? ;

//Delete a todo by id ;
DELETE FROM todo WHERE id = ? ;

