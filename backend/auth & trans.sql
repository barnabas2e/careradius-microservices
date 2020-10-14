
-- treatment_authorization

CREATE TABLE `treatment_authorization` (
	`internal_id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`reference_number` BIGINT NOT NULL,
	`contact_name` VARCHAR(255) NULL,
	`contact_phone` VARCHAR(255) NULL,
	`contact_method` VARCHAR(15) NULL,
	`initiated_date` DATETIME NULL,
	`valid_date_from` DATETIME NULL,
	`valid_date_to` DATETIME NULL,
	PRIMARY KEY (`internal_id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

-- transaction

CREATE TABLE `transaction` (
	`internal_id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`authorization_internal_id` BIGINT(19) NOT NULL,
	`member_internal_id` BIGINT(19) NULL,
	`user_created_by_id` BIGINT(19) NULL,
	`user_updated_by_id` BIGINT(19) NULL,
	`create_date` DATETIME NULL DEFAULT NULL,
	`update_date` DATETIME NULL DEFAULT NULL,
	PRIMARY KEY (`internal_id`) USING BTREE,
	INDEX `fk_tran_auth` (`authorization_internal_id`) USING BTREE,
	CONSTRAINT `fk_tran_auth` FOREIGN KEY (`authorization_internal_id`) REFERENCES `cr_treatment_authorization`.`treatment_authorization` (`internal_id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=21
;
