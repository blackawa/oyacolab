-- name: find-all
select * from article order by id

-- name: find-all-published
select * from article where article_status_id = 2

-- name: find-published-by-id
select * from article where id = :id and article_status_id = 2

-- name: create-article<!
insert into article
(title, content, editor_id, article_status_id)
values
(:title, :content, :editor_id, :article_status_id)

-- name: find-by-id
select * from article where id = :id

-- name: update-article!
update article
set title = :title,
    content = :content,
    editor_id = :editor_id,
    article_status_id = :article_status_id
where id = :id
