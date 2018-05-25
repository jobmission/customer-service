# create table

CREATE TABLE user_account_entity
(
  id            BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username      VARCHAR(20) COMMENT '用户登录名,必须唯一，字母,邮箱或者手机号等',
  password      VARCHAR(255),
  avatar_url    VARCHAR(200) COMMENT '上传头像地址',
  email         VARCHAR(50) COMMENT '邮箱，用于找回密码，接收通知等',
  mobile        VARCHAR(100) COMMENT '用户联系电话',
  nick_name     VARCHAR(20) COMMENT '昵称',
  role          VARCHAR(255),
  date_created  DATETIME,
  last_modified DATETIME,
  record_status INT(11)                         DEFAULT '0',
  remarks       VARCHAR(255),
  sort_priority INT(11)                         DEFAULT '0'
  COMMENT '优先级',
  version       INT(11)                         DEFAULT '0',
  CONSTRAINT unique_username UNIQUE (username)
);

CREATE TABLE conversation_entity
(
  id            BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  browser       VARCHAR(255),
  initiator_id  BIGINT(20)             NOT NULL
  COMMENT '会话发起人id',
  ip            VARCHAR(255),
  os            VARCHAR(255),
  recipient_id  BIGINT(20) COMMENT '客服id',
  status        INT(11)                NOT NULL DEFAULT 0,
  username      VARCHAR(255),
  date_created  DATETIME,
  last_modified DATETIME,
  record_status INT(11)                         DEFAULT '0',
  remarks       VARCHAR(255),
  sort_priority INT(11)                         DEFAULT '0'
  COMMENT '优先级',
  version       INT(11)                         DEFAULT '0'
);

CREATE TABLE conversation_message_entity
(
  id              BIGINT(20) PRIMARY KEY NOT NULL              AUTO_INCREMENT,
  conversation_id BIGINT(20)             NOT NULL
  COMMENT '会话id',
  message         VARCHAR(255),
  author          VARCHAR(255),
  customer            TINYINT(1)             NOT NULL              DEFAULT 0,
  date_created    DATETIME,
  last_modified   DATETIME,
  record_status   INT(11)                NOT NULL              DEFAULT '0',
  remarks         VARCHAR(255),
  sort_priority   INT(11)                                      DEFAULT '0'
  COMMENT '优先级',
  version         INT(11)                                      DEFAULT '0'
);