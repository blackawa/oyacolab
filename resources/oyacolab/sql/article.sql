-- name: find-all
select * from article

-- name: find-all-published
select * from article where article_status_id = 2
