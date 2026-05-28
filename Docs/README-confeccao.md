# Software-JJConfecc

API REST para gerenciamento de uma confecção — controle de produção, funcionários, pedidos, clientes e financeiro.

Construído do zero com foco em modelagem de banco de dados sólida, estratégia de índices e arquitetura limpa.

---

## Stack

- Java 21 (OpenJDK 25) ✅
- Spring Boot 4.x ✅
- Spring Data JPA + Hibernate ✅
- Spring Security + JWT
- MySQL 8.0 (Docker) ✅
- JUnit 5 + Mockito
- Docker & Docker Compose ✅
- AWS

---

## Domínio

Uma confecção recebe pedidos de clientes empresariais (CNPJ obrigatório). O cliente envia o tecido cortado; a confecção é responsável pela costura e entrega.

Cada pedido tem prazo calculado pela capacidade produtiva diária:
O dono abre cada pedido diariamente, aloca os funcionários disponíveis e registra a produção real ao fim do dia. O sistema acompanha a produção esperada vs. realizada ao longo do tempo.

O pedido só é concluído quando o total de peças entregues bater com a quantidade da nota fiscal original.

---

## Entidades

| Entidade | Responsabilidade |
|----------|-----------------|
| `client` | Clientes empresariais com CNPJ |
| `product` | Tipos de peça com preço unitário e tempo de produção (minutos) |
| `employee` | Funcionários com PIS, RG, cargo e status |
| `orders` | Contrato com o cliente — produto, quantidade, prazo, status |
| `daily_production` | Registro diário — funcionários alocados, produção esperada e real |
| `daily_production_employees` | Tabela intermediária N:N entre daily_production e employees |
| `financial` | Registro de entrega — peças entregues e faturamento |
| `machine` | Cadastro de máquinas |
| `machine_maintenance` | Histórico de manutenções por máquina |

---

## Modelagem e Infraestrutura do Banco

### Ambiente & Docker ✅
- [x] Configuração estrutural do arquivo `docker-compose.yml` (Portas `3306:3306` mapeadas externamente)
- [x] Isolamento de dados com persistência via Docker Volumes
- [x] Gerenciamento nativo de variáveis de ambiente no IntelliJ IDEA (`DB_NAME`, `DB_PORT`, `DB_USER`, `DB_PASSWORD`)

### Entidades & Mapeamento JPA/Hibernate ✅
- [x] `client` — id, name, cnpj VARCHAR(18), address, cellphone, email
- [x] `product` — id, name, ncm VARCHAR(8), price DECIMAL(10,2), production_time INT
- [x] `employee` — id, name, address, pis VARCHAR(15), rg VARCHAR(20), position VARCHAR(45), status ENUM('ATIVO','FERIAS','AFASTADO','DESLIGADO'), birth_date DATE
- [x] `orders` — id, client_id FK, product_id FK, quantity INT, start_date DATE, expected_delivery_date DATE, status ENUM('PENDING','IN_PRODUCTION','COMPLETED')
- [x] `daily_production` — id, orders_id FK, current_date DATE, expected_production INT, actual_production INT
- [x] `daily_production_employees` — Tabela intermediária implícita gerenciada via `@ManyToMany`
- [x] `financial` — id, daily_production_id FK, quantity_delivered INT, total_billed DECIMAL(10,2)
- [x] `machine` — id, name VARCHAR(45), serial_number VARCHAR(45)
- [x] `machine_maintenance` — id, machine_id FK, maintenance_date DATE, mechanic_name VARCHAR(45)

### Relacionamentos Mapeados no Java ✅
- [x] `client` 1:N `orders`
- [x] `product` 1:N `orders`
- [x] `orders` 1:N `daily_production`
- [x] `daily_production` N:N `employees` via `@ManyToMany` e `@JoinTable` (sem necessidade de entidade física extra para preservar o total gerado puramente no dia)
- [x] `daily_production` 1:N `financial`
- [x] `machine` 1:N `machine_maintenance`

### Diagrama ER ✅
- Arquivo `docs/modelagem.mwb`

### Scripts SQL ✅
- [x] `docs/schema.sql` — script de criação do banco
- [x] Criação automática via DDL Auto do Hibernate conectada ao Docker em tempo de boot

### Índices
- [ ] Definidos e documentados

---

## Progresso — API

### Segurança
- [ ] Autenticação JWT
- [ ] Senhas com BCrypt
- [ ] Endpoints protegidos

### Endpoints
- [ ] Clientes
- [ ] Produtos
- [ ] Funcionários
- [ ] Pedidos
- [ ] Produção diária
- [ ] Financeiro
- [ ] Máquinas

### Testes
- [ ] Testes unitários
- [ ] Testes de integração (queries JPQL)

---

## Decisões de Design

- Valores monetários usam `DECIMAL(10,2)` — nunca `DOUBLE` ou `FLOAT`
- Documentos (CNPJ, PIS, RG) armazenados como `VARCHAR` — preserva formatação e zeros à esquerda
- `status` do funcionário como `ENUM` — banco rejeita valores fora da lista, sem necessidade de validação extra
- `status` do pedido como `ENUM('PENDING','IN_PRODUCTION','COMPLETED')` — estados fixos e controlados
- `daily_production` N:N com `employees` via tabela intermediária automática — guarda a data e o total de produção geral na tabela principal, enquanto a relação associa os operadores daquele dia.
- `financial` separado de `daily_production` — produzir e entregar são eventos distintos; o motorista pode buscar mais de uma vez no mesmo dia
- Pedido só fecha quando o total entregue bater com a quantidade da nota fiscal — integridade financeira por design
- `machine_maintenance` como entidade separada — histórico completo de manutenções, não só a última data
- Preço unitário não é duplicado no `financial` — vem de `product` via JOIN, evitando inconsistência