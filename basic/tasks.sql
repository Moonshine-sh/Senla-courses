--task1
SELECT * 
FROM cd.facilities;

--task2
SELECT name, membercost 
FROM cd.facilities;

--task3
SELECT * 
FROM cd.facilities 
WHERE membercost>0;

--task4
SELECT facid, name, membercost, monthlymaintenance 
FROM cd.facilities 
WHERE membercost>0 AND membercost<monthlymaintenance/50;

--task5
SELECT * 
FROM cd.facilities 
WHERE name LIKE '%Tennis%';

--task6
SELECT * 
FROM cd.facilities
WHERE facid IN (1,5);

--task7
SELECT name,
	CASE 
		WHEN monthlymaintenance>100 THEN 'expensive'
		ELSE 'cheap'
	END AS cost
FROM cd.facilities;

--task8
SELECT memid, surname, firstname, joindate 
FROM cd.members
WHERE joindate>'2012-09-01 00:00:00';

--task9
SELECT DISTINCT surname 
FROM cd.members
ORDER BY surname
LIMIT 10;

--task10
SELECT surname
FROM cd.members 
UNION SELECT name 
FROM cd.facilities;

--task11
SELECT MAX(joindate) 
FROM cd.members;

--task12 v1
SELECT firstname, surname, joindate
FROM cd.members
ORDER BY joindate DESC
LIMIT 1

--task12 v2
SELECT firstname, surname, joindate
FROM cd.members
WHERE joindate = 
	(SELECT MAX(joindate) 
	 FROM cd.members
	 );