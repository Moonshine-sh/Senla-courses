--task1
SELECT COUNT(facid) 
FROM cd.facilities;

--task2
SELECT COUNT(facid)
FROM cd.facilities
WHERE guestcost>=10;

--task3
SELECT recommendedby, COUNT(recommendedby)
FROM cd.members
WHERE recommendedby IS NOT NULL
GROUP BY recommendedby
ORDER BY recommendedby;

--task4
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
ORDER BY facid;

--task5
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE starttime >= '2012-09-01' AND starttime < '2012-10-01'
GROUP BY facid
ORDER BY "Total Slots";

--task6
SELECT facid, EXTRACT(MONTH FROM starttime) AS month, SUM(slots) AS "Total Slots"
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
ORDER BY facid, month;

--task7
SELECT COUNT(mems)
FROM (SELECT DISTINCT memid FROM cd.bookings) AS mems;

--task8
SELECT facid, SUM(slots) AS "Total Slots"
FROM cd.bookings
GROUP BY facid
HAVING SUM(slots) > 1000
ORDER BY facid;

--task9
SELECT f.name, SUM(
	CASE
		WHEN b.memid = 0 THEN
		b.slots*f.guestcost
		ELSE
		b.slots*f.membercost
	END) AS revenue
FROM cd.bookings AS b
JOIN cd.facilities AS f ON b.facid = f.facid
GROUP BY f.name
ORDER BY revenue;

--task10
SELECT fname, revenue
FROM (SELECT f.name AS fname, SUM(
		CASE
			WHEN b.memid = 0 THEN
			b.slots*f.guestcost
			ELSE
			b.slots*f.membercost
		END) AS revenue
		FROM cd.bookings AS b
		JOIN cd.facilities AS f ON b.facid = f.facid
	 	GROUP BY f.name) AS stats
WHERE revenue < 1000
ORDER BY revenue;

--task11
SELECT id ,slot
FROM (SELECT facid AS id, SUM(slots) AS slot
  FROM cd.bookings
  GROUP BY facid
  ) AS total
WHERE slot = (
	  SELECT MAX(slot) 
	  FROM (
		SELECT facid AS id, SUM(slots) AS slot
  		FROM cd.bookings
  		GROUP BY facid
		) AS totals
	)
	
--task12
SELECT facid, EXTRACT(MONTH FROM starttime) AS month, SUM(slots)
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid, month
UNION
SELECT facid, null, SUM(SLOTS)
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
GROUP BY facid
UNION
SELECT null, null, SUM(SLOTS)
FROM cd.bookings
WHERE EXTRACT(YEAR FROM starttime) = 2012
ORDER BY facid, month;

--task13
SELECT f.facid AS facid, f.name AS name, CAST(SUM(b.slots) / 2.0 AS decimal(10,2)) AS total
FROM cd.facilities AS f
JOIN cd.bookings AS b ON f.facid = b.facid
GROUP BY f.facid, f.name
ORDER BY f.facid;

--task14
SELECT m.surname, m.firstname, m.memid, strtime.stime
FROM cd.members AS m
JOIN (
  SELECT nm.memid AS mid, MIN(b.starttime) AS stime
  FROM cd.members AS nm
  JOIN cd.bookings AS b ON nm.memid = b.memid
  WHERE b.starttime >= '2012-09-01'
  GROUP BY mid
  ORDER BY mid
  ) AS strtime ON strtime.mid = m.memid
WHERE strtime.stime >= '2012-09-01'
ORDER BY m.memid ASC;

--task15
SELECT (SELECT COUNT(memid) FROM cd.members) AS count, firstname, surname FROM cd.members;

--task16
SELECT row_number() OVER (ORDER BY joindate) n, firstname, surname
FROM cd.members;

--task17
SELECT facid, total
FROM (
  SELECT facid, SUM(slots) AS total
  FROM cd.bookings
  GROUP BY facid
  ) AS totals
WHERE total = (
  SELECT MAX(total)
  FROM (
	  SELECT facid, SUM(slots) AS total
	  FROM cd.bookings
	  GROUP BY facid
	) AS totalss
  );
  
--task18
SELECT m.firstname, m.surname, h.hours, h.rank
FROM (
  SELECT memid, ROUND(SUM(slots)/2,-1) AS hours, rank() OVER (ORDER BY ROUND(SUM(slots)/2,-1) DESC) AS rank
  FROM cd.bookings
  GROUP BY memid
  ) AS h
JOIN cd.members AS m ON h.memid = m.memid
ORDER BY rank, surname, firstname;

--task19
SELECT name, rank
FROM (
  SELECT f.name AS name, rank() OVER (ORDER BY SUM(
	  CASE
		  WHEN b.memid = 0 THEN
		  b.slots*f.guestcost
		  ELSE
		  b.slots*f.membercost
	  END) DESC) AS rank
  FROM cd.bookings AS b
  JOIN cd.facilities AS f ON b.facid = f.facid
  GROUP BY f.name) AS r
WHERE rank<4;

--task20
SELECT name, CASE
	WHEN class=1 THEN 'high'
	WHEN class=2 THEN 'average'
	ELSE 'low'
	END AS revenue
FROM (
  SELECT f.name AS name, ntile(3) OVER (ORDER BY SUM(
	  CASE
		  WHEN b.memid = 0 THEN
		  b.slots*f.guestcost
		  ELSE
		  b.slots*f.membercost
	  END) DESC) AS class
  FROM cd.bookings AS b
  JOIN cd.facilities AS f ON b.facid = f.facid
  GROUP BY f.name) AS r
GROUP BY name, class
ORDER BY class, name;

--task21
SELECT f.name, f.initialoutlay/((r.revenue-3*f.monthlymaintenance)/3)
FROM(
  SELECT b.facid AS facid, SUM(
	  CASE
		  WHEN b.memid = 0 THEN
		  b.slots*f.guestcost
		  ELSE
		  b.slots*f.membercost
	  END) AS revenue
  FROM cd.bookings AS b
  JOIN cd.facilities AS f ON b.facid = f.facid
  WHERE EXTRACT(YEAR FROM starttime) = 2012
  GROUP BY b.facid
  ORDER BY facid) AS r
JOIN cd.facilities AS f on r.facid = f.facid
ORDER BY f.name;

--task22
select 	dategen.date,
	(
		select sum(case
			when memid = 0 then slots * facs.guestcost
			else slots * membercost
		end) as rev

		from cd.bookings bks
		inner join cd.facilities facs
			on bks.facid = facs.facid
		where bks.starttime > dategen.date - interval '14 days'
			and bks.starttime < dategen.date + interval '1 day'
	)/15 as revenue
	from
	(
		select 	cast(generate_series(timestamp '2012-08-01',
			'2012-08-31','1 day') as date) as date
	)  as dategen
order by dategen.date; 