delete from editor_status;
--;;
select setval ('editor_status_id_seq', 1, false);
--;;
delete from article_status;
--;;
select setval ('article_status_id_seq', 1, false);