CREATE USER IF NOT EXISTS 'java'@'localhost' IDENTIFIED BY 'javapassword';
GRANT INSERT on *.* TO 'java'@'localhost';
GRANT SELECT on *.* TO 'java'@'localhost';
GRANT DELETE on *.* TO 'java'@'localhost';
GRANT UPDATE on *.* TO 'java'@'localhost';