# Functions

## Data store
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## Document handling
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## External-api module
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## Recalc module
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## User handling
- Registration: _api_url/register endpoint. Request method POST. <br>Required data (JSON): <br>{ "username": "string", "password": "string", "email": "string", "fullName": "string" }<br>Response: <br>- JSON object with the user information (the one defined above), except the password, if the registration is successfull.<br>- If the username or the email address is already taken, 400 BadRequest._

- Authentication: _api_url/authenticate endpoint. Request method POST. <br>Required data (JSON): <br>{ "username": "string", "password": "string" }<br>Response: <br>- A generated JWT Token that belongs to the user.<br>- If the username or password is incorrect, 401 UnAuthorized._

- Profile creation: _api_url/create-profile endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "name": "string", "displayName": "string" }<br>Response: <br>- JSON object with the created profile information.<br>- If the user is not authorized, 401 UnAuthorized._

- Query all profiles: _api_url/profiles endpoint. Request method GET, the user has to be authenticated to be able to access this endpoint. <br>Response: <br>- JSON array with the profile informations.<br>- If the user is not authorized, 401 UnAuthorized._

- Role creation: _api_url/create-role endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "name": "string", "displayName": "string" }<br>Response: <br>- JSON object with the created role information.<br>- If the user is not authorized, 401 UnAuthorized._

- Assign role to profile: _api_url/assign-role endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "roleId": "int", "profileId": "int" }<br>Response: <br>- 200 OK, if the assignment is successfull.<br>- If the user is not authorized, 401 UnAuthorized._

- Query if the profile has a specific role assigned: _api_url/profile-has-role endpoint. Request method POST, the user has to be authenticated to be able to access this endpoint. <br>Required data (JSON): <br>{ "roleId": "int", "profileId": "int" }<br>Response: <br>- A boolean regarding the result.<br>- If the user is not authorized, 401 UnAuthorized._
