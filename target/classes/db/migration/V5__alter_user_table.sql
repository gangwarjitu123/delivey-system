ALTER TABLE `user`
ADD COLUMN `is_active` TINYINT NULL AFTER `user_type_id`;