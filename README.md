# Ecommerce Shop
> This is a fullstack online store project. The application includes the basic functions of an online store, as well as those that are a bit more advanced.  There is integration with a payment gateway in this application, sending emails and much more.

The application in a nutshell has such functionalities:

On the administrative side:
- Add, edit and delete products.
- Add, edit and delete categories
- Order management, including exporting orders to CSV file and viewing sales statistics in the form of a chart
- Moderation of user reviews (approval, deletion)

From the user side:
- Viewing products
- Searching for products by category
- Creating orders (adding products to cart, deleting, ordering)
- Adding product reviews 
- Viewing the list of your orders
- Email notifications

In a nutshell, this is the outline of the application. There is also a mechanism for registration, login and password reminders.

I don't know how long the application will run, but there is a demo on display, available at: https://shopfrontend-production-f14c.up.railway.app/

And for the administrator: https://shopfrontend-production-f14c.up.railway.app/admin
- The default login is: admin
- Default password is: test

## Table of Contents
* [Technologies Used](#technologies-used)
* [Screenshots](#screenshots)
* [Setup](#setup)
* [Contact](#contact)

## Technologies Used
- Java - version 17.0.3.1
- Spring Boot (with Spring Reactive Web, Spring Security, Spring Web, Spring Data JPA, Spring Test and other) - version 3.1.0
- MySQL - version 8.0.33
- Jjwt - version 4.0.0
- Liquibase - version 4.23.0
- Maven - version 4.0.0
- Lombok - version 1.18.28

## Screenshots
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/b93b50b4-c471-4a71-b062-a8690495c629)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/20021740-6085-4ea6-a77f-6a85060153a4)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/7bee2729-2464-4cc8-b25a-8e258bdb83ed)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/eea5586c-2f6d-454a-9a82-c80949b6d270)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/08c95581-2136-42ed-aaae-8eda8e7c981a)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/3948b43f-9d80-4d53-8a74-e6bbe2955f98)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/2390caec-be62-4e7d-b493-f68576d16796)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/f1602a19-409f-485d-98b7-294330dea7d6)
![image](https://github.com/jakubknap/ShopBackend/assets/93727414/5b501d0f-a038-43dd-9c08-e761bea2d8d1)


## Setup
- Clone [this repository](https://github.com/jakubknap/ShopBackend.git) to your IDE (https://github.com/jakubknap/ShopBackend.git)
- The project uses [Maven](https://maven.apache.org/) as a build tool. It already contains
`.mvn` wrapper script, so there's no need to install maven.
- Manually create the database at: localhost:3306
 ```bash
  CREATE DATABASE shop
```
- Manually create a directory 'data\productImages' in the root directory of the application
- To take full advantage of the potential of ap liking you must run it with the following parameters:
 ```bash
<this is for sending email>.

--spring.mail.username=<your email to gmail>
--spring.mail.password=<your password to gmail>

<If we set this propertis then we will have the "sending" of the email in the logs>.

--app.email.sender=fakeEmailService

<Needed for jwt>.

--jwt.secret=<your secret jwt string>
--springdoc.api-docs.enabled=true

<Here are the credentials for the payment gateway przelewy24, if you don't fill it in, just the online payment won't work>.

--app.payments.p24.merchantId=<your merchant id>
--app.payments.p24.posId=<your pos id>
--app.payments.p24.crc=<your crc>
--app.payments.p24.secretKey=<your secret key>
--app.payments.p24.testCrc=<your test crc>
--app.payments.p24.testSecretKey=<your test secret key>
```
If something is not configured, the functionality will not work

- To build and run the project execute the following command:
```bash
  mvn spring-boot:run
```
- You can check the functionality of the backend itself using swagger: http://localhost:8080/swagger-ui.html  Important! Remember about authentication

- The client application runs on localhost:8080.  The administrative application is available at localhost:8080/admin

## These are APIs that are available
### Default login to admin website: login: admin, password: test
- ![image](https://github.com/jakubknap/ShopBackend/assets/93727414/87149c07-f97d-47a1-8396-f30664e6d807)
- ![image](https://github.com/jakubknap/ShopBackend/assets/93727414/8966e5f9-d2fa-4c7c-b6fd-0aa2599964c4)
- ![image](https://github.com/jakubknap/ShopBackend/assets/93727414/223c601e-1f38-42a3-8be8-8d2edaf06838)
 




## Contact
Created by [Jakub Knap](https://www.linkedin.com/in/jakub-knap/) - feel free to contact me!

