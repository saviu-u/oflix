# OFLIX

### Idéia da APS

O tema escolhido para a APS foi *"Locadora de filmes"*, e o projeto é feito baseado em Java WEB utilizando o modelo de **MVC**
O projeto segue as seguintes funções:
- Cadastrar usuários
- Cadastrar clientes
- Cadastrar categorias de DVD's
- Controle de locação e filmes disponíveis

### Composição do projeto

As rotas funcionam da seguinte maneira;

| Rotas | Verbo | Modelo |
| ---: | :---: | :--- |
| **Index**   | `GET` |*`/modelo`*
| **New**     | `GET` |*`/modelo/new`*
| **Create**  | `POST` |*`/modelo/new`*
| **Edit**       | `GET` |*`/modelo/id/edit`*
| **Update** | `POST` |*`/modelo/id/edit`*
| **Delete**   | `GET` |*`/modelo/id/delete`*

O projeto têm as seguintes rotas:

| Rotas               | Home    | Usuários | Clientes | Categorias | Filmes   | Locação |
| :------------------ | :-----: | :-----:  | :------: | :--------: | :-----: | :-----: |
| **Index (GET)**     |✅|✅ |✅ |✅   |✅|✅|
| **New (GET)**       |❌|✅ |✅ |✅   |✅|✅|
| **Create (POST)**   |❌|✅ |✅ |✅   |✅|✅|
| **Edit (GET)**      |❌|✅ |✅ |✅   |✅|✅|
| **Update (PUT)**    |❌|✅ |✅ |✅   |✅|✅|
| **Delete(DELETE)**  |❌|✅ |✅ |❌   |❌|❌|
