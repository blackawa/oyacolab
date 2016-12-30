-- name: create-editor!
insert into editor
(username, password, editor_status_id)
values
(:username, :password, :editor_status_id)
