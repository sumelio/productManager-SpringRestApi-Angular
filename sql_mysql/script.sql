
create database beitech_db;
 
use beitech_db;
 
CREATE USER 'beithecUser'@'localhost' IDENTIFIED BY 'beithecUser-2017';

GRANT ALL PRIVILEGES ON beitech_db. * TO 'beithecUser'@'localhost';

CREATE TABLE IF NOT EXISTS `product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,3) NOT NULL,
  PRIMARY KEY (`product_id`));

CREATE TABLE `customer` (
  `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`customer_id`));

 
create table `order_`(
    order_id int not null auto_increment,  
    customer_id int not null,  
    order_time datetime not null, 
    delivery_address varchar(255) not null, 
    primary key(order_id), index(customer_id), 
    FOREIGN key (customer_id) references customer(customer_id)) ;

CREATE TABLE IF NOT EXISTS `customer_available_product` (
  `customer_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
 PRIMARY KEY (`customer_id`,`product_id`),
  CONSTRAINT `customer_product_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`customer_id`),
  CONSTRAINT `customer_product_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`));

 
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_detail_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_description` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,3) NOT NULL,
  `order_id` INT(11) NOT NULL,
   PRIMARY KEY (`order_detail_id`),
  INDEX `order_id` (`order_id` ASC),
  CONSTRAINT `order_detail_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order_` (`order_id`));



INSERT INTO `customer` (`customer_id`, `name`, `email`) VALUES (1, 'Manny Bharma', 'manny@Bharma.com');
INSERT INTO `customer` (`customer_id`, `name`, `email`) VALUES (2, 'Alan Briggs', 'alan@Briggs.com');
INSERT INTO `customer` (`customer_id`, `name`, `email`) VALUES (3, 'Mike Simm', 'mike@Simm.com');

INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (1, 'Product A', 100.05);
INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (2, 'Product B', 200.10);
INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (3, 'Product C', 300.05);
INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (4, 'Product D', 400.25);

INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (1, 1);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (1, 2);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (1, 3);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (2, 2);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (3, 1);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (3, 4);
