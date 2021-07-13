/*
Created by: Zachary Young
Updated by: Zachary Young
Created on: 07/01/2021
Last edited: 07/12/2021
Created for: CMSC495

Tested platform(s): 
  mysql  Ver 15.1 Distrib 10.3.28-MariaDB, for Linux (x86_64) using readline 5.1

Ensure the following are applied:
  - Create new users that can query application database
  - Lock down new user permissions

HELPFUL COMMANDS:
  drop app tables:
DROP TABLE IF EXISTS SIMS_app_data.orders;
DROP TABLE IF EXISTS SIMS_app_data.users;
DROP TABLE IF EXISTS SIMS_app_data.inventory;
DROP TABLE IF EXISTS SIMS_app_data.sales;
  
  drop app mysql.user:
DROP USER IF EXISTS 'SIMS_admin'@'localhost';
DROP USER IF EXISTS 'SIMS_admin_bkup'@'localhost';
DROP USER IF EXISTS 'zyoung5';EXISTS 'SIMS_admin_bkup'@'localhost';
        "
  drop app database: "
        USE mysql; DROP DATABASE IF EXISTS SIMS_app_data;
        "
        
EXAMPLE USAGE:
mysql -u root -p < ./SIMS_MySQL_setup.sql

*/


/* create original application admin for DataBase connection */
SELECT ' [+] Creating users ...' as '';
USE mysql;
CREATE USER IF NOT EXISTS 'SIMS_admin' IDENTIFIED BY 'SIMS_Sup3r_C0mplex!';
CREATE USER IF NOT EXISTS 'SIMS_admin_bkup' IDENTIFIED BY 'SIMS_Sup3r_B&kup!';
CREATE USER IF NOT EXISTS 'zyoung5' IDENTIFIED BY 'P@ssw0rd';
SELECT ' [+] Users created:' as '';
SELECT user FROM mysql.user WHERE user LIKE "SIMS%";
SELECT user FROM mysql.user;


/* create database if it does not exist */
SELECT ' [+] Configuring database ...' as '';
CREATE DATABASE IF NOT EXISTS SIMS_app_data;
SELECT ' [+] database built:' as '';
SHOW databases;

/* harden default SIMS admin account */
SELECT ' [+] Updating user permissions ...' as '';
SHOW GRANTS FOR 'SIMS_admin';
SHOW GRANTS FOR 'SIMS_admin_bkup';
SHOW GRANTS FOR 'zyoung5';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'SIMS_admin';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'SIMS_admin_bkup';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'SIMS_admin';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'SIMS_admin_bkup';
GRANT ALL PRIVILEGES ON SIMS_app_data.* TO 'zyoung5';
SHOW GRANTS FOR 'SIMS_admin';
SHOW GRANTS FOR 'SIMS_admin_bkup';
SHOW GRANTS FOR 'zyoung5';
SELECT ' [+] Users permissions updated.' as '';

/* All users created will have a SQL account and be \
    granted limited permissions. 
    
    Administrator = 0
    Supervisor    = 1
    User          = 2
    
    examples:
CREATE USER IF NOT EXISTS 'zyoung5' IDENTIFIED BY 'P@ssw0rd';
SHOW GRANTS FOR 'zyoung5';
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'zyoung5';
GRANT SELECT, INSERT ON SIMS_app_data.* TO 'zyoung5';
SHOW GRANTS FOR 'zyoung5';
    
*/

USE SIMS_app_data;

/* create tables if they do not exists */
SELECT ' [+] Configuring tables ...' as '';

-- User application table
-- Role: 0 = admin, 1 = approver, 2 = employee
-- username is their sql name
CREATE TABLE IF NOT EXISTS users(
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
CREATE TABLE IF NOT EXISTS  inventory(
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

-- OrderID will be the complete to track all items related to single order
-- OrderID Random GUID assigned by Java Function .... 
CREATE TABLE IF NOT EXISTS orders(
    OrderID INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
    OrderEventID INT NOT NULL, 
    EmployeeID INT NOT NULL,
    ItemID INT NOT NULL,
    SalesTax DECIMAL(5,4) NOT NULL,
    WholeSaleUnitPrice DECIMAL(8,2) NOT NULL,
    WholeSaleTotalPrice DECIMAL(10,2) AS (((WholeSaleUnitPrice * Quantity) *
	       	SalesTax ) + (WholeSaleUnitPrice * Quantity)),
    Quantity INT NOT NULL, 
    OrderDate DATETIME NOT NULL,
    Status INT DEFAULT 0 CHECK (Status = 0 OR Status = 1 OR Status = 2),
    FOREIGN KEY (EmployeeID) REFERENCES users(UserID),
    FOREIGN KEY (ItemID) REFERENCES inventory(InventoryID)
)
COMMENT="The orders table is used for managing whole sale orders placed."
;

--
CREATE TABLE IF NOT EXISTS sales(
    SalesID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    SalesEventID INT NOT NULL,
    EmployeeID INT NOT NULL,
    ItemID INT NOT NULL,
    SalePrice DECIMAL(8,2) NOT NULL,
    TotalSalePrice  DECIMAL(10,2) AS (SalePrice * Quantity),
    Quantity INT NOT NULL, 
    OrderDate DATETIME NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES users(UserID),
    FOREIGN KEY (ItemID) REFERENCES inventory(InventoryID)
)
COMMENT="The sales table is used for tracking customer sales."
;

SELECT ' [+] tables built:' as '';
SHOW tables;

-- check those tables
SELECT ' [+] Final report:' as '';
SELECT TABLE_COMMENT,CREATE_TIME,UPDATE_TIME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='SIMS_app_data';
SELECT ' [!] COMPELETE.' as '';
