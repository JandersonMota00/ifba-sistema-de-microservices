# Trabalho de Sistema de Pagamentos
- **Instituição:** Instituto Federal da Bahia (IFBA)
- **Curso:** Análise e Desenvolvimento de Sistemas (ADS)
- **Disciplina:** Padrões de Projetos
- **Projeto:** Tema de livre escolha da equipe
- **Professor:** Felipe de Souza Silva
- **Semestre:** 5
- **Ano:** 2025.1

# Descrição do projeto: Sistema de Pagamentos
Deverá modelar um sistema de microservices (tema livre, ex: e-commerce, rede social, sistema de pagamentos, IoT distribuído) que inicialmente apresente falhas arquiteturais comuns:
- Chamadas diretas entre serviços sem controle de falha.
- Alto acoplamento entre serviços (dependências rígidas).
- Violações explícitas de princípios SOLID (ex: God Service).

Em seguida, deverão refatorar e aplicar padrões:
1. API Gateway – concentrar acesso externo e controlar o roteamento.
2. Circuit Breaker – impedir falhas em cascata entre serviços.
3. Bulkhead – isolar recursos críticos e evitar sobrecarga global.
4. Injeção de Dependência (IoC) – desacoplar módulos de comunicação.

[Lista A8 - Simulação de Microservices Resilientes com Aplicação de Padrões Arquiteturais e Diagn.pdf](https://github.com/user-attachments/files/22138076/A8.-.SAJ-ADS08.-.Simulacao.de.Microservices.Resilientes.com.Aplicacao.de.Padroes.Arquiteturais.e.Diagn.pdf)

## Integrantes do Projeto

<table>
  <tr>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/80362674?v=4" width="100px;" alt="Foto do Integrante Janderson"/><br />
      <sub><b><a href="https://github.com/JandersonMota">Janderson Mota</a></b></sub>
    </td>
    <td align="center">
      <img src="https://avatars.githubusercontent.com/u/110790276?v=4" width="100px;" alt="Foto da Integrante Sarah"/><br />
      <sub><b><a href="https://github.com/SarahPithon/">Sarah Pithon</a></b></sub>
    </td>
  </tr>
</table>

## Tecnologias
- **Linguagem:** Java 21

## Estrutura do Projeto
A estrutura será a seguinte, com cada microserviço sendo um módulo Maven independente.
```
ifba-sistema-de-microservices/
├── versao_inicial/
├── versao_refatorada/
|
└── README.md
```

Estrutura da versão inicial
```
versao_inicial/
├── usuario-servico/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── usuarioservico/
│   │   │   │           └── usuario/
│   │   │   │               ├── controller/
│   │   │   │               │   └── UsuarioController.java
│   │   │   │               │
│   │   │   │               ├── model/
│   │   │   │               │   └── Usuario.java
│   │   │   │               │
│   │   │   │               ├── repository/
│   │   │   │               │   └── UsuarioRepository.java
│   │   │   │               │
│   │   │   │               └── UsuarioApplication.java
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       ├── templates/
│   │   │       └── application.properties
│   │   │
│   │   └── tets/
│   └── pom.xml
|
├── pedido-servico/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── pedidoservico/
│   │   │   │           └── pedido/
│   │   │   │               ├── controller/
│   │   │   │               │   └── PedidoController.java
│   │   │   │               │
│   │   │   │               ├── model/
│   │   │   │               │   └── Pedido.java
│   │   │   │               │
│   │   │   │               ├── repository/
│   │   │   │               │   └── PedidoRepository.java
│   │   │   │               │
│   │   │   │               └── PedidoApplication.java
│   │   │   │
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       ├── templates/
│   │   │       └── application.properties
│   │   │
│   │   └── tets/
│   └── pom.xml
|
└── pagamento-servico/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── pagamentoservico/
    │   │   │           └── pagamento/
    │   │   │               ├── controller/
    │   │   │               │   └── PagamentoController.java
    │   │   │               │
    │   │   │               ├── model/
    │   │   │               │   └── Pagamento.java
    │   │   │               │
    │   │   │               ├── repository/
    │   │   │               │   └── PagamentoRepository.java
    │   │   │               │
    │   │   │               └── PagamentoApplication.java
    │   │   │
    │   │   └── resources/
    │   │       ├── static/
    │   │       ├── templates/
    │   │       └── application.properties
    │   │
    │   └── tets/
    └── pom.xml
```

Estrutura da versão refatorada
```
versao_refatorada/
├── api-gateway-servico/
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       |   └── com/
│   │       |       └── apigatewayservico/
│   │       |           └── apigateway/
│   │       |               └── ApigatewayApplication.java
│   |       |
│   |       └── resources/
│   |           └── application.properties
│   └── pom.xml
│
├── pagamento-servico/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
│   │               └── pagamentoservico/
│   │                   └── pagamento/
|   |                       ├── controller/
|   |                       │   └── PagamentoController.java
|   |                       |
│   |                       ├── model/
│   |                       │   └── PagamentoRequisicao.java
|   |                       |
│   |                       └── PagamentoServicoApplication.java
│   └── pom.xml
│
├── pedido-servico/
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── com/
|   |               └── pedidoservico/
|   |                   └── pedido/
│   │                       ├── config/
│   │                       │   └── AppConfig.java
│   │                       │
│   │                       ├── controller/
│   │                       │   └── PedidoController.java
│   │                       │
│   │                       ├── model/
│   │                       │   └── Pedido.java
│   │                       │
│   │                       ├── repository/
│   │                       │   └── PedidoRepository.java
│   │                       │
│   │                       ├── service/
│   │                       │   ├── PagamentoService.java
│   │                       │   └── UsuarioService.java
│   │                       │
│   │                       └── PedidoServicoApplication.java
│   └── pom.xml
│
└── usuario-servico/
    ├── src/
    │   └── main/
    │       └── java/
    │           └── com/
    │               └── usuarioservico/
    │                   └── usuario/
    │                       ├── controller/
    │                       │   └── UsuarioController.java
    │                       |
    │                       ├── model/
    │                       │   └── Usuario.java
    │                       |
    │                       ├── repository/
    │                       │   └── UsuarioRepository.java
    │                       |
    │                       └── UsuarioApplication.java
    └── pom.xml
```

## UML

### Versão Inicial

### Versão Refatorada

## Instruções de Execução

## Documentação
https://docs.google.com/document/d/1nUA8HdSj414nH4rgpXNYhCkczUzeVNjgaVSPjjOYnK8/edit?usp=sharing

