--task1
WITH RECURSIVE recommenders(recommendedby, memid, firstname, surname) AS (
    SELECT recommendedby, memid, firstname, surname FROM cd.members WHERE memid = 27
  UNION ALL
    SELECT m.recommendedby,  m.memid, m.firstname, m.surname
    FROM recommenders AS r, cd.members AS m
    WHERE m.memid = r.recommendedby
)
SELECT memid, firstname, surname
FROM recommenders
WHERE memid != 27
ORDER BY memid DESC;

--task2
WITH RECURSIVE recommenders(memid) AS (
    SELECT memid FROM cd.members WHERE memid = 1
  UNION ALL
    SELECT m.memid
    FROM recommenders AS r
    JOIN cd.members AS m ON r.memid = m.recommendedby
)
SELECT m.memid, m.firstname, m.surname
from recommenders AS r
JOIN cd.members AS m ON r.memid = m.memid
WHERE m.memid != 1
ORDER BY memid;

--task3
WITH RECURSIVE recommenders(recommendedby, memid) AS (
    SELECT recommendedby, memid
   	FROM cd.members
  	UNION ALL
    SELECT m.recommendedby, r.memid
    FROM recommenders AS r
    JOIN cd.members AS m ON m.memid = r.recommendedby
)
SELECT r.memid, r.recommendedby, m.firstname, m.surname
from recommenders AS r
JOIN cd.members AS m ON r.recommendedby = m.memid
WHERE r.memid = 12 OR r.memid = 22
ORDER BY r.memid, r.recommendedby DESC;