# init  sample data


INSERT INTO user_account_entity (id, username, password, role, nick_name)
VALUES (1, 'xyz@xyz.com', '$2a$10$OTt1dd.k71QTkwpXBOYxYO.2o0qEJ9SrYzONngijYv.7QDxtNTfvq', 'ROLE_SUPER', '秘书');

INSERT INTO user_account_entity (id, username, password, role, nick_name)
VALUES (2, 'help', '$2a$10$OTt1dd.k71QTkwpXBOYxYO.2o0qEJ9SrYzONngijYv.7QDxtNTfvq', 'ROLE_COMMISSIONER', '客服3');

INSERT INTO discussion_topic_entity (topic, sort_priority)
  value ('月季', 10), ('玫瑰', 9), ('百合', 8), ('康乃馨', 7), ('郁金香', 6);
