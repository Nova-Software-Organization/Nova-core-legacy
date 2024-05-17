
---

# Nova Core

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)](https://github.com/usuario/repositorio/actions)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

## Sumário

- [📄 Descrição](##descrição)
- [🏛️ Arquitetura](##arquitetura)
- [🛠️ Tecnologias Utilizadas](#tecnologias-utilizadas)
- [⚙️ Configuração do Ambiente](#configuração-do-ambiente)
- [🚀 Execução](#execução)
- [🐳 Ambiente de Desenvolvimento com Docker](#ambiente-de-desenvolvimento-com-docker)
- [🌐 Ambiente de Produção com Docker](##ambiente-de-produção-com-docker)
- [🧪 Testes](#testes)
- [🤝 Contribuições](#contribuições)
- [📏 Padrões de Código](#padrões-de-código)
- [📚 Documentação](##documentação)
- [📜 Licença](##licença)
- [📞 Contato](##contato)

## 📄 Descrição

Este repositório contém os microserviços da nova software, uma plataforma inovadora para Nova software, teve seu início em 2023 com intuito de ser um sistema onde fornece serviços personalizados atendendo as demandas de micro empresas que estão iniciando no mercado de ecommerce ou seja vendas online. 
O sistemas está na sua faze de desenvolvimento é previsto que seu término ocorrerá em 2025, entretanto até o desenvolvimento ser totalmente concluído alguns serviços seriam disponibilizados afim de teste feitos pelos próprios usuário compreendendo as reais necessidades do mesmo.. O sistema é construído para ser escalável, modular e de fácil manutenção, utilizando uma arquitetura baseada em microserviços.

## 🏛️ Arquitetura

A arquitetura do sistema é baseada em microserviços, cada um responsável por uma função específica e comunicando-se entre si através de APIs REST e mensageria. A seguir, uma visão geral dos principais componentes:

- **Gateway de API:** Centraliza e gerencia todas as requisições dos clientes, distribuindo-as para os microserviços apropriados.
- **Serviço de Autenticação:** Gerencia a autenticação e autorização dos usuários, utilizando OAuth 2.0.
- **Serviço de Usuários:** Controla o cadastro, atualização e gerenciamento de perfis de usuários.
- **Serviço de Pagamentos:** Processa pagamentos e transações financeiras de maneira segura e eficiente.
- **Serviço de Relatórios:** Gera relatórios detalhados com base nos dados do sistema, utilizando Python.
- **Serviço de Notificações:** Envia notificações por e-mail e SMS aos usuários, utilizando AWS SNS e SES.

## 🛠️ Tecnologias Utilizadas

- **Linguagens de Programação:**
  - Java (Spring Boot para construção de microserviços robustos e escaláveis)
  - Python (para geração de relatórios e processamento de dados)
- **Banco de Dados:**
  - MySQL (para dados relacionais)
- **Mensageria:**
  - RabbitMQ (mensageria e filas de tarefas)
  - AWS SNS (serviço de notificação)
- **Infraestrutura:**
  - Docker (contenedorização de aplicações)
  - Kubernetes (orquestração de contêineres)
- **Cache e Armazenamento:**
  - Redis (cache de alto desempenho)
- **Monitoramento e Observabilidade:**
  - Prometheus (coleta e monitoramento de métricas)
  - Grafana (visualização de métricas e dashboards)

## ⚙️ Configuração do Ambiente

### Pré-requisitos

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Git](https://git-scm.com/)
- JDK 11+ (para serviços Spring Boot)
- Python 3.8+ (para serviços Python)

### Passo a Passo

1. Clone o repositório:
    ```bash
      git@github.com:Nova-Software-Organization/Nova-core.git
    ```

2. Configure as variáveis de ambiente criando um arquivo `application-dev.properties` na raiz do projeto e defina as variáveis necessárias:
    ```env
    DATABASE_URL=mysql://usuario:senha@localhost:5432/meubanco
    REDIS_URL=redis://localhost:6379/0
    RABBITMQ_URL=amqp://guest:guest@localhost:5672/
    AWS_ACCESS_KEY_ID=sua_chave_de_acesso
    AWS_SECRET_ACCESS_KEY=sua_chave_secreta
    AWS_REGION=sua_regiao
    JWT_SECRET=seu_segredo_jwt
    ```

3. Instale as dependências:

    - Para serviços Java com Spring Boot:
      ```bash
      ./mvnw install
      ```

    - Para serviços Python:
      ```bash
      pip install -r requirements.txt
      ```

4. Inicie os contêineres Docker:
    ```bash
    docker-compose up -d
    ```

## 🚀 Execução

Para iniciar um microserviço específico, siga o exemplo abaixo:

1. Navegue até o diretório do serviço desejado:
    - Para o serviço de autenticação (Spring Boot):
      ```bash
      cd services/auth_service
      ```

    - Para o serviço de relatórios (Python):
      ```bash
      cd services/report_service
      ```

2. Inicie o serviço:
    - Para serviços Java com Spring Boot:
      ```bash
      ./mvnw spring-boot:run
      ```

    - Para serviços Python:
      ```bash
      uvicorn main:app --host 0.0.0.0 --port 8000
      ```

3. Acesse o serviço através da URL:
    ```
    http://localhost:8000
    ```

## 🐳 Ambiente de Desenvolvimento com Docker

### Configuração

1. Certifique-se de que o Docker e o Docker Compose estão instalados em seu sistema.

2. Clone o repositório e navegue até a pasta do projeto, matendo o projeto docker-dev junto com os microserviços:
    ```bash
    git clone git@github.com:Nova-Software-Organization/docker-dev.git
    cd docker-dev
    ```

3. Crie e configure o arquivo `.env` com as variáveis de ambiente necessárias (veja a seção [Configuração do Ambiente](##configuração-do-ambiente)).

4. Suba os contêineres Docker para o ambiente de desenvolvimento:
    ```bash
    docker-compose -f docker-compose.dev.yml up -d
    ```

5. Para visualizar os logs dos contêineres em tempo real:
    ```bash
    docker-compose logs -f
    ```

### Desenvolvimento

Durante o desenvolvimento, você pode acessar os serviços individuais através dos contêineres Docker. Utilize volumes do Docker para sincronizar o código local com os contêineres para um desenvolvimento mais rápido.

Para acessar um contêiner específico e realizar comandos diretamente nele:
```bash
docker-compose exec <nome_do_servico> /bin/sh
```

## 🌐 Ambiente de Produção com Docker | Somente para ADM

### Configuração

1. Certifique-se de que o Docker e o Docker Compose estão instalados no servidor de produção.

2. Clone o repositório no servidor de produção:
    ```bash
    git clone git@github.com:Nova-Software-Organization/docker-dev.git
    cd repositorio
    ```

3. Configure as variáveis de ambiente necessárias em um arquivo `.env.prod`:
    ```env
    DATABASE_URL=postgresql://usuario:senha@db_producao:5432/meubanco
    REDIS_URL=redis://redis_producao:6379/0
    RABBITMQ_URL=amqp://guest:guest@rabbitmq_producao:5672/
    AWS_ACCESS_KEY_ID=sua_chave_de_acesso_producao
    AWS_SECRET_ACCESS_KEY=sua_chave_secreta_producao
    AWS_REGION=sua_regiao_producao
    JWT_SECRET=seu_segredo_jwt_producao
    ```

4. Suba os contêineres Docker para o ambiente de produção:
    ```bash
    docker-compose -f docker-compose.prod.yml up -d
    ```

### Monitoramento e Logs

Para monitorar os serviços e visualizar logs:
```bash
docker-compose -f docker-compose.prod.yml logs -f
```

### Atualizações e Deploy Contínuo

Para aplicar atualizações e realizar deploy contínuo:
1. Puxe as últimas mudanças do repositório:
    ```bash
    git pull origin main
    ```

2. Recrie os contêineres com as novas mudanças:
    ```bash
    docker-compose -f docker-compose.prod.yml up -d --build
    ```

## 🧪 Testes

Execute os testes automatizados utilizando os seguintes comandos:

- Para serviços Java com Spring Boot:
  ```bash
  ./mvnw test
  ```

- Para serviços Python:
  ```bash
  pytest
  ```

## 🤝 Contribuições

Contribuições são bem-vindas! Por favor, siga os passos abaixo para contribuir:

1. Faça um fork do projeto.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`).
4. Push para a branch (`git push origin feature/nova-feature`

## 📜 Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## 📞 Contato
Para dúvidas e suporte, entre em contato com novasoftwareorganization@gmail.com
