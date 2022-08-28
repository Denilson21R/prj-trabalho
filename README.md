### ENDPOINTS

1. GET ```/user```
- Retorna todos os usuarios

2. GET ```/user/{id}```
- Retorna os dados de um usuário

3. POST ```/user```
- Parêmetros

| Nome     | Obrigatório |  Tipo  |
|----------|-------------|:------:|
| name     | sim         | string |
| phone    | sim         | string |
| email    | sim         | string |
| password | sim         | string |
| type     | sim         |  int   |
| company  | não         |  int   |
- Adiciona um novo usuário e retorna seus dados

4. PUT ```/user/{id}```
- Atualiza um usuário e retorna os dados novos

5. GET ```/user/{id}/animals```
- Retorna os animais de um usuário

6. GET ```/animal/{id}```
- Retorna os dados de um animal

7. POST ```/animal```
- Adiciona um novo animal e retorna seus dados

8. PUT ```/animal/{id}```
- Atualiza um animal e retorna os dados novos

9. DELETE ```/animal/{id}```
- Deleta um animal e retorna 204 em caso de sucesso