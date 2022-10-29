### ENDPOINTS

1. POST ```/user/authenticate```
   - Verifica e-mail e senha de um usuário e retorna seus dados completos se estiverem corretos

2. GET ```/user/{id}```
    - Retorna os dados de um usuário

3. POST ```/user```
   - Adiciona um novo usuário e retorna seus dados

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

9. POST ```/company```
   - Adiciona uma empresa e retorna seus dados

10. GET ```/company/{id}```
    - Retorna os dados de uma empresa

11. GET ```/companies```
    - Retorna uma lista com todas as empresas ativas
    
12. PUT ```/company/{id}```
    - Atualiza uma empresa e retorna seus dados

13. GET ```/company/{id}/users```
    - Retorna todos os usuarios de uma empresa

14. POST ```/permission/```
    - Adiciona uma permissão e retorna seus dados

15. GET ```/permission/{id}```
    - Retorna os dados de uma permissão

16. PATCH ```/permission/{id}```
    - Atualiza uma permissão e retorna seus dados

17. DELETE ```/permission/{id}```
    - Deleta uma permissão e retorna 204 em caso de sucesso

18. GET ```/company/{id}/services```
    - Retorna todos os serviços de uma empresa

19. POST ```/service```
    - Adiciona um serviço e retorna seus dados

20. GET ```/service/{id}```
    - Retorna os dados de um serviço

21. PUT ```/service/{id}```
    - Atualiza um serviço e retorna seus dados

22. GET ```/schedules/client/{id}```
    - Retorna os dados de um agendamento

23. GET ```/schedule/{id}```
    - Retorna os dados de um agendamento

24. GET ```/company/{id}/schedules```
    - Retorna todos os agendamentos de uma empresa

25. POST ```/schedule```
    - Adiciona um agendamento e retorna seus dados

26. PUT ```/schedule/{id}```
    - Atualiza um agendamento e retorna seus dados

27. GET ```/schedules/company/{id}/quantity```
    - Retorna a quantidade de agendamentos novos que a empresa tem hoje

28. GET ```/schedules/user/{id}/quantity```
    - Retorna a quantidade de agendamentos novos que o usuário tem hoje

29. POST ```/service-request```
    - Adiciona uma requisição de serviço e retorna seus dados

30. PUT ```/service-request/{id}```
    - Atualiza uma requisição de serviço e retorna seus dados

31. PATCH ```/service-request/{id}/services```
    - Atualiza uma lista com os serviços de uma requisição de serviço e retorna a requisição completa

32. GET ```/requests/company/{id}```
    - Retorna as requisições de serviço de uma empresa

33. GET ```/requests/user/{id}```
    - Retorna as requisições de serviço de um usuário

34. GET ```/requests/user/{id}/quantity```
    - Retorna a quantidade de pedidos abertos do usuário

35. GET ```/requests/user/{id}```
    - Retorna a quantidade de pedidos abertos da empresa

36. POST ```/invite```
    - Adiciona um convite para empresa e retorna seus dados

37. PATCH ```/invite/{id}```
    - Atualiza o status de um convite para empresa

38. GET ```/invites/user/{id}```
    - Retorna os convites para empresa de um usuário

39. GET ```/invites/company/{id}```
    - Retorna os convites para empresa de uma empresa