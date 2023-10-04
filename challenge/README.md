# MICROSERVICIO
Microservicio para alta y consulta de usuarios.

# Instrucciones:
El microservicio expone 2 endpoints:
# Endpoint /sign-up
Recibe un objeto UserRequest como parametro.
Responde un objeto UserResponse.

CURL de ejemplo para una creacion correcta: (si se envia dos veces recibiran el error de usuario existente)

curl --location 'http://localhost:8080/user/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"Victor Albornoz",
"email":"vic@globallogic.com",
"password":"d1dsdSdsdsd2",
"phones":[
{
"number":"123456",
"cityCode":1,
"countryCode":"80"
}
]
}'

CURL ejemplo de contraseña invalida

curl --location 'http://localhost:8080/user/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"Victor Albornoz",
"email":"vic@globallogic.com",
"password":"d1dsdsdsdsd2",
"phones":[
{
"number":"123456",
"cityCode":1,
"countryCode":"80"
}
]
}'

CURL ejemplo de mail incorrecto

curl --location 'http://localhost:8080/user/sign-up' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"Victor Albornoz",
"email":"vic@globallogiccom",
"password":"d1dsdSdsdsd2",
"phones":[
{
"number":"123456",
"cityCode":1,
"countryCode":"80"
}
]
}'

# Endpoint /login
Recibe un token, que fue dado en su alta, para consultar por usuario, que responde un objeto userdto con los datos del usuario.

CURL ejemplo consulta token (reemplazar el token por el obtenido en el sing up)

curl --location 'http://localhost:8080/user/login' \
--header 'Content-Type: application/json' \
--data '{
"token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWNAZ2xvYmFsbG9naWMuY29tIiwidXVpZCI6ImI4ZWUzMmI1OWIxZTQyMmVhMmRjOTg2MWNlOWU2ZjY0IiwiaWF0IjoxNjk2NDE4MzEwLCJleHAiOjE2OTY0MTgzNDZ9.fmFITTr4aysqL1FJUiF_Ma30fIRo4v06h--BXG6UcSM"
}'

EJEMPLO RESPUESTA OK

{
"id": 1,
"uuid": "b8ee32b59b1e422ea2dc9861ce9e6f64",
"created": "2023-10-04T11:18:30.621+00:00",
"lastLogin": "2023-10-04T11:18:30.621+00:00",
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ2aWNAZ2xvYmFsbG9naWMuY29tIiwidXVpZCI6ImI4ZWUzMmI1OWIxZTQyMmVhMmRjOTg2MWNlOWU2ZjY0IiwiaWF0IjoxNjk2NDE4MzEwLCJleHAiOjE2OTY0MTgzNDZ9.fmFITTr4aysqL1FJUiF_Ma30fIRo4v06h--BXG6UcSM",
"isActivo": true,
"name": "Victor Albornoz",
"email": "vic@globallogic.com",
"password": "$2a$10$Uiw.8ignTF4Q5Wx1roe6H.j/v6FNd.iUuh5ApfR5JMYHTr/tSGd7.",
"phones": [
{
"number": 123456,
"cityCode": 1,
"countryCode": "80"
}
]
}

#Data Base
Actualmente levantando la api se corre una base DB2 que corre en memoria.
Puede configurarse una base de  datos MYSQL descomentando la propiedad correspondiente en el application.properties y corriendo los
scripts correspondientes dentro de la carpeta scripts en el directorio de resources