# productManager

Este proyecto contiene dos componente:
1. productManager-api-rest:(backend)  API Rest . 
2. productManager-web:(frontend) Una aplicación que exponé una página web la cual permite consultar la lista de ordenes asociadas a un cliente en el último mes.

## Pagina web
La pagina web esta implementada en html5 y usa Angular.

1. Permite seleccionar un cliente y presentar las órdenes del último mes usando el método listar órdenes del servicio web.

La aplicación esta desplegada en la siguiente URL:

[http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-web/](http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-web/)


## API Rest
La API Rest esta implementada en Spring y Hibernate. Expone las siguientes funcionalidas:

1. Consultar el listado de clientes.

2. Permite crear una órden para un cliente con hasta máximo 5 productos. Teniendo en cuenta que sólo algunos productos están permitidos para un cliente.

3. Permite listar las órdenes de un cliente por un rango de fechas.

Para mas detalle ver ejemplos en la parte inferior y ver archivo PDF de la documentación de la API : 
<object data="http://yoursite.com/the.pdf" type="application/pdf" width="700px" height="700px">
    <embed src="http://yoursite.com/the.pdf">
        This browser does not support PDFs. Please download the PDF to view it: <a href="https://sumelio.github.io/productManager-SpringRestApi-Angular/resources/API%20REST.pdf">Download PDF</a>.</p>
    </embed>
</object>


La aplicación esta desplegada en una instancia de EC2 Amazon y esta es la URL:
[http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-api-rest/v1/customer/1/order?startDate=2017-09-26&endDate=2017-10-26](http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-api-rest/v1/customer/1/order?startDate=2017-09-26&endDate=2017-10-26)



## Diagrama entidad relación
![https://sumelio.github.io/productManager-SpringRestApi-Angular/ER.png](https://sumelio.github.io/productManager-SpringRestApi-Angular/resources/ER.png)

## Componentes
### Frontend: productManager-web
- Angular, html
### Backend: productManager-api-rest
- Spring, Hibernate

![https://sumelio.github.io/productManager-SpringRestApi-Angular/Component%20Model.jpg](https://sumelio.github.io/productManager-SpringRestApi-Angular/resources/Component%20Model.jpg)

## Ejemplos API Rest
### Create order
URL  [/v1/order](http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-api-rest/v1/order)

Method  The request type  POST 

Version 1.0

Ejemplo:
```
http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-api-rest/v1/order
```

```
{
  "orderCustomer": { 
    "deliveryAddress": "15 Queens Park Road, W32 YYY, UK", 
    "customer": {
      "customerId": 1
    }
  },
  "products": [
    {
      "productId": 1
    }, 
        {
      "productId": 1
    },
        {
      "productId": 2
    }
  ]
}
```
Success Response 
The order was created.

Code: 200 
Content: 
```
{
    "description": "OK",
    "code": 200
}
```
Error Response 
-  Validate the maximum products
Example: 
Code: 400 BAD_REQUEST
Content: 
```
{
    "description": "La cantidad de productos debe ser menor o igual a 5",
    "code": 400
}
```

- Not available products by Customer
Example: 
Code: 400 BAD_REQUEST
Content:
```
{
    "description": "El producto con id 4 no está disponible para el cliente Manny Bharma",
    "code": 400
}
``` 


### Get orders by Customer and Date
URL  [/customer/:id/order](http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-api-rest/v1/customer/1/order?startDate=2017-09-26&endDate=2017-10-26)

Method  The request type  GET 

Version 1.0
 ```
http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-api-rest/v1/customer/1/order?startDate=2017-09-26&endDate=2017-10-26
 ```
 
- The order was found.

Code: 200 
Content: 

 ```
[
  {
    "orderId": 1,
    "deliveryAddress": "15 Queens Park Road, W32 YYY, UK",
    "orderTime": "2017-10-26",
    "customer": {
      "customerId": 1,
      "email": "manny@Bharma.com",
      "name": "Manny Bharma",
      "products": [
        {
          "productId": 1,
          "name": "Product A",
          "price": 100.05
        },
        {
          "productId": 2,
          "name": "Product B",
          "price": 200.1
        },
        {
          "productId": 3,
          "name": "Product C",
          "price": 300.05
        }
      ]
    },
    "orderDetails": [
      {
        "orderDetailId": 1,
        "price": 200.1,
        "productDescription": "2 X Product A"
      },
      {
        "orderDetailId": 2,
        "price": 200.1,
        "productDescription": "1 X Product B"
      },
      {
        "orderDetailId": 3,
        "price": 300.05,
        "productDescription": "1 X Product C"
      }
    ],
    "totalPrice": 700.25
  },
  {
    "orderId": 2,
    "deliveryAddress": "15 Queens Park Road, W32 YYY, UK",
    "orderTime": "2017-10-26",
    "customer": {
      "customerId": 1,
      "email": "manny@Bharma.com",
      "name": "Manny Bharma",
      "products": [
        {
          "productId": 1,
          "name": "Product A",
          "price": 100.05
        },
        {
          "productId": 2,
          "name": "Product B",
          "price": 200.1
        },
        {
          "productId": 3,
          "name": "Product C",
          "price": 300.05
        }
      ]
    },
    "orderDetails": [
      {
        "orderDetailId": 4,
        "price": 100.05,
        "productDescription": "1 X Product A"
      },
      {
        "orderDetailId": 5,
        "price": 200.1,
        "productDescription": "1 X Product B"
      },
      {
        "orderDetailId": 6,
        "price": 600.1,
        "productDescription": "2 X Product C"
      }
    ],
    "totalPrice": 900.25
  }
]
 ```
### Página web
[http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-web/](http://ec2-52-14-66-207.us-east-2.compute.amazonaws.com:8080/productManager-web/)

![https://sumelio.github.io/productManager-SpringRestApi-Angular/resources/web.png](https://sumelio.github.io/productManager-SpringRestApi-Angular/resources/web.png)



### Script base de datos Mysql
 
 ```
create database beitech_db;
 
use beitech_db;
 
CREATE USER 'beithecUser'@'localhost' IDENTIFIED BY 'beithecUser-2017';

GRANT ALL PRIVILEGES ON beitech_db. * TO 'beithecUser'@'localhost';

CREATE TABLE IF NOT EXISTS `product` (
  `product_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,3) NOT NULL,
  PRIMARY KEY (`product_id`));

CREATE TABLE `customer` (
  `customer_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`customer_id`));

 
create table `order_`(
    order_id int not null auto_increment,  
    customer_id int not null,  
    order_time datetime not null, 
    delivery_address varchar(255) not null, 
    primary key(order_id), index(customer_id), 
    FOREIGN key (customer_id) references customer(customer_id)) ;

CREATE TABLE IF NOT EXISTS `customer_available_product` (
  `customer_id` INT(11) NOT NULL,
  `product_id` INT(11) NOT NULL,
 PRIMARY KEY (`customer_id`,`product_id`),
  CONSTRAINT `customer_product_ibfk_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`customer_id`),
  CONSTRAINT `customer_product_ibfk_2`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`product_id`));

 
CREATE TABLE IF NOT EXISTS `order_detail` (
  `order_detail_id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_description` VARCHAR(255) NOT NULL,
  `price` DECIMAL(10,3) NOT NULL,
  `order_id` INT(11) NOT NULL,
   PRIMARY KEY (`order_detail_id`),
  INDEX `order_id` (`order_id` ASC),
  CONSTRAINT `order_detail_ibfk_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order_` (`order_id`));



INSERT INTO `customer` (`customer_id`, `name`, `email`) VALUES (1, 'Manny Bharma', 'manny@Bharma.com');
INSERT INTO `customer` (`customer_id`, `name`, `email`) VALUES (2, 'Alan Briggs', 'alan@Briggs.com');
INSERT INTO `customer` (`customer_id`, `name`, `email`) VALUES (3, 'Mike Simm', 'mike@Simm.com');

INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (1, 'Product A', 100.05);
INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (2, 'Product B', 200.10);
INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (3, 'Product C', 300.05);
INSERT INTO `product` (`product_id`, `name`, `price`) VALUES (4, 'Product D', 400.25);

INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (1, 1);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (1, 2);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (1, 3);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (2, 2);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (3, 1);
INSERT INTO `customer_available_product` (`customer_id`, `product_id`) VALUES (3, 4);

```
