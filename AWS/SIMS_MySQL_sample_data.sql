/*
Created by: Zachary Young
Updated by: Zachary Young
Created on: 07/01/2021
Last edited: 08/02/2021
Created for: CMSC495
Tested platform(s): 
  mysql  Ver 15.1 Distrib 10.3.28-MariaDB, for Linux (x86_64) using readline 5.1
Ensure the following are applied:
-
Objectives
-
        
EXAMPLE USAGE:
mysql -u root -p < ./SIMS_MySQL_sample_data.sql
*/

/* populate basic data */
-- users

SHOW DATABASES;
USE SIMS_app_data;

SELECT ' [+] Adding users ...' as '';
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("zyoung5", "Zachary", "Young", 0);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("guhlan", "Gary", "Uhland", 2);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("bsutte", "Benjamin", "Sutter", 2);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("stetan", "Sophia", "Tetang", 1);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("mbalth", "Marsha", "Balthazar", 0);
SELECT ' [+] users added:' as '';
SELECT UserID, Username, Role FROM SIMS_app_data.users;

-- populate passwords... admins only have access to this table

-- inventory
SELECT ' [+] Adding inventory items ...' as '';
INSERT INTO inventory(Name, Description, FoodCategory , WholeSalePrice, RetailPrice, Quantity) VALUES ("Green Apples", "Savory, stem and all.", "Fruit", 0.25, 1.50, 125);
INSERT INTO inventory(Name, Description, FoodCategory , WholeSalePrice, RetailPrice, Quantity) VALUES ("Idaho Potato", "Mash it, bake it, fry it, grill it.", "Vegitable", 0.30, 2.99, 1214);
INSERT INTO inventory(Name, Description, FoodCategory , WholeSalePrice, RetailPrice, Quantity) VALUES ("Bell Pepper", "Freshest bell pepper this side of the Atlantic.", "Vegitable", 0.15, 1.99, 5);
INSERT INTO inventory(Name, Description, FoodCategory , WholeSalePrice, RetailPrice, Quantity) VALUES ("Dozen Toilet Paper", "Softest brand around, double ply.", "Other", 5.15, 18.99, 35);
SELECT ' [+] inventory items added:' as '';
SELECT InventoryID, Name, Quantity FROM SIMS_app_data.inventory;


-- orders
SELECT ' [+] Adding orders ...' as '';
INSERT INTO orders(OrderEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, OrderDate, Status) VALUES ("6c311225-1c87-4df8-9dce-aee66af12b97", 2, 1, 0.08, 0.25, 100, '2020-06-19 19:34:02', 1);
INSERT INTO orders(OrderEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, OrderDate, Status) VALUES ("5ac4aa66-6716-41f9-a6a3-dbe76f8eaae4", 3, 1, 0.08, 0.30, 200, '2021-06-19 17:34:02', 1);
INSERT INTO orders(OrderEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, OrderDate, Status) VALUES ("3610d758-ae4d-416e-975c-0829aa6ac00a", 3, 2, 0.08, 0.15, 1000, '2021-07-02 10:18:35', 0);
INSERT INTO orders(OrderEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, OrderDate, Status) VALUES ("3610d758-ae4d-416e-975c-0829aa6ac00a", 2, 4, 0.08, 5.15, 175, '2021-07-02 10:18:35', 0);
SELECT ' [+] orders added:' as '';
SELECT OrderID, OrderEventID, OrderDate FROM SIMS_app_data.orders;

-- sales
SELECT ' [+] Adding sales ...' as '';
INSERT INTO sales(SalesEventID, EmployeeID, ItemID, SalesUnitPrice, SalesTax, Quantity, SalesDate) VALUES ("78087753-b86c-45a0-974a-abe65827a27d", 2, 1, 1.50, 0.08, 12, '2020-06-19 19:34:02');
INSERT INTO sales(SalesEventID, EmployeeID, ItemID, SalesUnitPrice, SalesTax, Quantity, SalesDate) VALUES ("78087753-b86c-45a0-974a-abe65827a27d", 3, 2, 2.99, 0.08, 48, '2021-06-19 17:34:02');
INSERT INTO sales(SalesEventID, EmployeeID, ItemID, SalesUnitPrice, SalesTax, Quantity, SalesDate) VALUES ("e3bbf41a-a9d2-4e39-8546-11beecba6c01", 3, 2, 2.99, 0.08, 24, '2021-07-02 10:18:35');
INSERT INTO sales(SalesEventID, EmployeeID, ItemID, SalesUnitPrice, SalesTax, Quantity, SalesDate) VALUES ("1ad6afbf-8ca1-453f-baf6-7be400f02595", 2, 4, 18.99, 0.08, 1, '2021-07-02 10:18:35');
SELECT ' [+] orders added:' as '';
SELECT SalesID, SalesEventID, SalesDate FROM SIMS_app_data.sales;

-- waste
SELECT ' [+] Adding waste ...' as '';
INSERT INTO waste(WasteEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, WasteDate, Status) VALUES ("6c311225-1c87-4df8-9dce-aee66af12b97", 2, 1, 0.08, 0.25, 100, '2020-06-19 19:34:02', 1);
INSERT INTO waste(WasteEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, WasteDate, Status) VALUES ("5ac4aa66-6716-41f9-a6a3-dbe76f8eaae4", 3, 1, 0.08, 0.30, 200, '2021-06-19 17:34:02', 1);
INSERT INTO waste(WasteEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, WasteDate, Status) VALUES ("3610d758-ae4d-416e-975c-0829aa6ac00a", 3, 2, 0.08, 0.15, 1000, '2021-07-02 10:18:35', 0);
INSERT INTO waste(WasteEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, WasteDate, Status) VALUES ("3610d758-ae4d-416e-975c-0829aa6ac00a", 2, 4, 0.08, 5.15, 175, '2021-07-02 10:18:35', 0);
SELECT ' [+] waste added:' as '';
SELECT WasteID, WasteEventID, WasteDate FROM SIMS_app_data.waste;
