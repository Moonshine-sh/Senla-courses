explain select * from person p join person_cred pc on p.id = pc.person_id where p.id = 10

explain select * from person_role pr join person p on p.id = pr.person_id join role r on r.id = pr.role_id where p.id =10

explain select * from verification_token vt join person p on p.id = vt.person_id where p.id =10

explain select * from "order" o join person p on p.id = o.person_id join status s on o.status_id = s.id where p.id = 10

explain select * from book_item bi join "order" o on o.id = bi.order_id join book b on b.id = bi.book_id where b.id = 10

explain select * from book_genre bg join book b on b.id = bg.book_id join genre g on g.id = bg.genre_id where b.id = 10

explain select * from book_author ba join book b on b.id = ba.book_id join author a on b.name = a.name where b.id = 10