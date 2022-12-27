--task1
SELECT b.starttime
FROM cd.bookings AS b
JOIN cd.members AS m on b.memid = m.memid
WHERE m.firstname = 'David' AND m.surname = 'Farrell';

--task2
SELECT b.starttime AS start, f.name 
FROM cd.bookings AS b
JOIN cd.facilities AS f on b.facid = f.facid
WHERE f.name LIKE 'Tennis Court%' 
AND b.starttime >= '2012-09-21 00:00:00' AND b.starttime <= '2012-09-21 23:59:59';

--task3
SELECT DISTINCT nm.firstname, nm.surname
FROM cd.members AS m
JOIN cd.members AS nm ON nm.memid = m.recommendedby
ORDER BY nm.surname, nm.firstname;

--task4
SELECT m.firstname AS memfname, m.surname AS memsname, nm.firstname AS recfname, nm.surname AS recsname
FROM cd.members AS m
LEFT JOIN cd.members AS nm ON nm.memid = m.recommendedby
ORDER BY m.surname, m.firstname;

--task5
SELECT DISTINCT CONCAT(m.firstname,' ',m.surname) AS member, f.name AS facility 
FROM cd.members AS m
JOIN cd.bookings AS b ON b.memid = m.memid
JOIN cd.facilities AS f ON f.facid = b.facid
WHERE f.name LIKE 'Tennis Court%'
ORDER BY member, facility;

--task6
SELECT CONCAT(m.firstname,' ',m.surname) AS member, f.name AS facility, 
	CASE 
		WHEN b.memid = 0 THEN f.guestcost*b.slots
		ELSE f.membercost*b.slots
	END AS cost
FROM cd.bookings AS b
JOIN cd.members AS m ON m.memid = b.memid
JOIN cd.facilities AS f ON f.facid = b.facid
WHERE 
	b.starttime >= '2012-09-14' AND 
	b.starttime < '2012-09-15' AND (
		(b.memid = 0 AND f.guestcost*b.slots > 30) OR 
		(b.memid != 0 AND f.membercost*b.slots > 30)
	)
ORDER BY cost DESC;

--task7
SELECT DISTINCT CONCAT(m.firstname,' ',m.surname) AS member, 
	(SELECT CONCAT(rm.firstname,' ',rm.surname) AS recommember
		FROM cd.members AS rm
	 	WHERE rm.memid = m.recommendedby
	)
FROM cd.members AS m
ORDER BY member;

--task8
SELECT member, facility, cost FROM (
	SELECT 
		m.firstname || ' ' || m.surname AS member,
		f.name AS facility,
		CASE
			WHEN m.memid = 0 THEN
				b.slots*f.guestcost
			ELSE
				b.slots*f.membercost
		END AS cost
		FROM
			cd.members AS m
			JOIN cd.bookings b
				ON m.memid = b.memid
			JOIN cd.facilities AS f
				ON b.facid = f.facid
		WHERE
			b.starttime >= '2012-09-14' and
			b.starttime < '2012-09-15'
	) AS bookings
	WHERE cost > 30
ORDER BY cost DESC; 