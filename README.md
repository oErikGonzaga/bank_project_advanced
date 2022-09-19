# __Java Avançado com Spring__

## **Case - Sistema Bancário**

<br>

- Configurar Projeto SpringBoot
- Configurar SpringData
- Introdução ao Hibernate (Mapeamento de Banco Java)
- Configurar FlyWay (Gerenciador de Banco)
- Modelar as Entidades Hibernate JPA

<br>

- Introdução ao SpringData
- Implementar Cadastro de Conta Bancária
	- _Construção da API (REST)_
	- _Consumir a API do VIACEP para buscar o endereço do Cliente_

<br>

- Implementar Depósito
- Implementar Saque
	- _Introdução sobre Cache_
	- _Introdução sobre Redis (Banco de Dados em Memória)_
	- _Implementação de Solução de Limite de Saque por dia - Redis_

<br>

- Implementar transferência entre contas (Usar limite de saque acima)
- Implementar uma funcionalidade de concessão de empréstimo
	- _Introdução sobre processos sincronos e assincronos_
	- _Introdução sobre Messageria_
	- _Introdução sobre RabbitMQ_

<br>

- Implementar uma feature de negativação
	- _Vamos criar um Scheduler (agendador) para verificar on inadimplentes_
	- _Consumir uma api do SERASA para adicionar ou remover os negativados_
		_-Integração via FEIGN CLIENT (POST, PUT, GET, PATCH)_