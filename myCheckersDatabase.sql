DROP database IF EXISTS checkersproducts;
CREATE database checkersproducts;
USE checkersproducts;

CREATE TABLE IF NOT EXISTS products (
  prodID INT NOT NULL PRIMARY KEY auto_increment,
  prodName VARCHAR(30) NOT NULL,
  prodType VARCHAR(30) NOT NULL,
  prodPrice DECIMAL(6,2) NOT NULL
  );
