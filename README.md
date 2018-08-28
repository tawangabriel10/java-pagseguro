### Java API REST para Integração com o PagSeguro
Exemplo de aplicação REST com integração com PagSeguro.

### Tecnologias Utilizadas
- Spring Boot
- Spring MVC
- Maven
- Docker
- API PagSeguro
- Mysql (Pendente)

### Instruções

- Baixar/clonar o projeto;

- Execute o comando mvn já iniciando o Spring Boot; 

### Build/Start do sistema

```
mvn clean install spring-boot:run
```


### Lista de endpoints cadastrados:

- Registrar Checkout simples de pagamento no Pagseguro, caso sucesso é retornado uma URL de redirecionamento:
```
POST http://localhost:8080/checkout/register
```
- JSON de exemplo para a requisição:



- Registrar Checkout transparente por meio de boleto bancario, caso sucesso é retornado uma URL de redirecionamento:
```sh
POST http://localhost:8080/checkout/transparente/boleto-bancario
```
- JSON de exemplo para a requisição checkout transparente boleto bancario:
```sh
{
   "modoPagamento": "default",
   "id": 1,
   "moeda": "BRL",
   "precoExtra": 100.00,
   "referencia": "LIBJAVA_DIRECT_PAYMENT",
   "remetente": {
        "id": 1,
        "nome": "Jose Comprador",
        "cpf": "99999999999",
        "email": "comprador@uol.com.br",
        "hash": "12345",
        "telefone": {
            "id": 1,
            "codigoArea": "99",
            "numero": "99999999"
        }
   },
   "remessa": {
        "tipo": "SEDEX",
        "custo": 10.00,
        "endereco": {
            "codigoPostal": "99999999",
            "pais": "BRA",
            "estado": "SP",
            "cidade": "Cidade Exemplo",
            "complemento": "99o andar",
            "distrito": "Jardim Internet",
            "numero": "9999",
            "rua": "Av. PagSeguro"
        }
   },
   "produtos": [{
        "id": 1,
        "descricao": "Produto PagSeguroI",
        "preco": 99999.99,
        "quantidade": 1,
        "peso": 1000
   }, {
    "id": 2,
    "descricao": "Produto PagSeguroII",
    "preco": 99999.98,
    "quantidade": 2,
    "peso": 750
   }],
   "cartaoCredito": null
}
```


- Registrar Checkout transparente por meio de cartão de credito, caso sucesso é retornado uma URL de redirecionamento:
```sh
POST http://localhost:8080/checkout/transparente/cartao-credito
```
- JSON de exemplo para a requisição checkout cartao de credito:
```sh
{
   "modoPagamento": "default",
   "id": 1,
   "moeda": "BRL",
   "precoExtra": 100.00,
   "referencia": "LIBJAVA_DIRECT_PAYMENT",
   "remetente": {
        "id": 1,
        "nome": "Jose Comprador",
        "cpf": "99999999999",
        "email": "comprador@uol.com.br",
        "hash": "12345",
        "telefone": {
            "id": 1,
            "codigoArea": "99",
            "numero": "99999999"
        }
   },
   "remessa": {
        "tipo": "SEDEX",
        "custo": 10.00,
        "endereco": {
            "codigoPostal": "99999999",
            "pais": "BRA",
            "estado": "SP",
            "cidade": "Cidade Exemplo",
            "complemento": "99o andar",
            "distrito": "Jardim Internet",
            "numero": "9999",
            "rua": "Av. PagSeguro"
        }
   },
   "produtos": [{
        "id": 1,
        "descricao": "Produto PagSeguroI",
        "preco": 99999.99,
        "quantidade": 1,
        "peso": 1000
   }, {
    "id": 2,
    "descricao": "Produto PagSeguroII",
    "preco": 99999.98,
    "quantidade": 2,
    "peso": 750
   }],
   "cartaoCredito": {
       "token": "12345",
       "titular": {
           "nome": "",
           "dataAniversario": ,
           "telefone": {

           },
           "documentos": [{}]
       },
       "enderecoCobranca": {
           
       }
   }
}
```


- Registrar Checkout transparente por meio de debito online, caso sucesso é retornado uma URL de redirecionamento:
```sh
POST http://localhost:8080/checkout/transparente/debito-online
```
- JSON de exemplo para a requisição:


