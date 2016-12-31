-- name: create-auth-token!
insert into auth_token
(editor_id, token)
values
(:editor_id, :token)
