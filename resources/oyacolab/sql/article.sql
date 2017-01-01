-- name: find-all
select * from article

-- name: find-all-published
select * from article where article_status_id = 2

-- name: create-article<!
insert into article
(title, content, editor_id, article_status_id)
values
(:title, :content, :editor_id, :article_status_id)
