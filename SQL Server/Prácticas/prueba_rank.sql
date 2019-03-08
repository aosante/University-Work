CREATE TABLE juegos 
(  
    nombre          varchar(100), 
    plataforma      varchar(100),
    rating          int
); 
GO 

INSERT juegos (nombre, plataforma, rating) 
VALUES 
    ('Duck Dash', 'Android', 4),
    ('The Square', 'Android', 4),
    ('Hit Brick', 'Android', 4),
    ('Go Bunny', 'iOS', 5),
    ('Perfect Time', 'Windows Phone', 6),
    ('First Finish', 'Windows Phone', 7),
    ('Froggy Adventure', 'iOS', 7),
    ('Speed Race', 'iOS', 7),
    ('Shoot in Time', 'Android', 9),
    ('Monsters in Dungeon', 'Android', 9),
    ('Fire Rescue', 'iOS', 9),
    ('Eternal Stone', 'iOS', 10)
GO

SELECT ROW_NUMBER() OVER(ORDER BY rating DESC) AS num_fila,
nombre, plataforma, rating,
RANK() OVER(ORDER BY rating DESC ) AS rank,
DENSE_RANK() OVER(ORDER BY  rating DESC ) AS dense_rank
FROM juegos;


SELECT RANK() OVER(ORDER BY rating ASC) AS rank,
    nombre, plataforma, rating
FROM juegos;

