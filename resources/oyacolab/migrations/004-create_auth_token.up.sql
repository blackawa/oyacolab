create table auth_token (
  editor_id integer,
  token text,
  primary key (editor_id, token),
  foreign key (editor_id) references editor (id)
);
