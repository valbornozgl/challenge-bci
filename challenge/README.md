# API Rest
API Rest para alta y consulta de usuarios.

# Instrucciones:
La API tiene 2 recursos:
# Recurso /sign-up
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

# Recurso /login
Recibe un token, que fue dado en su alta,