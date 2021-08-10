/*
Created by: Zachary Young
Updated by: Zachary Young
Created on: 07/01/2021
Last edited: 08/09/2021
Created for: CMSC495
Tested platform(s): 
  mysql Ver 15.1 Distrib 10.3.28-MariaDB, for Linux (x86_64) using readline 5.1
  mysql AWS RDS Ver 8.0.25
  

HELPFUL COMMANDS:
-- drop app tables:
DROP TABLE IF EXISTS SIMS_app_data.orders;
DROP TABLE IF EXISTS SIMS_app_data.waste;
DROP TABLE IF EXISTS SIMS_app_data.sales;
DROP TABLE IF EXISTS SIMS_app_data.users;
DROP TABLE IF EXISTS SIMS_app_data.inventory;

-- drop mysql.user(s) created
DROP USER IF EXISTS 'SIMS_admin';
DROP USER IF EXISTS 'SIMS_admin_bkup';
DROP USER IF EXISTS 'zyoung5';
DROP USER IF EXISTS 'guhlan';
DROP USER IF EXISTS 'bsutte'; 
DROP USER IF EXISTS 'stetan';
DROP USER IF EXISTS 'mbalth';
  
-- drop app database: 
USE mysql; DROP DATABASE IF EXISTS SIMS_app_data;

*/


/* create original application admin and users for DataBase connection */
SELECT ' [+] Creating users ...' as '';
USE mysql;
CREATE USER IF NOT EXISTS 'SIMS_admin' IDENTIFIED BY 'SIMS_Sup3r_C0mplex!';
CREATE USER IF NOT EXISTS 'SIMS_admin_bkup' IDENTIFIED BY 'SIMS_Sup3r_B&kup!';
CREATE USER IF NOT EXISTS 'zyoung5' IDENTIFIED BY 'P@ssw0rd';
CREATE USER IF NOT EXISTS 'guhlan' IDENTIFIED BY 'P@ssw0rd';
CREATE USER IF NOT EXISTS 'bsutte' IDENTIFIED BY 'P@ssw0rd';
CREATE USER IF NOT EXISTS 'stetan' IDENTIFIED BY 'P@ssw0rd';
CREATE USER IF NOT EXISTS 'mbalth' IDENTIFIED BY 'P@ssw0rd';
SELECT ' [+] Users created:' as '';
SELECT user FROM mysql.user;


/* create database if it does not exist */
SELECT ' [+] Configuring database ...' as '';
CREATE DATABASE IF NOT EXISTS SIMS_app_data;
SELECT ' [+] database built:' as '';
SHOW databases;

/* harden account */
/* currently everyuser has admin of SIMS_app_data -- this is bad */
SELECT ' [+] Updating user permissions ...' as '';
SELECT user,ssl_type,Host from mysql.user;
UPDATE mysql.user SET ssl_type = 'ANY' WHERE Host != 'localhost';
FLUSH PRIVILEGES;
SHOW GRANTS FOR 'SIMS_admin';
SHOW GRANTS FOR 'SIMS_admin_bkup';
SHOW GRANTS FOR 'zyoung5';
SHOW GRANTS FOR 'guhlan';
SHOW GRANTS FOR 'bsutte'; 
SHOW GRANTS FOR 'stetan';
SHOW GRANTS FOR 'mbalth';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'SIMS_admin';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'SIMS_admin_bkup';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'zyoung5';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'guhlan';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'bsutte';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'stetan';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'mbalth';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'SIMS_admin';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'SIMS_admin_bkup';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'zyoung5';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'guhlan';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'bsutte';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'stetan';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'mbalth';
UPDATE mysql.user SET ssl_type = 'ANY' WHERE Host != 'localhost';
FLUSH PRIVILEGES;
SELECT user,ssl_type,Host from mysql.user;
SHOW GRANTS FOR 'SIMS_admin';
SHOW GRANTS FOR 'SIMS_admin_bkup';
SHOW GRANTS FOR 'zyoung5';
SHOW GRANTS FOR 'guhlan';
SHOW GRANTS FOR 'bsutte'; 
SHOW GRANTS FOR 'stetan';
SHOW GRANTS FOR 'mbalth';
SELECT ' [+] Users permissions updated.' as '';

/* All users created will have a SQL account and be \
    granted limited permissions. 
    
    Administrator = 0
    Supervisor    = 1
    User          = 2 
*/

USE SIMS_app_data;

/* create tables if they do not exists */
SELECT ' [+] Configuring tables ...' as '';

-- User application table
-- Role: 0 = admin, 1 = approver, 2 = employee
-- username is their sql name
CREATE TABLE IF NOT EXISTS SIMS_app_data.users(
    UserID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    Username VARCHAR(60) UNIQUE NOT NULL, 
    Fname VARCHAR(60) NOT NULL, 
    Lname VARCHAR(60) NOT NULL,
    Role INT DEFAULT 2 CHECK (Role = 1 OR Role = 2 OR Role = 0) 
)
COMMENT="The users table is used for managing application users."
;

-- password table referencing user

-- Inventory table
CREATE TABLE IF NOT EXISTS  SIMS_app_data.inventory(
    InventoryID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    Name VARCHAR(60) UNIQUE NOT NULL,  
    Description VARCHAR(120) UNIQUE NOT NULL,
    FoodCategory VARCHAR(60) NOT NULL, 
    WholeSalePrice DECIMAL(8,2) NOT NULL,
    RetailPrice DECIMAL(8,2) NOT NULL,
    Quantity INT NOT NULL
)
COMMENT="The inventory table is used for managing inventory items."
;

-- Sales tables
CREATE TABLE IF NOT EXISTS SIMS_app_data.orders(
    OrderID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    OrderEventID VARCHAR(130) NOT NULL,
    EmployeeID INT NOT NULL,
    ApproverID INT,
    ItemID INT NOT NULL,
    WholeSaleUnitPrice DECIMAL(8,2) NOT NULL,
    WholeSaleTotalPrice DECIMAL(10,2) AS (WholeSaleUnitPrice * Quantity),
    Quantity INT NOT NULL, 
    OrderDate DATETIME NOT NULL,
    Status INT DEFAULT 0 CHECK (Status = 0 OR Status = 1 OR Status = 2),
    FOREIGN KEY (EmployeeID) REFERENCES users(UserID),
    FOREIGN KEY (ApproverID) REFERENCES users(UserID),
    FOREIGN KEY (ItemID) REFERENCES inventory(InventoryID)
)
COMMENT="The orders table is used for managing whole sale orders placed."
;

-- Waste tables
CREATE TABLE IF NOT EXISTS SIMS_app_data.waste(
    WasteID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    WasteEventID VARCHAR(130) NOT NULL,
    EmployeeID INT NOT NULL,
    ApproverID INT,
    ItemID INT NOT NULL,
    WholeSaleUnitPrice DECIMAL(8,2) NOT NULL,
    WholeSaleTotalPrice DECIMAL(10,2) AS (WholeSaleUnitPrice * Quantity),
    Quantity INT NOT NULL, 
    WasteDate DATETIME NOT NULL,
    Status INT DEFAULT 0 CHECK (Status = 0 OR Status = 1 OR Status = 2),
    FOREIGN KEY (EmployeeID) REFERENCES users(UserID),
    FOREIGN KEY (ApproverID) REFERENCES users(UserID),
    FOREIGN KEY (ItemID) REFERENCES inventory(InventoryID)
)
COMMENT="The waste table is used for managing waste request placed."
;

SELECT ' [+] tables built:' as '';
SHOW tables;

-- check those tables
SELECT ' [+] Final report:' as '';
SELECT TABLE_COMMENT,CREATE_TIME,UPDATE_TIME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='SIMS_app_data';
SELECT ' [!] COMPELETE.' as '';
