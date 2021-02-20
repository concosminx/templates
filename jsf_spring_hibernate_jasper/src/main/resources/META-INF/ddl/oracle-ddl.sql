--oracle ddl--
CREATE TABLE products (	
  id NUMBER(10,0) NOT NULL, 
  code VARCHAR2(20) NOT NULL, 
  description VARCHAR2(128) NOT NULL, 
  price NUMBER(10,0) NOT NULL,
  CONSTRAINT pk_products PRIMARY KEY(id),
  CONSTRAINT uk_products UNIQUE(code)
); 

CREATE TABLE shopping_cart (
  session_id VARCHAR2(60) NOT NULL, 
  product_id NUMBER(10,0) NOT NULL, 
  quantity NUMBER(10,0) NOT NULL,
  CONSTRAINT pk_shopping_cart PRIMARY KEY (session_id, product_id),
  CONSTRAINT fk_cart_products FOREIGN KEY (product_id) REFERENCES products(id)
);

--inserts--
Insert into products (id, code, description, price) values ( 1,'C0001','ASUS PRIME A320M-K, Socket AM4',2000);
Insert into products (id, code, description, price) values ( 2,'C0002','ASUS PRIME H310M-K R2.0, Socket 1151',2000);
Insert into products (id, code, description, price) values ( 3,'C0003','ASUS PRIME Z490-A, Socket 1200',1000);
Insert into products (id, code, description, price) values ( 4,'C0004','ASUS ROG STRIX B360-F GAMING, Socket 1151',1500);
Insert into products (id, code, description, price) values ( 5,'C0005','ASUS ROG STRIX B550-F GAMING(WI-FI), Socket AM4',500);
Insert into products (id, code, description, price) values ( 6,'C0006','ASUS PRIME B360M-A, Socket 1151',3500);
Insert into products (id, code, description, price) values ( 7,'C0007','ASUS TUF GAMING B550-PLUS, Socket AM4',50);
Insert into products (id, code, description, price) values ( 8,'C0008','Asus ROG MAXIMUS XII EXTREME, Socket 1200',5600);
Insert into products (id, code, description, price) values ( 9,'C0009','ASUS PRIME A320M-A, Socket AM4 ',400);
Insert into products (id, code, description, price) values (10,'C0010','ASUS ROG STRIX B460-F GAMING, Socket 1200',1100);


