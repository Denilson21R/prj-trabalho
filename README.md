### ENDPOINTS

1. POST ```/user/authenticate```
   - Verifica e-mail e senha de um usuário e retorna seus dados completos se estiverem corretos

2. POST ```/user```
   - Adiciona um novo usuário e retorna seus dados

3. GET ```/user/{id}```
    - Retorna os dados de um usuário

4. PUT ```/user/{id}```
   - Atualiza um usuário e retorna os dados novos

5. GET ```/user/{id}/animals```
   - Retorna uma lista com os animais de um usuário

6. GET ```/animal/{id}```
   - Retorna os dados de um animal

7. POST ```/animal```
   - Adiciona um novo animal e retorna seus dados

8. PUT ```/animal/{id}```
   - Atualiza um animal e retorna os dados novos

9. DELETE ```/animal/{id}```
   - Deleta um animal e retorna 204 em caso de sucesso

10. GET ```/company/{id}```
    - Retorna os dados de uma empresa

11. POST ```/company```
    - Adiciona uma empresa e retorna seus dados
    
12. PUT ```/company/{id}```
    - Atualiza uma empresa e retorna seus dados

13. GET ```/permission/{id}```
    - Retorna os dados de uma permissão

14. POST ```/permission/```
    - Adiciona uma permissão e retorna seus dados

15. PUT ```/permission/{id}```
    - Atualiza uma permissão e retorna seus dados

16. DELETE ```/permission/{id}```
    - Deleta uma permissão e retorna 204 em caso de sucesso

17. GET ```/company/{id}/services```
    - Retorna todos os serviços de uma empresa

18. GET ```/service/{id}```
    - Retorna os dados de um serviço

19. POST ```/service```
    - Adiciona um serviço e retorna seus dados

20. PUT ```/service/{id}```
    - Atualiza um serviço e retorna seus dados

21. DELETE ```/service/{id}```
    - Deleta um serviço e retorna 204 em caso de sucesso

23. GET ```/schedule/{id}```
    - Retorna os dados de um agendamento

24. GET ```/company/{id}/schedules```
    - Retorna todos os agendamentos de uma empresa

25. POST ```/schedule```
    - Adiciona um agendamento e retorna seus dados

26. PUT ```/schedule/{id}```
    - Atualiza um agendamento e retorna seus dados

27. DELETE ```/schedule/{id}```
    - Deleta uma nota e retorna 204 em caso de sucesso

28. GET ```/schedule/{id}/annotation```
    - Retorna todas as notas de um agendamento

29. GET ```/annotation/{id}```
    - Retorna os dados de uma nota

30. POST ```/annotation```
    - Adiciona uma nota e retorna seus dados

31. PUT ```/annotation/{id}```
    - Atualiza uma nota e retorna seus dados

32. DELETE ```/annotation/{id}```
    - Deleta uma nota e retorna 204 em caso de sucesso