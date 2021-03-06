DELIMITER //  
Create Trigger before_insert_orders   
BEFORE INSERT ON orders FOR EACH ROW  
BEGIN  
SET NEW.order_date = now();
END //  

DELIMITER ;  

DELIMITER //  
CREATE PROCEDURE get_my_cart(IN uid INT)
BEGIN  
	SELECT `cart`.`cart_id`, `cart`.`item_id`, `cart`.`user_id`, `inventory`.`item_name`, `inventory`.`item_price`, `inventory`.`item_qty` FROM `cart`,`inventory` WHERE `cart`.`item_id`=`inventory`.`item_id` AND `user_id` = uid; 
END //  

DELIMITER ;

call get_my_cart(1);

CREATE TRIGGER `decrease_inventory` 
AFTER INSERT ON `orders` FOR EACH ROW
 BEGIN 
	UPDATE `inventory` SET `item_qty`= `item_qty`- NEW.order_qty WHERE `item_id`= NEW.item_id;
 END

DELIMITER //  
CREATE PROCEDURE `getDailySales`()
	BEGIN DECLARE sum_total INT DEFAULT 0; SELECT SUM(orders.total_price) into sum_total FROM `inventory`,`orders` WHERE `inventory`.`item_id` = `orders`.`item_id` AND DATE(`orders`.`order_date`) = CURRENT_DATE; INSERT INTO `sales` (`date`, `total_sales`) VALUES (CURRENT_DATE, sum_total); END
END //

CREATE EVENT `sales_event` ON SCHEDULE EVERY 1 DAY STARTS '2021-07-03 20:00:00' ON COMPLETION PRESERVE ENABLE DO CALL getDailySales()

DROP EVENT `sales_event`; CREATE DEFINER=`root`@`localhost` EVENT `sales_event` ON SCHEDULE EVERY 1 DAY STARTS '2021-07-06 11:15:11' ON COMPLETION PRESERVE ENABLE DO CALL getDailySales()

DELIMITER //  
CREATE PROCEDURE sales_event()
BEGIN DECLARE sum_total INT DEFAULT 0; SELECT SUM(orders.total_price) into sum_total FROM `inventory`,`orders` WHERE `inventory`.`item_id` = `orders`.`item_id` AND DATE(`orders`.`order_date`) = CURRENT_DATE AND orders.status = 3; INSERT INTO `sales` (`date`, `total_sales`) VALUES (CURRENT_DATE, sum_total); 
END //



DELIMITER //
CREATE PROCEDURE get_orders_by_date(IN d Date)
BEGIN
SELECT * FROM `orders` WHERE DATE(`order_date`)=d;
END //


DELIMITER //
CREATE FUNCTION totalSale(
    d date
)
    RETURNS int
    DETERMINISTIC
BEGIN
    DECLARE sale int;

SELECT SUM(total_price) INTO sale FROM `orders` WHERE DATE(`order_date`)=d;

RETURN (sale);
END//
DELIMITER ;



CREATE VIEW `view_cart` AS  select `c`.`cart_id` AS `cart_id`,`c`.`item_id` AS `item_id`,`c`.`user_id` AS `user_id`,`i`.`item_name` AS `item_name`,`i`.`item_price` AS `item_price`,`i`.`item_qty` AS `item_qty` from (`inventory` `i` join `cart` `c`) where (`c`.`item_id` = `i`.`item_id`) ;