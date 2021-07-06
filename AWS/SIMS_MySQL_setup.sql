/*
Created by: Zachary Young
Updated by: Zachary Young
Created on: 07/01/2021
Last edited: 07/05/2021
Created for: CMSC495

Tested platform(s): 
  mysql  Ver 15.1 Distrib 10.3.28-MariaDB, for Linux (x86_64) using readline 5.1

Ensure the following are applied:
- AWS account differs from MySQL account and from Application account
Objectives
  - Secure MySQL instance, Databases and Tables
  - Create new users that can query applicaiton database
  - Lock down new user permissions
  - Create new applicaiton database
  - Create 4 tables: Users, Inventory, Order History, and App Passwords

HELPFUL COMMANDS:
  drop app tables: "
        DROP TABLE IF EXISTS inventory_app_data.orders;
        DROP TABLE IF EXISTS inventory_app_data.users;
        DROP TABLE IF EXISTS inventory_app_data.inventory;
        "
  drop app mysql.user: "
        DROP USER IF EXISTS 'inventory_admin'@'localhost';
        DROP USER IF EXISTS 'inventory_user'@'localhost';
        "
  drop app database: "
        USE mysql; DROP DATABASE IF EXISTS inventory_app_data;
        "
        
EXAMPLE USAGE:
mysql -u root -p < ./SIMS_MySQL_setup.sql

*/


/* create MySQL users for DataBase connection */
SELECT ' [+] Configuring users ...' as '';
USE mysql;
CREATE USER IF NOT EXISTS 'inventory_admin'@'localhost' IDENTIFIED BY 'password';
CREATE USER IF NOT EXISTS 'inventory_user'@'localhost' IDENTIFIED BY 'password';
SELECT ' [+] Users configured:' as '';
SELECT user FROM mysql.user WHERE user LIKE "inventory%";

/* create database if it does not exist */
SELECT ' [+] Configuring database ...' as '';
CREATE DATABASE IF NOT EXISTS inventory_app_data;
SELECT ' [+] database built:' as '';
SHOW databases;

USE inventory_app_data;

/* limit user to only application data/users */
/*
-- remove user ability to query any database
SHOW GRANTS FOR 'inventory_admin'@'localhost'; 
SHOW GRANTS FOR 'inventory_user'@'localhost'; 
REVOKE ALL PRIVILEGES ON %database_name% FROM 'inventory_admin'@'localhost'; 
REVOKE ALL PRIVILEGES ON %database_name% FROM 'inventory_user'@'localhost'; 
SHOW GRANTS FOR 'inventory_admin'@'localhost'; 
SHOW GRANTS FOR 'inventory_user'@'localhost'; 

-- GRANT inventory_admin for the inventory_app_data database
GRANT ALL PRIVILEGES ON %database_name% TO 'inventory_admin'@'localhost';

-- GRANT inventory_user for inventory_app_data database
GRANT ALL PRIVILEGES ON %database_name% TO 'inventory_user'@'localhost';

-- check permission of users
SHOW GRANTS FOR 'inventory_admin'@'localhost';
SHOW GRANTS FOR 'inventory_user'@'localhost';

*/

/* create tables if they do not exists */
SELECT ' [+] Configuring tables ...' as '';

-- User application table
-- Role: 0 = admin, 1 = approver, 2 = employee
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
    Category VARCHAR(60) NOT NULL, 
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
    EventID INT NOT NULL, 
    EmployeeID INT NOT NULL,
    ItemID INT NOT NULL,
    Description VARCHAR(120) NOT NULL,
    FoodCategory VARCHAR(60) NOT NULL, 
    WholeSaleUnitPrice DECIMAL(8,2) NOT NULL,
    WholeSaleTotalPrice DECIMAL(10,2) NOT NULL,
    Quantity INT NOT NULL, 
    OrderDate DATETIME NOT NULL,
    Status INT DEFAULT 0 CHECK (Status = 0 OR Status = 1),
    FOREIGN KEY (EmployeeID) REFERENCES users(UserID),
    FOREIGN KEY (ItemID) REFERENCES inventory(InventoryID)
)
COMMENT="The orders table is used for managing whole sale orders placed."
;

SELECT ' [+] tables built:' as '';
SHOW tables;

-- check those tables
SELECT ' [+] Final report:' as '';
SELECT TABLE_COMMENT,CREATE_TIME,UPDATE_TIME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='inventory_app_data';
SELECT ' [!] COMPELETE.' as '';
