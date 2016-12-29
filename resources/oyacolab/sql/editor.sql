-- name: create-editor!
insert into editor
(name, user_id, salt, password, editor_status_id)
values
(:name, :user_id, :salt, :password, :editor_status_id)
