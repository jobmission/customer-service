# create table
# 用户表
CREATE TABLE IF NOT EXISTS user_account_entity
(
  id            BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username      VARCHAR(20) COMMENT '用户登录名,必须唯一，字母,邮箱或者手机号等',
  password      VARCHAR(255),
  avatar_url    VARCHAR(200) COMMENT '上传头像地址',
  email         VARCHAR(50) COMMENT '邮箱，用于找回密码，接收通知等',
  mobile        VARCHAR(100) COMMENT '用户联系电话',
  nick_name     VARCHAR(20) COMMENT '昵称',
  role          VARCHAR(255),
  date_created  DATETIME                        DEFAULT CURRENT_TIMESTAMP,
  last_modified DATETIME                        DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  record_status INT(11)                         DEFAULT '0',
  remarks       VARCHAR(255),
  sort_priority INT(11)                         DEFAULT '0'
  COMMENT '优先级',
  version       INT(11)                         DEFAULT '0',
  CONSTRAINT unique_username UNIQUE (username)
);

# 会话表
CREATE TABLE IF NOT EXISTS conversation_entity
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
  date_created  DATETIME                        DEFAULT CURRENT_TIMESTAMP,
  last_modified DATETIME                        DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  record_status INT(11)                         DEFAULT '0',
  remarks       VARCHAR(255),
  sort_priority INT(11)                         DEFAULT '0'
  COMMENT '优先级',
  version       INT(11)                         DEFAULT '0'
);

# 消息表
CREATE TABLE IF NOT EXISTS conversation_message_entity
(
  id              BIGINT(20) PRIMARY KEY NOT NULL              AUTO_INCREMENT,
  conversation_id BIGINT(20)             NOT NULL
  COMMENT '会话id',
  message         VARCHAR(255),
  author          VARCHAR(255),
  customer        TINYINT(1)             NOT NULL              DEFAULT 0,
  date_created    DATETIME                                     DEFAULT CURRENT_TIMESTAMP,
  last_modified   DATETIME                                     DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  record_status   INT(11)                NOT NULL              DEFAULT '0',
  remarks         VARCHAR(255),
  sort_priority   INT(11)                                      DEFAULT '0'
  COMMENT '优先级',
  version         INT(11)                                      DEFAULT '0'
);

# 帖子主题
CREATE TABLE IF NOT EXISTS discussion_topic_entity
(
  id            BIGINT(20) PRIMARY KEY NOT NULL              AUTO_INCREMENT,
  topic         VARCHAR(20)            not null              default '',
  date_created  DATETIME                                     DEFAULT CURRENT_TIMESTAMP,
  last_modified DATETIME                                     DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  record_status INT(11)                NOT NULL              DEFAULT '0',
  remarks       VARCHAR(255),
  sort_priority INT(11)                                      DEFAULT '0'
  COMMENT '优先级',
  version       INT(11)                                      DEFAULT '0',
  CONSTRAINT unique_topic UNIQUE (topic)
);

# 帖子表
CREATE TABLE IF NOT EXISTS discussion_entity
(
  id                  BIGINT(20) PRIMARY KEY NOT NULL              AUTO_INCREMENT,
  user_id             BIGINT(20)             NOT NULL
  COMMENT '发帖者id',
  discussion_topic_id BIGINT(20)             NOT NULL,
  author              VARCHAR(20),
  title               VARCHAR(40),
  content             VARCHAR(1000),
  tags                json comment '标签',
  view_count          INT(11)                NOT NULL              DEFAULT '0',
  comment_count       INT(11)                NOT NULL              DEFAULT '0',
  date_created        DATETIME                                     DEFAULT CURRENT_TIMESTAMP,
  last_modified       DATETIME                                     DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  record_status       INT(11)                NOT NULL              DEFAULT '0',
  remarks             VARCHAR(255),
  sort_priority       INT(11)                                      DEFAULT '0'
  COMMENT '优先级',
  version             INT(11)                                      DEFAULT '0',
  INDEX (discussion_topic_id)
);

# 帖子评论表
CREATE TABLE IF NOT EXISTS discussion_comment_entity
(
  id            BIGINT(20) PRIMARY KEY NOT NULL              AUTO_INCREMENT,
  user_id       BIGINT(20)             NOT NULL
  COMMENT '发帖者id',
  discussion_id BIGINT(20)             NOT NULL
  COMMENT '帖id',
  author        VARCHAR(20),
  content       VARCHAR(1000),
  date_created  DATETIME                                     DEFAULT CURRENT_TIMESTAMP,
  last_modified DATETIME                                     DEFAULT CURRENT_TIMESTAMP
  ON UPDATE CURRENT_TIMESTAMP,
  record_status INT(11)                NOT NULL              DEFAULT '0',
  remarks       VARCHAR(255),
  sort_priority INT(11)                                      DEFAULT '0'
  COMMENT '优先级',
  version       INT(11)                                      DEFAULT '0',
  INDEX (discussion_id)
);

