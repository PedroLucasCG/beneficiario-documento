# BENEFICIÁRIO DOCUMENTO

Essa API gerência o CRUD da entidade beneficiário junto a sua entidade subordinada documento,
além de contar com autenticação.

## O que temos nesse projeto?
* Essa é uma API desenvolvida com Java Spring Boot 
* A API foi construída usando os princípios do DDD
* Essa API também conta com autenticação do Spring Security

## Como usar essa API?
1 - Abra o projeto e gere o .jar com o comando abaixo:
```
mvn clean package
```
Esse comando deve ser executado no terminal na pasta raíz do projeto

2 - Após compilar é executar o .jar da maneira que preferir, ele reside na pasta target/ a partir
da raíz do projeto

## Considerações sobre o projeto
* Foi requisitado o endpoint para retornar todos os beneficiários, porém não foi requisitado.
o endpoint para buscar por ‘id’, por isso que apesar de implementado o método no repositório, não há
endpoint para buscar beneficiário singular.
* Na implementação da autenticação como não foi posto regras para como deve ser implementada essa parte
do projeto, ficou aparente algumas abordagens para que essa seja feita:
    * Implementar um dominio especifico para autenticação
    * Implementar autenticacao no beneficiário
    * Implementar ambos
  
    Foi escolhida a primeira devido a sua simplicidade, a segundo exigiria expor o endpoint que cria
    beneficiário como público ou alterar como este funciona; a terceira introduz niveis de complexidade
    que não se justificam para esse projeto.
* Foram implementados testes para todos os services.

## Endpoints da API

### As informações abaixo também podem ser vistas pelo swagger (OpenAPI) através do link abaixo (local):
http://localhost:8080/beneficiario-documento/api/beneficiario-documento/api/public/swagger

Base URL (para uso local):

[http://localhost:8080/beneficiario-documento/api](http://localhost:8080/beneficiario-documento/api)

A API utiliza autenticação JWT Bearer Token para endpoints protegidos.

**POST** `/v1/authentication/register`

Cria um novo usuário para acesso à API.

**Request Body**
````
{
  "email": "999999999999@site.com.br",
  "senha": "SenhaSegura@123"
} 
````

**Resposta**

`200 OK`

---

### Login

**POST** `/v1/authentication/login`

Realiza autenticação do usuário e retorna o token JWT.

**Request Body**

```json
{
  "email": "999999999999@site.com.br",
  "senha": "SenhaSegura@123"
}
```

**Resposta**

`200 OK`

```json
{
  "expiracao": "2026-07-18T15:00:00",
  "token": "jwt-token"
}
```

---

## Beneficiário

### Cadastrar beneficiário com documentos

**POST** `/v1/beneficiario`

Cadastra um beneficiário juntamente com seus documentos.

**Autenticação:** Bearer Token

**Request Body**

```json
{
  "nome": "João Silva",
  "telefone": "11999999999",
  "dataNascimento": "1990-01-01",
  "documentosSalvarRequests": [
    {
      "tipoDocumento": "IDENTIDADE",
      "nome": "RG",
      "descricao": "Documento de identidade"
    }
  ]
}
```

**Resposta**

`201 Created`

---

### Listar todos os beneficiários

**GET** `/v1/beneficiario/todos`

Retorna todos os beneficiários cadastrados.

**Autenticação:** Bearer Token

**Resposta**

`200 OK`

---

### Atualizar beneficiário

**PATCH** `/v1/beneficiario/{idBeneficiario}`

Atualiza os dados cadastrais de um beneficiário.

**Autenticação:** Bearer Token

**Path Parameter**

| Parâmetro      | Tipo |
| -------------- | ---- |
| idBeneficiario | UUID |

**Request Body**

```json
{
  "nome": "João Silva Atualizado",
  "telefone": "11988888888",
  "dataNascimento": "1990-01-01"
}
```

**Resposta**

`204 No Content`

---

### Remover beneficiário

**DELETE** `/v1/beneficiario/{idBeneficiario}`

Remove um beneficiário.

**Autenticação:** Bearer Token

**Path Parameter**

| Parâmetro      | Tipo |
| -------------- | ---- |
| idBeneficiario | UUID |

**Resposta**

`204 No Content`

---

## Documentos

### Listar documentos do beneficiário

**GET** `/v1/beneficiario/{idBeneficiario}/documento`

Retorna todos os documentos associados ao beneficiário.

**Autenticação:** Bearer Token

**Path Parameter**

| Parâmetro      | Tipo |
| -------------- | ---- |
| idBeneficiario | UUID |

**Resposta**

`200 OK`

```json
[
  {
    "id": "uuid",
    "tipoDocumento": "IDENTIDADE",
    "nome": "RG",
    "descricao": "Documento de identidade",
    "dataInclusao": "2026-07-18",
    "dataAtualizacao": "2026-07-18"
  }
]
```

---

## Tipos de documento disponíveis

* `FINANCEIRO`
* `LEGAL`
* `ORDINARIO`
* `IDENTIDADE`

---

## Autorização

Para endpoints protegidos, enviar o header:

```
Authorization: Bearer {token}
```

## Segue a estrutura de pastas do projeto

```
com.wakanda.beneficiario_documento
│
├── authentication
│   ├── application
│   │   ├── api
│   │   └── service
│   ├── domain
│   └── infra
│
├── beneficiario
│   ├── application
│   │   ├── api
│   │   └── service
│   ├── domain
│   └── infra
│
├── documento
│   ├── application
│   │   ├── api
│   │   └── service
│   ├── domain
│   └── infra
│
├── config
│   ├── cors
│   ├── openapi_docs
│   └── security
│
├── handler
│
└── BeneficiarioDocumentoApplication
```
