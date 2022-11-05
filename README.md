## ENDPOINTS

#### 1. POST ```/user/authenticate```
- Parêmetros

|   nome    |  obrigatório  |  tipo   | 
|:---------:|:-------------:|:-------:|
| password  |  obrigatório  | string  |
|   email   |  obrigatório  | string  |

- Retornos

|     dados      | status |      condição       |
|:--------------:|:------:|:-------------------:|
| objeto usuário |  200   |       sucesso       |
|                |  204   | usuário inexistente |

#### 2. GET ```/user/{id}```
- Parêmetros

| nome  |  obrigatório  |     tipo      | 
|:-----:|:-------------:|:-------------:|
|  id   |  obrigatório  | string na url |

- Retornos

|     dados      | status |      condição       |
|:--------------:|:------:|:-------------------:|
| objeto usuário |  200   |       sucesso       |
|                |  404   | usuário inexistente |

#### 3. POST ```/user```
- Parêmetros

|   nome   | obrigatório |    tipo    | 
|:--------:|:-----------:|:----------:|
|   name   | obrigatório |   string   |
| password | obrigatório |   string   |
|  email   | obrigatório |   string   |
|  phone   |  opcional   |   string   |
|   type   | obrigatório |  inteiro   |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto usuário |  201   |           sucesso           |
|                |  500   |            erro             |
|                |  422   | ausência de algum parâmetro |

#### 4. PUT ```/user/{id}```
- Parêmetros

|   nome   | obrigatório |     tipo      | 
|:--------:|:-----------:|:-------------:|
|    id    | obrigatório | string na url |
|   name   | obrigatório |    string     |
| password |  opcional   |    string     |
|  email   | obrigatório |    string     |
|  phone   |  opcional   |    string     |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto usuário |  200   |           sucesso           |
|                |  500   |            erro             |
|                |  422   | ausência de algum parâmetro |
|                |  404   |   usuário não encontrado    |

#### 5. GET ```/user/{id}/animals```
- Parêmetros

|   nome   | obrigatório |     tipo      | 
|:--------:|:-----------:|:-------------:|
|    id    | obrigatório | string na url |

- Retornos

|          dados          | status | condição |
|:-----------------------:|:------:|:--------:|
| lista de objetos animal |  200   | sucesso  |
|                         |  500   |   erro   |

#### 6. GET ```/animal/{id}```
- Parêmetros

|   nome   | obrigatório |     tipo      | 
|:--------:|:-----------:|:-------------:|
|    id    | obrigatório | string na url |

- Retornos

|     dados     | status |       condição        |
|:-------------:|:------:|:---------------------:|
| objeto animal |  200   |        sucesso        |
|               |  404   | animal não encontrado |
|               |  500   |         erro          |

#### 7. POST ```/animal```
- Parêmetros

|    nome     | obrigatório |  tipo  | 
|:-----------:|:-----------:|:------:|
| description | obrigatório | string |
|    name     | obrigatório | string |
|    owner    | obrigatório |  long  |
|   specie    | obrigatório | string |

- Retornos

|     dados     | status |          condição           |
|:-------------:|:------:|:---------------------------:|
| objeto animal |  201   |           sucesso           |
|               |  422   | ausência de algum parâmetro |
|               |  500   |            erro             |

#### 8. PUT ```/animal/{id}```
- Parêmetros

|    nome     | obrigatório |  tipo   | 
|:-----------:|:-----------:|:-------:|
| description |  opcional   | string  |
|    name     |  opcional   | string  |
|   status    |  opcional   | inteiro |

- Retornos

|     dados     | status |       condição        |
|:-------------:|:------:|:---------------------:|
| objeto animal |  200   |        sucesso        |
|               |  422   | animal não encontrado |
|               |  500   |         erro          |

#### 9. POST ```/company```
- Parêmetros

| nome  | obrigatório |  tipo   | 
|:-----:|:-----------:|:-------:|
| name  | obrigatório | string  |
| email | obrigatório | string  |
| cnpj  |  opcional   | string  |
| user  | obrigatório | inteiro |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto empresa |  201   |           sucesso           |
|                |  422   | ausência de algum parâmetro |
|                |  500   |            erro             |

#### 10. GET ```/company/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|     dados      | status |        condição        |
|:--------------:|:------:|:----------------------:|
| objeto empresa |  200   |        sucesso         |
|                |  404   | empresa não encontrada |

#### 11. GET ```/companies```
- Requisição sem parêmetros

- Retornos

|          dados           | status | condição |
|:------------------------:|:------:|:--------:|
| lista de objetos empresa |  200   | sucesso  |
|                          |  500   |   erro   |

#### 12. PUT ```/company/{id}```
- Parêmetros

| nome  | obrigatório |  tipo  | 
|:-----:|:-----------:|:------:|
| email | obrigatório | string |
| name  | obrigatório | string |
| cnpj  |  opcional   | string |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto empresa |  200   |           sucesso           |
|                |  404   |   empresa não encontrada    |
|                |  500   |            erro             |
|                |  422   | ausência de algum parâmetro |

#### 13. GET ```/company/{id}/users```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|          dados           | status | condição |
|:------------------------:|:------:|:--------:|
| lista de objetos usuário |  200   | sucesso  |
|                          |  500   |   erro   |

#### 14. POST ```/permission/```
- Parêmetros

|       nome        | obrigatório |   tipo   | 
|:-----------------:|:-----------:|:--------:|
|      company      | obrigatório | integer  |
|       user        | obrigatório | integer  |
|   company_owner   | obrigatório | integer  |
| can_add_schedules | obrigatório | booleano |
| can_add_services  | obrigatório | booleano |

- Retornos

|      dados       | status |          condição           |
|:----------------:|:------:|:---------------------------:|
| objeto permissão |  201   |           sucesso           |
|                  |  500   |            erro             |
|                  |  422   | ausência de algum parâmetro |

#### 15. GET ```/permission/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|      dados       | status |         condição         |
|:----------------:|:------:|:------------------------:|
| objeto permissão |  200   |         sucesso          |
|                  |  500   |           erro           |
|                  |  404   | permissão não encontrada |

#### 16. PATCH ```/permission/{id}```
- Parêmetros

|   nome    | obrigatório |     tipo      | 
|:---------:|:-----------:|:-------------:|
|    id     | obrigatório | string na url |
| schedules | obrigatório |   booleano    |
| services  | obrigatório |   booleano    |

- Retornos

|      dados       | status |           condição            |
|:----------------:|:------:|:-----------------------------:|
| objeto permissão |  200   |            sucesso            |
|                  |  500   |             erro              |
|                  |  404   |   permissão não encontrada    |
|                  |  422   | ausência de alguns parâmetros |

#### 17. GET ```/company/{id}/services```
- Parêmetros

|   nome    | obrigatório |     tipo      | 
|:---------:|:-----------:|:-------------:|
|    id     | obrigatório | string na url |

- Retornos

|          dados           | status | condição |
|:------------------------:|:------:|:--------:|
| lista de objetos serviço |  200   | sucesso  |
|                          |  500   |   erro   |

#### 18. POST ```/service```
- Parêmetros

|    nome     | obrigatório |  tipo   | 
|:-----------:|:-----------:|:-------:|
| description | obrigatório | string  |
|    value    | obrigatório |  float  |
|   company   | obrigatório | inteiro |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto serviço |  201   |           sucesso           |
|                |  500   |            erro             |
|                |  422   | ausência de algum parâmetro |

#### 19. GET ```/service/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|     dados      | status |        condição        |
|:--------------:|:------:|:----------------------:|
| objeto serviço |  200   |        sucesso         |
|                |  500   |          erro          |
|                |  404   | serviço não encontrado |

#### 20. PUT ```/service/{id}```
- Parêmetros

|    nome     | obrigatório |                  tipo                  | 
|:-----------:|:-----------:|:--------------------------------------:|
|     id      | obrigatório |             string na url              |
| description | obrigatório |                 string                 |
|    value    | obrigatório |                 float                  |
|   status    | obrigatório | integer(0 para ativo e 1 para inativo) |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto serviço |  200   |           sucesso           |
|                |  500   |            erro             |
|                |  404   |   serviço não encontrado    |
|                |  422   | ausência de algum parâmetro |

#### 21. GET ```/schedules/client/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|            dados             | status | condição |
|:----------------------------:|:------:|:--------:|
| lista de objetos agendamento |  200   | sucesso  |
|                              |  500   |   erro   |

#### 22. GET ```/schedule/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|       dados        | status |          condição          |
|:------------------:|:------:|:--------------------------:|
| objeto agendamento |  200   |          sucesso           |
|                    |  500   |            erro            |
|                    |  404   | agendamento não encontrado |

#### 23. POST ```/schedule```
- Parêmetros

|       nome        | obrigatório |                 tipo                 | 
|:-----------------:|:-----------:|:------------------------------------:|
|     services      | obrigatório | array de string separado por vírgula |
|      amount       | obrigatório |                float                 |
|       date        | obrigatório |            localdatetime             |
|      animal       | obrigatório |               inteiro                |
|      company      | obrigatório |               inteiro                |
| employee_schedule | obrigatório |               inteiro                |

- Retornos

|       dados        | status |          condição           |
|:------------------:|:------:|:---------------------------:|
| objeto agendamento |  201   |           sucesso           |
|                    |  500   |            erro             |
|                    |  422   | ausência de algum parâmetro |

#### 25. PUT ```/schedule/{id}```
- Parêmetros

|   nome   | obrigatório |                 tipo                 | 
|:--------:|:-----------:|:------------------------------------:|
| services | obrigatório | array de string separado por vírgula |
|  status  |  opcional   |               inteiro                |
|   paid   |  opcional   |               booleano               |

- Retornos

|       dados        | status |          condição          |
|:------------------:|:------:|:--------------------------:|
| objeto agendamento |  200   |          sucesso           |
|                    |  500   |            erro            |
|                    |  404   | agendamento não encontrado |

#### 24. GET ```/company/{id}/schedules```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|       dados        | status | condição |
|:------------------:|:------:|:--------:|
| objeto agendamento |  200   | sucesso  |
|                    |  500   |   erro   |

#### 26. GET ```/schedules/company/{id}/quantity```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|  dados  | status | condição |
|:-------:|:------:|:--------:|
| inteiro |  200   | sucesso  |
|         |  500   |   erro   |

#### 27. GET ```/schedules/user/{id}/quantity```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|  dados  | status | condição |
|:-------:|:------:|:--------:|
| inteiro |  200   | sucesso  |
|         |  500   |   erro   |

#### 28. POST ```/service-request```
- Parêmetros

|  nome   | obrigatório |     tipo      | 
|:-------:|:-----------:|:-------------:|
| animal  | obrigatório | string na url |
|  user   | obrigatório | string na url |
| company | obrigatório | string na url |
|  date   | obrigatório | string na url |

- Retornos

|          dados           | status |          condição           |
|:------------------------:|:------:|:---------------------------:|
| objeto pedido de serviço |  201   |           sucesso           |
|                          |  500   |            erro             |
|                          |  422   | ausência de algum parâmetro |

#### 29. PUT ```/service-request/{id}```
- Parêmetros

|  nome  | obrigatório |     tipo      | 
|:------:|:-----------:|:-------------:|
|   id   | obrigatório | string na url |
| animal |  opcional   |    inteiro    |
| status |  opcional   |    inteiro    |
|  date  |  opcional   | localdatetime |

- Retornos

|          dados           | status |       condição        |
|:------------------------:|:------:|:---------------------:|
| objeto pedido de serviço |  200   |        sucesso        |
|                          |  500   |         erro          |
|                          |  404   | pedido não encontrado |

#### 30. PATCH ```/service-request/{id}/services```
- Parêmetros

|   nome   | obrigatório |                 tipo                 | 
|:--------:|:-----------:|:------------------------------------:|
|    id    | obrigatório |            string na url             |
| services | obrigatório | array de string separado por vírgula |

- Retornos

|          dados           | status |          condição           |
|:------------------------:|:------:|:---------------------------:|
| objeto pedido de serviço |  200   |           sucesso           |
|                          |  500   |            erro             |
|                          |  404   |    pedido não encontrado    |
|                          |  422   | ausência de algum parâmetro |

#### 31. GET ```/requests/company/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|               dados                | status | condição |
|:----------------------------------:|:------:|:--------:|
| lista de objetos pedido de serviço |  200   | sucesso  |
|                                    |  500   |   erro   |

#### 32. GET ```/requests/user/{id}```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|               dados                | status | condição |
|:----------------------------------:|:------:|:--------:|
| lista de objetos pedido de serviço |  200   | sucesso  |
|                                    |  500   |   erro   |

#### 33. GET ```/requests/user/{id}/quantity```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|  dados  | status | condição |
|:-------:|:------:|:--------:|
| inteiro |  200   | sucesso  |
|         |  500   |   erro   |

#### 34. GET ```/requests/company/{id}/quantity```
- Parêmetros

| nome | obrigatório |     tipo      | 
|:----:|:-----------:|:-------------:|
|  id  | obrigatório | string na url |

- Retornos

|  dados  | status | condição |
|:-------:|:------:|:--------:|
| inteiro |  200   | sucesso  |
|         |  500   |   erro   |

#### 35. POST ```/invite```
- Parêmetros

|  nome   | obrigatório |  tipo   | 
|:-------:|:-----------:|:-------:|
|  email  | obrigatório | string  |
| company | obrigatório | inteiro |

- Retornos

|     dados      | status |          condição           |
|:--------------:|:------:|:---------------------------:|
| objeto convite |  201   |           sucesso           |
|                |  500   |            erro             |
|                |  404   |   convite não encontrado    |
|                |  422   | ausência de algum parâmetro |

#### 36. PATCH ```/invite/{id}```
- Parêmetros

|  nome  | obrigatório |     tipo      | 
|:------:|:-----------:|:-------------:|
|   id   | obrigatório | string na url |
| status | obrigatório |    inteiro    |

- Retornos

|     dados      | status |           condição           |
|:--------------:|:------:|:----------------------------:|
| objeto convite |  200   |           sucesso            |
|                |  500   |             erro             |
|                |  404   |    convite não encontrado    |
|                |  422   | ausência do parâmetro status |

#### 37. GET ```/invites/user/{id}```
- Parêmetros

|  nome  | obrigatório |     tipo      | 
|:------:|:-----------:|:-------------:|
|   id   | obrigatório | string na url |

- Retornos

|          dados           | status | condição |
|:------------------------:|:------:|:--------:|
| lista de objetos convite |  200   | sucesso  |
|                          |  500   |   erro   |

#### 38. GET ```/invites/company/{id}```
- Parêmetros

|  nome  | obrigatório |     tipo      | 
|:------:|:-----------:|:-------------:|
|   id   | obrigatório | string na url |

- Retornos

|          dados           | status | condição |
|:------------------------:|:------:|:--------:|
| lista de objetos convite |  200   | sucesso  |
|                          |  500   |   erro   |