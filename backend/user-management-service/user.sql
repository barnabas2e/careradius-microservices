--MYSQL script


--create user database table
CREATE TABLE `user` (
	`id` BIGINT(19,0) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`password` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`first_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`last_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`formatted_name` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`email` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`role` VARCHAR(15) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

