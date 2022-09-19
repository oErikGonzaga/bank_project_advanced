***Java Avançado com Spring***

Case - Sistema Bancário

- Configurar Projeto SpringBoot
- Configurar SpringData
- Introdução ao Hibernate (Mapeamento de Banco Java)
- Configurar FlyWay (Gerenciador de Banco)
- Modelar as Entidades Hibernate JPA

- Introdução ao SpringData
- Implementar Cadastro de Conta Bancária
	- Construção da API (REST)
	- Consumir a API do VIACEP para buscar o endereço do Cliente

- Implementar Depósito
- Implementar Saque
	- Introdução sobre Cache
	- Introdução sobre Redis (Banco de Dados em Memória)
	- Implementação de Solução de Limite de Saque por dia - Redis

- Implementar transferência entre contas (Usar limite de saque acima)
- Implementar uma funcionalidade de concessão de empréstimo
	- Introdução sobre processos sincronos e assincronos
	- Introdução sobre Messageria
	- Introdução sobre RabbitMQ

- Implementar uma feature de negativação
	- Vamos criar um Scheduler (agendador) para verificar on inadimplentes
	- Consumir uma api do SERASA para adicionar ou remover os negativados
		-Integração via FEIGN CLIENT (POST, PUT, GET, PATCH)