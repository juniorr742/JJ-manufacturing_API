# JJ Manufacturing API

> Backend de Controlo de Produção, Manufatura e Faturação para a **JJ Confecc**.

Este projeto consiste numa API REST robusta desenvolvida em **Spring Boot 4** para automatizar o fluxo de ponta a ponta de uma confeção têxtil. O sistema faz a ponte desde a entrada das encomendas de clientes, planeamento da produção diária no chão de fábrica, alocação de pessoal e maquinário, até ao cálculo automatizado de faturamento com base na expedição de mercadorias.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem Principal:** Java 25 (OpenJDK 25.0.2)
* **Framework:** Spring Boot 4.0.6
  * Spring Data JPA
  * Spring Security
  * Spring WebMVC
* **Persistência de Dados:** Hibernate ORM 7.2.12.Final
* **Banco de Dados:** MySQL 8.0 (Executado de forma isolada num contentor **Docker**)
* **IDE Recomendada:** IntelliJ IDEA (2025.3.3)

---

## 🗄️ Modelo de Dados (Arquitetura de Tabelas)

Todas as tabelas do ecossistema utilizam chaves primárias baseadas em identificadores únicos universais (`UUID`), mapeados no MySQL como `binary(16)`, garantindo alta segurança e escalabilidade.

* **`client`**: Registo de clientes jurídicos da confeção (CNPJ, Email, Nome, Morada). *CNPJ e Email possuem restrição UNIQUE.*
* **`orders`**: Controlo de encomendas, quantidades solicitadas e estado produtivo (`PRODUZINDO`, `PARADO`, `FINALIZADO`).
* **`product`**: Catálogo de peças de roupa vinculadas às encomendas, contendo NCM, preço unitário e tempo de produção estimado.
* **`employees`**: Registo de costureiros, operadores e mecânicos com controlo de estado funcional (`ATIVO`, `AFASTADO`, `DESLIGADO`), RG e PIS únicos.
* **`machine`**: Maquinário industrial identificado pelo número de série único.
* **`machine_maintenance`**: Histórico de manutenções preventivas e corretivas executadas por mecânicos específicos nas máquinas.
* **`daily_production`**: Registo diário da produção. Compara a produção esperada contra a produção real alcançada no dia.
* **`financial`**: Registo financeiro de expedição. Armazena a quantidade de peças entregues e calcula o faturamento acumulado da entrega.

> 💡 **Nota de Design de Arquitetura:** O faturamento mensal e anual do sistema é calculado **sob demanda** através de funções agregadoras (`SUM`) indexadas diretamente na tabela `financial`, eliminando tabelas de acumulados redundantes e prevenindo dados dessincronizados em caso de edições ou eliminações.

---

## 🐳 Como Gerir o Banco de Dados (Docker)

Como o MySQL está isolado dentro de um contentor Docker (`jj-manufactoring-mysql`), pode utilizar os seguintes comandos no terminal do IntelliJ ou Git Bash para o controlar:

### 1. Iniciar o Banco de Dados:
```bash
docker start jj-manufactoring-mysql
