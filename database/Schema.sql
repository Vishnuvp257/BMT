-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bookmytickets
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bookmytickets
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bookmytickets` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bookmytickets` ;

-- -----------------------------------------------------
-- Table `bookmytickets`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `pass` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookmytickets`.`movies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`movies` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `genre` VARCHAR(50) NULL DEFAULT NULL,
  `duration` VARCHAR(50) NULL DEFAULT NULL,
  `ratings` VARCHAR(50) NULL DEFAULT NULL,
  `descrip` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookmytickets`.`theaters`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`theaters` (
  `theater_id` INT NOT NULL AUTO_INCREMENT,
  `theatername` VARCHAR(50) NOT NULL,
  `location` VARCHAR(50) NULL DEFAULT NULL,
  `total_seats` INT NOT NULL,
  PRIMARY KEY (`theater_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookmytickets`.`showtimes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`showtimes` (
  `showtime_id` INT NOT NULL AUTO_INCREMENT,
  `movie_id` INT NOT NULL,
  `theater_id` INT NOT NULL,
  `show_date` DATE NOT NULL,
  `show_time` TIME NOT NULL,
  PRIMARY KEY (`showtime_id`),
  INDEX `movie_id` (`movie_id` ASC) VISIBLE,
  INDEX `theater_id` (`theater_id` ASC) VISIBLE,
  CONSTRAINT `showtimes_ibfk_1`
    FOREIGN KEY (`movie_id`)
    REFERENCES `bookmytickets`.`movies` (`movie_id`),
  CONSTRAINT `showtimes_ibfk_2`
    FOREIGN KEY (`theater_id`)
    REFERENCES `bookmytickets`.`theaters` (`theater_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookmytickets`.`bookings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`bookings` (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `showtime_id` INT NOT NULL,
  `booking_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `num_tickets` INT NOT NULL,
  PRIMARY KEY (`booking_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `showtime_id` (`showtime_id` ASC) VISIBLE,
  CONSTRAINT `bookings_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `bookmytickets`.`users` (`user_id`),
  CONSTRAINT `bookings_ibfk_2`
    FOREIGN KEY (`showtime_id`)
    REFERENCES `bookmytickets`.`showtimes` (`showtime_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookmytickets`.`seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`seats` (
  `seat_id` INT NOT NULL AUTO_INCREMENT,
  `theater_id` INT NOT NULL,
  `seat_number` VARCHAR(10) NOT NULL,
  `is_booked` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`seat_id`),
  INDEX `theater_id` (`theater_id` ASC) VISIBLE,
  CONSTRAINT `seats_ibfk_1`
    FOREIGN KEY (`theater_id`)
    REFERENCES `bookmytickets`.`theaters` (`theater_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 258
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bookmytickets`.`booking_seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bookmytickets`.`booking_seats` (
  `booking_seat_id` INT NOT NULL AUTO_INCREMENT,
  `booking_id` INT NOT NULL,
  `seat_id` INT NOT NULL,
  PRIMARY KEY (`booking_seat_id`),
  INDEX `booking_id` (`booking_id` ASC) VISIBLE,
  INDEX `seat_id` (`seat_id` ASC) VISIBLE,
  CONSTRAINT `booking_seats_ibfk_1`
    FOREIGN KEY (`booking_id`)
    REFERENCES `bookmytickets`.`bookings` (`booking_id`),
  CONSTRAINT `booking_seats_ibfk_2`
    FOREIGN KEY (`seat_id`)
    REFERENCES `bookmytickets`.`seats` (`seat_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `bookmytickets` ;

-- -----------------------------------------------------
-- procedure PopulateSeats
-- -----------------------------------------------------

DELIMITER $$
USE `bookmytickets`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PopulateSeats`(in theater_id int, in total_seats int)
begin
	declare prefix char(5) default 'A';
    declare counter int default 0;
    declare seat_number varchar(10);
    
    while counter < total_seats do
		set seat_number = concat(prefix,counter+1);
        insert into seats values (
			default, theater_id , seat_number , false
        );
        set counter = counter + 1;
	end while;
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure bookTicket
-- -----------------------------------------------------

DELIMITER $$
USE `bookmytickets`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `bookTicket`(
					in user_id int, 
					in showtime_id int, 
					in no_of_seats int,
					in selected_seat_ids varchar(20)
                )
begin
	insert into bookings values (null, @user_id, @showtime_id, now(), @no_of_seats);
    set @booking_id = last_insert_id();
    
    drop temporary table if exists seat_list;
    create temporary table seat_list (seat_id int);
    
    set @curr = 1;
    SET @str_len = CHAR_LENGTH(selected_seat_ids);
    
    while curr < str_len do
		set @seat_id_str = SUBSTRING_INDEX(SUBSTRING_INDEX(selected_seat_ids, ',', curr), ',', -1);
        insert into seat_list(seat_id) values (cast(seat_id_str as unsigned));
        set @curr = curr +1;
	end while;
    
    insert into booking_seats (booking_id, seat_id)
    select @booking_id , seat_id from seat_list;
    
    update seats set is_booked = true where seat_id in 
													(select seat_id from seat_list);
    
end$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure book_a_ticket
-- -----------------------------------------------------

DELIMITER $$
USE `bookmytickets`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `book_a_ticket`(
    IN user_id INT, 
    IN showtime_id INT, 
    IN no_of_seats INT,
    IN selected_seat_ids VARCHAR(255)
)
BEGIN
    DECLARE curr_pos INT DEFAULT 1;
    DECLARE next_pos INT DEFAULT 1;
    DECLARE seat_id_str VARCHAR(20);
    
    -- Insert into bookings table
    INSERT INTO bookings(user_id, showtime_id, booking_date, num_tickets)
    VALUES (user_id, showtime_id, NOW(), no_of_seats);
    
    SET @booking_id = LAST_INSERT_ID();
    
    -- Drop temporary table if exists
    DROP TEMPORARY TABLE IF EXISTS seat_list;
    -- Create temporary table to store seat ids
    CREATE TEMPORARY TABLE seat_list (seat_id INT);
    
    -- Loop to split the selected_seat_ids string and insert into temporary table
    WHILE CHAR_LENGTH(selected_seat_ids) > 0 DO
        SET next_pos = LOCATE(',', selected_seat_ids);
        
        IF next_pos = 0 THEN
            SET seat_id_str = selected_seat_ids;
            SET selected_seat_ids = '';
        ELSE
            SET seat_id_str = LEFT(selected_seat_ids, next_pos - 1);
            SET selected_seat_ids = SUBSTRING(selected_seat_ids, next_pos + 1);
        END IF;
        
        INSERT INTO seat_list(seat_id) VALUES (CAST(seat_id_str AS UNSIGNED));
    END WHILE;
    
    -- Insert into booking_seats table
    INSERT INTO booking_seats (booking_id, seat_id)
    SELECT @booking_id, seat_id FROM seat_list;
    
    -- Update seats table
    UPDATE seats SET is_booked = TRUE WHERE seat_id IN (SELECT seat_id FROM seat_list);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure inserTheater
-- -----------------------------------------------------

DELIMITER $$
USE `bookmytickets`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserTheater`(in theater varchar(50), in location varchar(50), in seats int)
begin
	insert into theaters values (
		default, theater, location, seats
	);

	set @id := last_insert_id();
	select total_seats into @total_seats from theaters where theater_id = @id;

	call PopulateSeats(@id,@total_seats);
end$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
