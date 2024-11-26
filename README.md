Aqui está o arquivo **README.md**:

# 🌍 API de Agência de Viagens

Bem-vindo à API de Agência de Viagens! Esta API foi desenvolvida para auxiliar agências de turismo e aplicativos de terceiros no gerenciamento de destinos e avaliações. 🚀

## 📝 Descrição do Projeto

A API permite o gerenciamento de destinos turísticos, permitindo que os usuários e parceiros possam:
- Cadastrar destinos de viagem.
- Listar todos os destinos disponíveis.
- Pesquisar destinos por nome ou localização.
- Visualizar informações detalhadas de um destino.
- Avaliar destinos com notas de 1 a 10.
- Excluir destinos do sistema.

A API segue os padrões RESTful, garantindo integração fácil e eficiente com outros sistemas.

---

## 🔗 Endpoints Disponíveis

### **Cadastrar Destino**
- **URL:** `/api/destinos`
- **Método:** `POST`
- **Descrição:** Cadastra um novo destino de viagem.
- **Corpo da Requisição:**
  ```json
  {
    "nome": "Paris",
    "localizacao": "França",
    "descricao": "Cidade das Luzes"
  }
  ```

### **Listar Destinos**
- **URL:** `/api/destinos`
- **Método:** `GET`
- **Descrição:** Retorna a lista de todos os destinos cadastrados.

### **Pesquisar Destinos**
- **URL:** `/api/destinos/pesquisar`
- **Método:** `GET`
- **Descrição:** Pesquisa destinos por nome ou localização.
- **Parâmetros Opcionais:**
  - `nome`: Nome do destino.
  - `localizacao`: Localização do destino.

### **Visualizar Detalhes de um Destino**
- **URL:** `/api/destinos/{id}`
- **Método:** `GET`
- **Descrição:** Retorna informações detalhadas de um destino específico.

### **Avaliar Destino**
- **URL:** `/api/destinos/{id}/avaliar`
- **Método:** `PATCH`
- **Descrição:** Avalia um destino com uma nota de 1 a 10.
- **Parâmetro na URL:**
  - `nota`: Nota a ser atribuída ao destino.

### **Excluir Destino**
- **URL:** `/api/destinos/{id}`
- **Método:** `DELETE`
- **Descrição:** Exclui um destino do sistema.

---

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o **Java 11** ou superior e o **Maven** instalados.
2. Clone este repositório:
   ```bash
   git clone https://github.com/JeanCarlosHP/travel-agency-api-senai.git
   cd travel-agency-api-senai
   ```
3. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
4. A API estará disponível em: `http://localhost:8080`.

---

## 🛠️ Comandos cURL para Testar os Endpoints

### **Cadastrar Destino**
```bash
curl -X POST http://localhost:8080/api/destinos \
-H "Content-Type: application/json" \
-d '{
  "nome": "Paris",
  "localizacao": "França",
  "descricao": "Cidade das Luzes"
}'
```

### **Listar Destinos**
```bash
curl -X GET http://localhost:8080/api/destinos
```

### **Pesquisar Destinos**
Pesquisar por nome:
```bash
curl -X GET "http://localhost:8080/api/destinos/pesquisar?nome=Paris"
```

Pesquisar por localização:
```bash
curl -X GET "http://localhost:8080/api/destinos/pesquisar?localizacao=Fran%C3%A7a"
```

### **Visualizar Detalhes de um Destino**
```bash
curl -X GET http://localhost:8080/api/destinos/1
```

### **Avaliar Destino**
```bash
curl -X PATCH "http://localhost:8080/api/destinos/1/avaliar?nota=9"
```

### **Excluir Destino**
```bash
curl -X DELETE http://localhost:8080/api/destinos/1
```

---