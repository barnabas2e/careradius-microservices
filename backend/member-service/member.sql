CREATE TABLE `member` (
	`internal_id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`member_id` VARCHAR(255) NOT NULL,
	`first_name` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`formatted_name` VARCHAR(255) NOT NULL,
	`gender` VARCHAR(15) NULL,
	`address` VARCHAR(500) NULL,
	PRIMARY KEY (`internal_id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;