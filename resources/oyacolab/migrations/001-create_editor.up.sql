create table editor_status (
  id serial primary key,
  description text not null
);
--;;
create table editor (
  id serial primary key,
  name varchar(128) not null,
  user_id varchar(32) not null,
  salt varchar(12) not null,
  password varchar(64) not null,
  editor_status_id integer not null,
  foreign key (editor_status_id) references editor_status (id)
);
