# IASA SC Analyst Shop API
**IASA student council analyst shop backend**

## Stack :computer:
Java, Spring Boot, Spring Data JPA, Spring Cloud, PostgreSQL

Swagger documentation

## Implementation details :bulb:
Database is located on Amazon S3. All images that are linked to entites are stored in Azure Blob storage. 

## Analyst shop :shirt:
*Selling merchandise*

Entities: ClothesBase, Print, StationeryItem, Photocard

## StationeryItem 

### JSON

````
{
  "id": 0,
  "type": "STICKER",
  "name": "Yellow sticker",
  "price": 0,
  "images": [
    {
      "id": 0,
      "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "image_url": "string"
    }
  ]
}
````

Types: *STICKER, STICKERPACK, NOTEBOOK, OTHER*

Specify only *type* and *name*

### HTTP methods

* **GET:** */api/stationery*

* **GET:** */api/stationery/{id}*

* **POST** */api/stationery*

Send request as a form-data: JSON and images

![post-request-example](https://user-images.githubusercontent.com/78265212/230292818-774fd50a-1791-4ea3-bcec-4f0e1f2aa6cd.png)
  
* **PUT** */api/stationery/{id}*

Send request as a form-data: JSON and images

Adds images to existing entity

* **DELETE** */api/stationery*

* **DELETE** */api/stationery/{id}*

## Photocard

### JSON

````
{
  "id": 0,
  "type": "NEW",
  "image": {
    "id": 0,
    "uuid": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "image_url": "string"
  }
}
````
Types: *OUR_CLIENTS, NEW, ARCHIVE*

Specify only *type*

### HTTP methods

* **GET:** */api/photocards*

* **GET:** */api/photocards/{id}*

* **POST** */api/photocards*

Send request as a form-data: JSON and image

![image](https://user-images.githubusercontent.com/78265212/230295653-763a4a49-c377-42eb-b133-ce729a35a824.png)
  
* **PUT** */api/photocards/{id}*

Send request as a form-data: JSON and image

Replaces image in the existing entity

* **DELETE** */api/photocards*

* **DELETE** */api/photocards/{id}*


