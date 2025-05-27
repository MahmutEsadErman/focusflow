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