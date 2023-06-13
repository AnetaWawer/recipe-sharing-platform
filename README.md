# RECIPE SHARING PLATFORM API DOCUMENTATION

#### Welcome to the Recipe Sharing Platform API Documentation.

The platform allows users to crate an account, log in and perform various operations such as creating, updating, and deleting recipes, as well as searching for recipes based on different criteria.


# API Endpoints

<details> 
<summary>Register</summary>

Used to register new user.

**URL** : `/api/authentication/register`

**Method** : `POST`

**Data example**

```json
{
  "email":"example@email.com",
  "password":"ExamplePassword",
  "username":"John"
}
```

**Authentication required** : NO

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user with the same username already exists.

**Code** : `409 Conflict`

**Content** :

```
"There is already accounted registered on this username."
```

**Condition** : If user with the same email already exists.

**Code** : `409 Conflict`

**Content** :

```
"There is already accounted registered on this email."
```
</details>

<details> 
<summary>Login</summary>

Used to user login.

**URL** : `/api/authentication/login`

**Method** : `POST`

**Data example**

```json
{
  "username":"John",
  "password":"ExamplePassword"
}
```

**Authentication required** : NO

###  Success Response

**Code** : `200 OK`

**Content example**

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJKb2huIiwiaWF0IjoxNjg2NjcwMzY3LCJleHAiOjE2ODY2NzA5Njd9.pUZyuLhyMwjTR1E0kiBUYN-GwL4DuhcCgOrc4rS569Y"
}
```

### Error Response

**Condition** : If user is using wrong username and password combination.

**Code** : `400 Bad request`

**Content** :

```
"Wrong credentials. Try again."
```
</details>


<details> 
<summary> Get all recipes</summary>

Used to collect all recipes.

**URL** : `/api/recipes/`

**Method** : `GET`

**Parameters** : 
>`None`

**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```
</details>

<details> 
<summary> Get recipe by recipe id</summary>

Used to fetch recipe with specific recipe id.

**URL** : `/api/recipes/{recipe_id}`

**Method** : `GET`

**Parameters** :

| name      | type     | data type | description                           |
|-----------|----------|-----------|---------------------------------------|
| recipe_id | required | Long      | The specific recipe unique identifier |



**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```
</details>

<details> 
<summary> Get recipes by recipe username</summary>

Used to collect all recipes created by specific user.

**URL** : `/api/recipes/users/search`

**Method** : `GET`

**Parameters** :

| name     | type     | data type | description                               |
|----------|----------|-----------|-------------------------------------------|
| username | required | String    | Match or partial match of unique username |


**Example request** : `/api/recipes/users/search?username=John`

**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```
</details>

<details> 
<summary> Get recipes by recipe title</summary>

Used to collect all recipes with specific title.

**URL** : `/api/recipes/search/`

**Method** : `GET`

**Parameters** :

| name  | type     | data type | description                            |
|-------|----------|-----------|----------------------------------------|
| title | required | String    | Match or partial match of recipe title |


**Example request** : `/api/recipes/search?title=Pizza`

**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```
</details>

<details> 
<summary> Create recipe</summary>

Used for creating new recipes

**URL** : `/api/recipes/`

**Method** : `POST`

**Data example**

```json

{
  "description":"Pizza Margherita is a typical Neapolitan pizza, made with San Marzano tomatoes and mozzarella cheese.",
  "instructions":"Preheat oven to 200C. Prepare all ingredients for the margherita pizza.On a floured surface, roll the dough into the desired shape. Top it off with pizza sauce, pieces of fresh mozzarella.Bake pizza for about 14 minutes or until the crust is lightly browned and the cheese is golden.Remove pizza from the oven.",
  "servings": 4,
  "title": "Pizza margherita",
  "ingredients": [
    {
      "type":"pizza dough",
      "unit":"PC",
      "value":1
    },
    {
      "type":"pizza sauce",
      "unit":"ML",
      "value":200
    },
    {
      "type":"sliced mozarella",
      "unit":"G",
      "value":200
    }
  ]

}

```

**Authentication required** : YES

###  Success Response

**Code** : `200 OK`


### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```

**Condition** : If user choose wrong ingredient unit.

**Code** : `400 Bad Request`

**Content** :

```
"Invalid value provided for Units. Accepted values are: TSP, PC, G, PINCH, TBSP, KG, ML, L."
```

**Condition** : If user will does not fill required fields.

**Code** : `400 Bad Request`

**Content** :

```
"Ensure that fields are not empty!"
```
</details>

<details> 
<summary>Update existing recipe </summary>

Used to update recipe with specific recipe identifier.

**URL** : `/api/recipes/{recipe_id}`

**Method** : `PUT`

**Parameters** :

| name      | type     | data type   | description                           |
|-----------|----------|-------------|---------------------------------------|
| recipe_id | required | Long        | The specific recipe unique identifier |

**Data example**

```json

{
  "description":"Pizza Margherita is a typical Neapolitan pizza, made with San Marzano tomatoes and mozzarella cheese.",
  "instructions":"Preheat oven to 200C. Prepare all ingredients for the margherita pizza.On a floured surface, roll the dough into the desired shape. Top it off with pizza sauce, pieces of fresh mozzarella.Bake pizza for about 14 minutes or until the crust is lightly browned and the cheese is golden.Remove pizza from the oven.",
  "servings": 8,
  "title": "Pizza margherita",
  "ingredients": [
    {
      "type":"pizza dough",
      "unit":"PC",
      "value":1
    },
    {
      "type":"pizza sauce",
      "unit":"ML",
      "value":200
    },
    {
      "type":"sliced mozarella",
      "unit":"G",
      "value":400
    }
  ]

}

```


**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```

**Condition** : If user wants to update recipe that does not exists.

**Code** : `400 Bad request`

**Content** :

```
"There is no recipe with given id."
```

**Condition** : If user choose wrong ingredient unit.

**Code** : `400 Bad Request`

**Content** :

```
"Invalid value provided for Units. Accepted values are: TSP, PC, G, PINCH, TBSP, KG, ML, L."
```

**Condition** : If user will does not fill required fields.

**Code** : `400 Bad Request`

**Content** :

```
"Ensure that fields are not empty!"
```
</details>

<details> 
<summary>Delete existing recipe </summary>

Used to delete recipe with specific recipe identifier.

**URL** : `/api/recipes/{recipe_id}`

**Method** : `DELETE`

**Parameters** :

| name      | type     | data type | description                           |
|-----------|----------|-----------|---------------------------------------|
| recipe_id | required | Long      | The specific recipe unique identifier |



**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```
</details>

<details> 
<summary> Get recommended recipes based on weather forecast</summary>

Used to collect recipes based on weather forecast. 
Returns recipes that are not using frozen ingredients if outside temperature is lower than 5C or returns recipes that do not require baking if outside temperature is higher than 20C.

**URL** : `/api/recipes/recommended`

**Method** : `GET`

**Parameters** :

| name        | type     | data type | description                |
|-------------|----------|-----------|----------------------------|
| temperature | required | int       | Temperature chosen by user |



**Authentication required** : YES

###  Success Response

**Code** : `200 OK`

### Error Response

**Condition** : If user is not authorized.

**Code** : `401 Unauthorized`

**Content** :

```
"You need to be logged in to perform this action"
```
</details>