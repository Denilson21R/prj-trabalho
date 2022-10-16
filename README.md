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

9. DELETE ```/animal/{id}```
   - Deleta um animal e retorna 204 em caso de sucesso

10. POST ```/company```
    - Adiciona uma empresa e retorna seus dados

11. GET ```/company/{id}```
    - Retorna os dados de uma empresa

12. GET ```/companies```
    - Retorna uma lista com todas as empresas ativas
    
13. PUT ```/company/{id}```
    - Atualiza uma empresa e retorna seus dados

14. GET ```/company/{id}/users```
    - Retorna todos os usuarios de uma empresa

15. POST ```/permission/```
    - Adiciona uma permissão e retorna seus dados

16. GET ```/permission/{id}```
    - Retorna os dados de uma permissão

17. PATCH ```/permission/{id}```
    - Atualiza uma permissão e retorna seus dados

18. DELETE ```/permission/{id}```
    - Deleta uma permissão e retorna 204 em caso de sucesso

19. GET ```/company/{id}/services```
    - Retorna todos os serviços de uma empresa

20. POST ```/service```
    - Adiciona um serviço e retorna seus dados

21. GET ```/service/{id}```
    - Retorna os dados de um serviço

22. PUT ```/service/{id}```
    - Atualiza um serviço e retorna seus dados

23. DELETE ```/service/{id}```
    - Deleta um serviço e retorna 204 em caso de sucesso

24. GET ```/schedules/client/{id}```
    - Retorna os dados de um agendamento

25. GET ```/schedule/{id}```
    - Retorna os dados de um agendamento

26. GET ```/company/{id}/schedules```
    - Retorna todos os agendamentos de uma empresa

27. POST ```/schedule```
    - Adiciona um agendamento e retorna seus dados

28. PUT ```/schedule/{id}```
    - Atualiza um agendamento e retorna seus dados

29. DELETE ```/schedule/{id}```
    - Deleta uma nota e retorna 204 em caso de sucesso

30. GET ```/schedule/{id}/annotation```
    - Retorna todas as notas de um agendamento

31. GET ```/annotation/{id}```
    - Retorna os dados de uma nota

32. POST ```/annotation```
    - Adiciona uma nota e retorna seus dados

33. PUT ```/annotation/{id}```
    - Atualiza uma nota e retorna seus dados

34. DELETE ```/annotation/{id}```
    - Deleta uma nota e retorna 204 em caso de sucesso

35. POST ```/service-request```
    - Adiciona uma requisição de serviço e retorna seus dados

36. PUT ```/service-request/{id}```
    - Atualiza uma requisição de serviço e retorna seus dados

37. PATCH ```/service-request/{id}/services```
    - Atualiza uma lista com os serviços de uma requisição de serviço e retorna a requisição completa

38. GET ```/requests/company/{id}```
    - Retorna as requisições de serviço de uma empresa

39. GET ```/requests/user/{id}```
    - Retorna as requisições de serviço de um usuário

40. POST ```/invite```
    - Adiciona um convite para empresa e retorna seus dados

41. PATCH ```/invite/{id}```
    - Atualiza o status de um convite para empresa

42. GET ```/invites/user/{id}```
    - Retorna os convites para empresa de um usuário

43. GET ```/invites/company/{id}```
    - Retorna os convites para empresa de uma empresa