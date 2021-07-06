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


CREATE PROCEDURE `getDailySales`() BEGIN DECLARE sum_total INT DEFAULT 0; SELECT SUM(orders.total_price) into sum_total FROM `inventory`,`orders` WHERE `inventory`.`item_id` = `orders`.`item_id` AND DATE(`orders`.`order_date`) = CURRENT_DATE; INSERT INTO `sales` (`date`, `total_sales`) VALUES (CURRENT_DATE, sum_total); END

CREATE EVENT `sales_event` ON SCHEDULE EVERY 1 DAY STARTS '2021-07-03 20:00:00' ON COMPLETION PRESERVE ENABLE DO CALL getDailySales() 

DROP EVENT `sales_event`; CREATE DEFINER=`root`@`localhost` EVENT `sales_event` ON SCHEDULE EVERY 1 DAY STARTS '2021-07-06 11:15:11' ON COMPLETION PRESERVE ENABLE DO CALL getDailySales()
