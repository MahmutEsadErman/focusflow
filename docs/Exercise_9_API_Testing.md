# FocusFlow Exercise 9 – API Testing

## Exercise 9.1
Base URL: http://localhost:8082 \

Endpoints:
* [GET] "/tasks/list"
  * list all tasks
    * RequestSchema:
      * /tasks/list
* [POST] "/tasks/create"
  * create a new task
    * RequestSchema:
      *  {\
        "title": "This is the first one",\
        "shortDescription": "short",\
        "longDescription": "This is the long description"\
        }
* [DELETE] "/tasks/delete/{id}"
  * deletes a task
    * RequestSchema:
      * /tasks/delete/1

## Exercise 9.2
Tests are made in Postman. Postman is a versatile tool to use and made testing process much more convenient. Yet it offers flexibility to adjust every parameter.

**Successful POST request Endpoint : /tasks/create**

**Request**

{
"title": "this should be title",\
"shortDescription": "short",\
"longDescription": "This is the long description"
}

**Response**

{
"timestamp": "2025-05-27T23:05:47.247112555",\
"status": 201,\
"message": "Task created successfully",\
"data": null\
}


**Unsuccessful POST request Endpoint : /tasks/create**

**Request**

This request has 2 errors. "titl" should be "title" and "shortDescription" should not be null. It can be seen in the response that succesfully raised 2 exceptions.

{
"titl": "this should be title",\
"shortDescription": "",\
"longDescription": "This is the long description"
}

**Response**

{
"path": "/tasks/create",\
"error": "Validation Error",\
"errors": {\
"shortDescription": "Short description is required",\
"title": "Title is required"},\
"timestamp": "2025-05-27T22:56:39.697948062",\
"status": 400
}

## Exercise 9.3

![All tests passed](./images/9.3.png)
