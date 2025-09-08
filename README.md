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
A refatoração da arquitetura se baseou na implementação de quatro padrões de design essenciais para microsserviços: **API Gateway**, **Circuit Breaker**, **Bulkhead** e **Inversão de Controle (IoC)** com Injeção de Dependência.

1. **API Gateway**
O padrão API Gateway foi aplicado com a criação de um novo serviço, o api-gateway-servico, que atua como o único ponto de entrada para todas as requisições externas ao sistema. Na versão inicial, os clientes acessavam cada serviço (usuário, pedido, pagamento) diretamente, o que aumentava o acoplamento e a complexidade do cliente.

Com o API Gateway, o cliente agora se comunica apenas com o localhost:8080. O Gateway é responsável por:

- **Roteamento Dinâmico:** Ele inspeciona o caminho da requisição (`/usuarios`, `/pedidos`, `/pagamentos`) e a roteia para o microsserviço interno correspondente (`localhost:8081`, `localhost:8082`, `localhost:8083`). Isso elimina a necessidade de o cliente conhecer a topologia interna do sistema.
- **Centralização de Lógica:** O Gateway pode ser expandido para lidar com autenticação, segurança (como validação de tokens JWT) e limitação de taxa de requisições (Rate Limiting), centralizando essa lógica e evitando a duplicação em cada microsserviço.

A implementação utilizou o **Spring Cloud Gateway**, que oferece uma solução de alto desempenho e facilmente configurável para este padrão, tanto de forma declarativa (via `application.properties`) quanto programática (via `RouteLocatorBuilder`), como demonstrado no código.

2. **Circuit Breaker**

O padrão Circuit Breaker (Disjuntor) foi implementado no `pedido-servico` para prevenir falhas em cascata. Na arquitetura inicial, uma falha no `pagamento-servico` (por exemplo, por sobrecarga ou indisponibilidade) causaria um `timeout` ou erro, que se propagaria de forma síncrona para o `pedido-servico`, e por fim para o cliente, degradando a experiência do usuário e podendo até derrubar todo o sistema.

Para resolver isso, usamos a biblioteca **Resilience4j**. A anotação `@CircuitBreaker` foi adicionada aos métodos de comunicação com outros serviços (`processarPagamento` e `validarUsuario` no `PedidoService`).

- **Mecanismo:** O Circuit Breaker monitora o número de falhas consecutivas.
  1. **Estado Fechado (Closed):** O fluxo de requisições é normal. Se o número de falhas ultrapassa um limite configurado, o disjuntor abre.

  2. **Estado Aberto (Open):** O disjuntor interrompe as requisições para o serviço defeituoso por um tempo pré-determinado. Qualquer nova chamada é imediatamente desviada para um método de `fallback`, que retorna uma resposta de erro rápida, sem tentar acessar o serviço problemático. Isso dá tempo para o serviço se recuperar.

  3. **Estado Meio Aberto (Half-Open):** Após o tempo de espera, o disjuntor permite que um pequeno número de requisições de teste passe. Se elas forem bem-sucedidas, ele fecha novamente, restaurando o fluxo normal. Se falharem, ele volta para o estado aberto.

O `fallbackMethod` associado ao `@CircuitBreaker` garante que a aplicação continue funcionando mesmo que um serviço dependente esteja com problemas, melhorando significativamente a resiliência do sistema.

Com certeza. A seguir, está a explicação técnica detalhada sobre a aplicação dos padrões de arquitetura na sua refatoração, conforme solicitado na Etapa 4 do seu projeto.

Explicação Técnica da Aplicação dos Padrões
A refatoração da arquitetura inicial para um sistema mais robusto e resiliente se baseou na implementação de quatro padrões de design essenciais para microsserviços: API Gateway, Circuit Breaker, Bulkhead e Inversão de Controle (IoC) com Injeção de Dependência.

1. API Gateway
O padrão API Gateway foi aplicado com a criação de um novo serviço, o api-gateway-servico, que atua como o único ponto de entrada para todas as requisições externas ao sistema. Na versão inicial, os clientes acessavam cada serviço (usuário, pedido, pagamento) diretamente, o que aumentava o acoplamento e a complexidade do cliente.

Com o API Gateway, o cliente agora se comunica apenas com o localhost:8080. O Gateway é responsável por:

Roteamento Dinâmico: Ele inspeciona o caminho da requisição (/usuarios, /pedidos, /pagamentos) e a roteia para o microsserviço interno correspondente (localhost:8081, localhost:8082, localhost:8083). Isso elimina a necessidade de o cliente conhecer a topologia interna do sistema.

Centralização de Lógica: O Gateway pode ser expandido para lidar com autenticação, segurança (como validação de tokens JWT) e limitação de taxa de requisições (Rate Limiting), centralizando essa lógica e evitando a duplicação em cada microsserviço.

A implementação utilizou o Spring Cloud Gateway, que oferece uma solução de alto desempenho e facilmente configurável para este padrão, tanto de forma declarativa (via application.properties) quanto programática (via RouteLocatorBuilder), como demonstrado no código.

2. Circuit Breaker
O padrão Circuit Breaker (Disjuntor) foi implementado no pedido-servico para prevenir falhas em cascata. Na arquitetura inicial, uma falha no pagamento-servico (por exemplo, por sobrecarga ou indisponibilidade) causaria um timeout ou erro, que se propagaria de forma síncrona para o pedido-servico, e por fim para o cliente, degradando a experiência do usuário e podendo até derrubar todo o sistema.

Para resolver isso, usamos a biblioteca Resilience4j. A anotação @CircuitBreaker foi adicionada aos métodos de comunicação com outros serviços (processarPagamento e validarUsuario no PedidoService).

Mecanismo: O Circuit Breaker monitora o número de falhas consecutivas.

Estado Fechado (Closed): O fluxo de requisições é normal. Se o número de falhas ultrapassa um limite configurado, o disjuntor abre.

Estado Aberto (Open): O disjuntor interrompe as requisições para o serviço defeituoso por um tempo pré-determinado. Qualquer nova chamada é imediatamente desviada para um método de fallback, que retorna uma resposta de erro rápida, sem tentar acessar o serviço problemático. Isso dá tempo para o serviço se recuperar.

Estado Meio Aberto (Half-Open): Após o tempo de espera, o disjuntor permite que um pequeno número de requisições de teste passe. Se elas forem bem-sucedidas, ele fecha novamente, restaurando o fluxo normal. Se falharem, ele volta para o estado aberto.

O fallbackMethod associado ao @CircuitBreaker garante que a aplicação continue funcionando mesmo que um serviço dependente esteja com problemas, melhorando significativamente a resiliência do sistema.

3. **Bulkhead**

Com certeza. A seguir, está a explicação técnica detalhada sobre a aplicação dos padrões de arquitetura na sua refatoração, conforme solicitado na Etapa 4 do seu projeto.

Explicação Técnica da Aplicação dos Padrões
A refatoração da arquitetura inicial para um sistema mais robusto e resiliente se baseou na implementação de quatro padrões de design essenciais para microsserviços: API Gateway, Circuit Breaker, Bulkhead e Inversão de Controle (IoC) com Injeção de Dependência.

1. API Gateway
O padrão API Gateway foi aplicado com a criação de um novo serviço, o api-gateway-servico, que atua como o único ponto de entrada para todas as requisições externas ao sistema. Na versão inicial, os clientes acessavam cada serviço (usuário, pedido, pagamento) diretamente, o que aumentava o acoplamento e a complexidade do cliente.

Com o API Gateway, o cliente agora se comunica apenas com o localhost:8080. O Gateway é responsável por:

Roteamento Dinâmico: Ele inspeciona o caminho da requisição (/usuarios, /pedidos, /pagamentos) e a roteia para o microsserviço interno correspondente (localhost:8081, localhost:8082, localhost:8083). Isso elimina a necessidade de o cliente conhecer a topologia interna do sistema.

Centralização de Lógica: O Gateway pode ser expandido para lidar com autenticação, segurança (como validação de tokens JWT) e limitação de taxa de requisições (Rate Limiting), centralizando essa lógica e evitando a duplicação em cada microsserviço.

A implementação utilizou o Spring Cloud Gateway, que oferece uma solução de alto desempenho e facilmente configurável para este padrão, tanto de forma declarativa (via application.properties) quanto programática (via RouteLocatorBuilder), como demonstrado no código.

2. Circuit Breaker
O padrão Circuit Breaker (Disjuntor) foi implementado no pedido-servico para prevenir falhas em cascata. Na arquitetura inicial, uma falha no pagamento-servico (por exemplo, por sobrecarga ou indisponibilidade) causaria um timeout ou erro, que se propagaria de forma síncrona para o pedido-servico, e por fim para o cliente, degradando a experiência do usuário e podendo até derrubar todo o sistema.

Para resolver isso, usamos a biblioteca Resilience4j. A anotação @CircuitBreaker foi adicionada aos métodos de comunicação com outros serviços (processarPagamento e validarUsuario no PedidoService).

Mecanismo: O Circuit Breaker monitora o número de falhas consecutivas.

Estado Fechado (Closed): O fluxo de requisições é normal. Se o número de falhas ultrapassa um limite configurado, o disjuntor abre.

Estado Aberto (Open): O disjuntor interrompe as requisições para o serviço defeituoso por um tempo pré-determinado. Qualquer nova chamada é imediatamente desviada para um método de fallback, que retorna uma resposta de erro rápida, sem tentar acessar o serviço problemático. Isso dá tempo para o serviço se recuperar.

Estado Meio Aberto (Half-Open): Após o tempo de espera, o disjuntor permite que um pequeno número de requisições de teste passe. Se elas forem bem-sucedidas, ele fecha novamente, restaurando o fluxo normal. Se falharem, ele volta para o estado aberto.

O fallbackMethod associado ao @CircuitBreaker garante que a aplicação continue funcionando mesmo que um serviço dependente esteja com problemas, melhorando significativamente a resiliência do sistema.

3. Bulkhead
O padrão **Bulkhead** (Anteparo) foi implementado em conjunto com o **Circuit Breaker** para isolar recursos críticos e evitar que um serviço lento ou sobrecarregado consuma todos os recursos da aplicação, causando uma falha global. Na arquitetura inicial, uma sobrecarga de threads no `pagamento-servico` poderia esgotar o pool de threads do `pedido-servico`, paralisando-o completamente.

Com a anotação `@Bulkhead` do **Resilience4j**, criamos pools de threads separados para cada tipo de chamada.

- **Mecanismo:** O `Bulkhead` limita o número de chamadas concorrentes para um serviço específico, como o `pagamento-servico`. Se o número de requisições ativas para o `pagamento-servico` atingir o limite do seu "anteparo" (`bulkhead`), as novas requisições são colocadas em uma fila ou rejeitadas imediatamente.

- **Isolamento de Falhas:** Isso garante que, mesmo que o `pagamento-servico` esteja lento ou sobrecarregado, ele não "afogue" o `pedido-servico` consumindo todos os seus recursos de thread. Os recursos de thread para o `usuario-servico` e outras operações continuam disponíveis, garantindo que o restante do sistema continue funcional.

O `Bulkhead` e o `Circuit Breaker` trabalham juntos para criar uma defesa em profundidade, onde o `Bulkhead` previne a sobrecarga e o `Circuit Breaker` reage a falhas para evitar a propagação.

4. **Injeção de Dependência (IoC)**

A Inversão de Controle (IoC), concretizada pela Injeção de Dependência, foi aplicada para desacoplar a comunicação entre os serviços. Na versão inicial, o `PedidoController` criava instâncias de `RestTemplate` diretamente (`new RestTemplate()`), gerando um acoplamento rígido com a implementação do cliente HTTP e dificultando testes unitários e a substituição por outras bibliotecas.

Na refatoração, fizemos as seguintes mudanças:

- **Centralização da Instância:** Um `WebClient.Builder` foi criado como um `@Bean` no `AppConfig`, permitindo que o Spring gerencie a criação e o ciclo de vida do cliente HTTP.

- **Injeção em Serviços:** As dependências de comunicação com outros serviços (`UsuarioService` e `PagamentoService`) foram extraídas do `PedidoController` para classes de serviço dedicadas. O `WebClient` é então injetado nessas classes de serviço via `@Autowired`.

Essa abordagem trouxe benefícios cruciares:

- **Baixo Acoplamento:** O `PedidoController` agora depende de abstrações (`UsuarioService`, `PagamentoService`) em vez de implementações concretas de comunicação. Isso facilita a mudança da lógica de comunicação sem alterar o controlador.

- **Testabilidade:** Os serviços podem ser facilmente substituídos por "mocks" em testes unitários, isolando a lógica do controlador e permitindo testar seu comportamento sem fazer chamadas de rede reais.

A combinação desses quatro padrões de arquitetura resultou em um sistema mais **resiliente**, **escalável** e **manutenção**, demonstrando como os princípios de design podem ser aplicados na prática para resolver problemas comuns em arquiteturas distribuídas.

## Documentação
https://docs.google.com/document/d/1nUA8HdSj414nH4rgpXNYhCkczUzeVNjgaVSPjjOYnK8/edit?usp=sharing

