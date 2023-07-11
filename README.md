## IASA SC Analyst Shop API
*Selling merchandise*

## Stack :computer:
Java, Spring Boot, Spring Data, Azure Blob storage, PostgreSQL

Swagger documentation

## System design diagram :bulb:

![IASA_Analyst_Shop_System_design_diagram](https://github.com/IASAStudentCouncil/iasa-sc-analyst-shop-api/assets/78265212/b44b304a-cfcf-4eaf-a4e8-08be633d7b2f)

## Stationery item 

### JSON

````
{
  "type": "STICKER",
  "name": "Yellow sticker",
  "price": 0
}
````

Types: *STICKER, STICKERPACK, NOTEBOOK, OTHER*

### Endpoints

* **GET:** */api/stationery*

* **GET:** */api/stationery/{id}*

* **POST** */api/stationery*

Send request as a form-data: JSON and images

![image](https://user-images.githubusercontent.com/78265212/236442820-2afd9f24-e1ae-4cf7-860c-c30dead7ea10.png)
  
* **PUT** */api/stationery/{id}*

Send request as a form-data: JSON and images

Adds images to existing entity

* **DELETE** */api/stationery*

* **DELETE** */api/stationery/{id}*

## Photocard

### JSON

````
{
  "type": "NEW"
}
````
Types: *OUR_CLIENTS, NEW, ARCHIVE*

### Endpoints

* **GET:** */api/photocards*

* **GET:** */api/photocards/{id}*

* **POST** */api/photocards*

Send request as a form-data: JSON and image

![image](https://user-images.githubusercontent.com/78265212/236442897-d93c55f9-7890-4f2d-be42-63f812cd2e29.png)
  
* **PUT** */api/photocards/{id}*

Send request as a form-data: JSON and image

Replaces image in the existing entity

* **DELETE** */api/photocards*

* **DELETE** */api/photocards/{id}*


