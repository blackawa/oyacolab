create table article_status (
  id serial primary key,
  description text not null
);
--;;
create table article (
  id serial primary key,
  title text not null,
  content text not null,
  thumbnail_small text,
  thumbnail_large text,
  article_status_id integer not null,
  foreign key (article_status_id) references article_status (id)
);