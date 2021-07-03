/*
Created by: Zachary Young
Created on: 07/01/2021
Last edited: 07/03/2021
Created for: CMSC495

MySQL

Ensure the following are applied:
-

Objectives
-

*/


/* populate basic data */
-- users
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("zyoung", "Zachary", "Young", 0);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("guhlan", "Gary", "Uhland", 2);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("bsutte", "Benjamin", "Sutter", 2);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("stetan", "Sophia", "Tetang", 1);
INSERT INTO users(Username, Fname, Lname, Role) VALUES ("mbalth", "Marsha", "Balthazar", 0);

-- populate passwords... admins only have access to this table

-- inventory
INSERT INTO inventory(Name, Description, Price, Quantity) VALUES ("Green Apples", "Savory, stem and all.", 1.50, 125);
INSERT INTO inventory(Name, Description, Price, Quantity) VALUES ("Idaho Potato", "Mash it, bake it, fry it, grill it.", 2.99, 1214);
INSERT INTO inventory(Name, Description, Price, Quantity) VALUES ("Bell Pepper", "Freshest bell pepper this side of the Atlantic.", 1.99, 5);
INSERT INTO inventory(Name, Description, Price, Quantity) VALUES ("Dozen Toilet Paper", "Softest brand around, double ply.", 18.99, 35);

-- purchase history
INSERT INTO orders(EmployeeID, ItemID, Description, UnitPrice, TotalPrice, Quantity, OrderDate, Status) VALUES (2, 1, "Need more Apples. Flying off the shelves.", 1.50, 150, 100, '2020-06-19 19:34:02', 1);
INSERT INTO orders(EmployeeID, ItemID, Description, UnitPrice, TotalPrice, Quantity, OrderDate, Status) VALUES (3, 1, "Need more Apples. Flying off the shelves.", 1.50, 300, 200, '2021-06-19 17:34:02', 1);
INSERT INTO orders(EmployeeID, ItemID, Description, UnitPrice, TotalPrice, Quantity, OrderDate, Status) VALUES (3, 2, "People love potatos.", 2.99, 2990, 1000, '2021-07-01 21:02:25', 0);
INSERT INTO orders(EmployeeID, ItemID, Description, UnitPrice, TotalPrice, Quantity, OrderDate, Status) VALUES (2, 4, "We need the paper.", 18.99, 3323.25, 175, '2021-07-02 10:18:35', 0);

