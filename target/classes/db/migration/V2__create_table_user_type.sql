CREATE TABLE `user_type` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `user`
ADD CONSTRAINT `user_type`
  FOREIGN KEY (`id`)
  REFERENCES `user_type` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;

ALTER TABLE `user`
CHANGE COLUMN `user_type` `user_type_id` BIGINT(20) NULL DEFAULT NULL ;