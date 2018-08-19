

#创建数据库
CREATE DATABASE IF NOT EXISTS customer_service DEFAULT CHARSET utf8mb4 COLLATE utf8_general_ci;
grant all privileges on customer_service.* to customer_service@localhost identified by 'customer_service';