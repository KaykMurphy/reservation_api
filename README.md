# 🏢 Room Reservation API

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

> Sistema robusto de gerenciamento de reservas de salas corporativas, desenvolvido com Spring Boot 4 e arquitetura orientada a domínio.

## 📋 Sobre o Projeto

API RESTful completa para gestão de reservas de salas, projetada para ambientes corporativos que necessitam de controle eficiente de espaços compartilhados. A solução oferece validações de negócio rigorosas, controle de conflitos de horários e gestão completa do ciclo de vida das reservas.

### 🎯 Destaques Técnicos

- **Arquitetura em Camadas**: Separação clara entre domínio, serviços, controladores e DTOs
- **Validações de Negócio**: Regras customizadas para prevenção de conflitos e garantia de integridade
- **Design Patterns**: Repository, Service Layer, DTO Pattern e Mapper Pattern
- **Transações ACID**: Gerenciamento transacional com Spring Transaction Management
- **API First**: Documentação automática com OpenAPI 3.0 (Swagger)

## 🚀 Tecnologias

### Core
- **Java 21** - Recursos modernos da linguagem (Records, Pattern Matching)
- **Spring Boot 4.0.1** - Framework base com auto-configuração
- **Spring Data JPA** - Abstração de persistência e gestão de entidades
- **Spring Validation** - Validação declarativa de dados
- **PostgreSQL** - Banco de dados relacional em produção
- **H2 Database** - Banco em memória para testes

### Ferramentas & Bibliotecas
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências
- **SpringDoc OpenAPI** - Documentação interativa da API

## 📐 Arquitetura

```
room-reservation-api/
├── domain/              # Entidades de domínio e enums
├── dto/                 # Data Transfer Objects
│   ├── request/        # DTOs de entrada
│   └── response/       # DTOs de saída
├── repository/         # Camada de acesso a dados
├── service/            # Lógica de negócio
│   └── validation/    # Validadores customizados
├── mapper/             # Conversores Entity ↔ DTO
├── controller/         # Endpoints REST
└── exception/          # Exceções customizadas
```

### Modelo de Domínio

**User** → possui múltiplas **Reservations**  
**Room** → pode ter múltiplas **Reservations**  
**Reservation** → conecta User e Room com regras de negócio

## 🔧 Funcionalidades

### Gestão de Usuários
- ✅ Cadastro de usuários com validação de e-mail
- ✅ Listagem de todos os usuários ativos
- ✅ Identificação única via UUID

### Gestão de Salas
- ✅ Criação de salas com tipos diversos (Reunião, Quadra, Auditório, Coworking)
- ✅ Configuração de capacidade e preço por hora
- ✅ Ativação/desativação (soft delete)
- ✅ Listagem de salas ativas

### Sistema de Reservas
- ✅ Criação de reservas com validação de disponibilidade
- ✅ Cálculo automático de preço total
- ✅ Fluxo de estados: PENDING → CONFIRMED → COMPLETED/CANCELLED
- ✅ Confirmação com revalidação de conflitos
- ✅ Cancelamento com regra de 24h de antecedência
- ✅ Consulta de reservas por usuário
- ✅ Prevenção de overlapping de horários
- ✅ Validação de datas futuras

### Regras de Negócio Implementadas

```java
// Validação de conflito de horários
boolean hasConflict = start.isBefore(existing.endTime) && 
                      end.isAfter(existing.startTime);

// Cancelamento apenas com 24h de antecedência
if (reservation.startTime.isBefore(now.plusHours(24))) {
    throw new BusinessException("Mínimo 24h de antecedência");
}

// Apenas salas ativas podem ser reservadas
if (!room.active) {
    throw new BusinessException("Sala inativa");
}
```

## 🏗️ Como Executar

### Pré-requisitos
```bash
Java 21+
PostgreSQL 12+
Maven 3.9+
```

### Configuração do Banco de Dados

```sql
CREATE DATABASE room_reservation_db;
```

Edite `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/room_reservation_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

### Executando a Aplicação

```bash
# Clone o repositório
git clone https://github.com/KaykMurphy/room-reservation-api.git

# Entre no diretório
cd room-reservation-api

# Execute com Maven Wrapper
./mvnw spring-boot:run

# Ou com Maven instalado
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## 📚 Documentação da API

Acesse a documentação interativa Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

### Exemplos de Endpoints

#### Criar Sala
```http
POST /api/rooms/
Content-Type: application/json

{
  "name": "Sala de Reunião A1",
  "capacity": 10,
  "pricePerHour": 150.00,
  "type": "MEETING_ROOM"
}
```

#### Criar Reserva
```http
POST /api/reservations/
Content-Type: application/json

{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "roomId": 1,
  "startDateTime": "2026-01-15T14:00:00",
  "durationInHours": 2
}
```

#### Confirmar Reserva
```http
PATCH /api/reservations/1/confirm
```

## 🧪 Testes

```bash
# Executar testes unitários (em desenvolvimento)
./mvnw test

# Executar com cobertura (em desenvolvimento)
./mvnw test jacoco:report
```

## 🛣️ Roadmap

### Em Desenvolvimento
- [ ] Global Exception Handler com respostas padronizadas
- [ ] Testes unitários completos (Service Layer)
- [ ] Testes de integração (Controller Layer)
- [ ] Interface web para gestão de reservas

### Futuras Melhorias
- [ ] Autenticação e autorização (Spring Security + JWT)
- [ ] Sistema de notificações (e-mail/push)
- [ ] Relatórios de ocupação e faturamento
- [ ] Cache com Redis
- [ ] Containerização com Docker
- [ ] CI/CD Pipeline
- [ ] Monitoramento com Actuator + Prometheus

## 👨‍💻 Autor

**Kayk Edmar**

Desenvolvedor Backend especializado em Java e Spring Boot, com foco em arquiteturas escaláveis e código limpo.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue)](https://www.linkedin.com/in/kayk-edmar/)
[![GitHub](https://img.shields.io/badge/GitHub-Follow-black)](https://github.com/KaykMurphy)

---

<div align="center">
  
**Se este projeto foi útil para você, considere deixar uma ⭐**

Desenvolvido com ☕ e dedicação

</div>
