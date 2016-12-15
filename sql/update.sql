ALTER TABLE `site`
MODIFY COLUMN `parent_id`  varchar(200) NULL DEFAULT 0 COMMENT '父级场地' AFTER `id`;