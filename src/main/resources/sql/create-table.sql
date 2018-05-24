

# create table

CREATE TABLE user_account_entity
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  date_created DATETIME,
  last_modified DATETIME,
  record_status INT(11) DEFAULT '0',
  remarks VARCHAR(255),
  sort_priority INT(11) DEFAULT '0' COMMENT '优先级',
  version INT(11) DEFAULT '0',
  address VARCHAR(150) COMMENT '详细地址',
  avatar_url VARCHAR(200) COMMENT '上传头像地址',
  city VARCHAR(30) COMMENT '城市',
  credit_score INT(11),
  email VARCHAR(50) COMMENT '邮箱，用于找回密码，接收通知等',
  mobile VARCHAR(100) COMMENT '用户联系电话',
  nick_name VARCHAR(20) COMMENT '昵称',
  password VARCHAR(255),
  province VARCHAR(30) COMMENT '省份',
  role VARCHAR(255),
  username VARCHAR(20) COMMENT '用户登录名,必须唯一，字母,邮箱或者手机号等',
  CONSTRAINT unique_username UNIQUE (username)
);

CREATE TABLE conversation_entity
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  date_created DATETIME,
  last_modified DATETIME,
  record_status INT(11) DEFAULT '0',
  remarks VARCHAR(255),
  sort_priority INT(11) DEFAULT '0' COMMENT '优先级',
  version INT(11) DEFAULT '0',
  browser VARCHAR(255),
  initiator_id BIGINT(20) NOT NULL COMMENT '会话发起人id',
  ip VARCHAR(255),
  os VARCHAR(255),
  recipient_id BIGINT(20) COMMENT '客服id',
  status INT(11) NOT NULL DEFAULT 0,
  username VARCHAR(255)
);

CREATE TABLE conversation_message_entity
(
  id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  date_created DATETIME,
  last_modified DATETIME,
  record_status INT(11) DEFAULT '0',
  remarks VARCHAR(255),
  sort_priority INT(11) DEFAULT '0' COMMENT '优先级',
  version INT(11) DEFAULT '0',
  conversation_id BIGINT(20) NOT NULL COMMENT '会话id',
  message VARCHAR(255),
  message_from VARCHAR(255),
  message_to VARCHAR(255),
  message_type VARCHAR(255),
  status INT(11) NOT NULL DEFAULT 0
);