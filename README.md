# 💲 Sistema Bancário

Projeto feito em Java com o objetivo de simular, no terminal, um aplicativo de banco digital e realizar suas principais operações.

## ✅ Funcionalidades

1. Cadastrar um usuário com nome, data de nascimento e e-mail.  
2. Abrir uma conta com os dados do usuário, definindo uma senha e realizando um depósito inicial.  
3. Acessar a conta com e-mail e senha.  
4. Visualizar os dados da conta, incluindo o saldo.  
5. Realizar operações de transferência, saque e depósito, com autenticação por senha.  
6. Visualizar o registro de atividades da conta.  
7. Encerrar a conta.
8. Validação em todos os campos de entrada.

 ## ▶️ Como Executar

1. Ter o **Java** (*versão 21*) e o editor de código **Eclipse** instalados no computador.  
2. Baixar o arquivo `.zip` ou clonar o repositório: https://github.com/lucaskauaa/sistema-bancario.git (é necessário ter o Git instalado).  
3. Abrir a pasta do projeto no Eclipse → acessar o arquivo `src/program/Main.java` → clicar com o botão direito → **Run As** → **Java Application**.

## 🧪 Exemplos de Uso

### Menu Inicial

```
==========================
Menu:
O que você deseja fazer?

[1] Criar conta
[2] Acessar conta
[3] Encerrar programa

```

### Criação de Conta

```
===================================
Criação de conta:

Insira o nome do titular da conta: Lucas Kauã
Data de nascimento (dd/mm/aaaa): 15/05/2003
Email: lucaskaua@email.com
Defina uma senha numérica com 04 dígitos: 1234
Deseja fazer um depósito inicial? s/n s
Insira o valor do depósito: 100,00

Conta criada com sucesso!
```

### Acesso a conta

```
Acessar conta:

Insira o email da conta: lucaskaua@email.com
Digite a senha da conta: 1234

================================
Conta: 
Nome do titular: Lucas Kauã
Data de nascimento: 15/05/2003
Email: lucaskaua@email.com

Número da conta: #1
Saldo: R$100,00

[1] Transferir
[2] Sacar dinheiro
[3] Depositar dinheiro
[4] Registro de atividades
[5] Encerrar conta

[0] Voltar para o menu inicial

Escolha uma opção: 
```

### Transferência

```
Transferência:

Insira o email da conta para a qual você deseja transferir: josesilva@email.com
Valor a ser transferido: 50,00
Digite a senha da conta: 1234

Transferência realizada com sucesso!
Transferido R$50,00 para José Silva
```

### Saque

```
Saque:

Valor do saque: 30,00
Digite a senha da conta: 1234

Saque realizado com sucesso!
Valor sacado: R$30,00
```

### Depósito

```
Depósito:

Valor do depósito: 20,00

Deposito realizado com sucesso!
Valor depositado: R$20,00
```

### Registro de atividade

```
Atividade:

-> 26/07/2025 - 17:06 | Depósito | + R$20,00
-> 26/07/2025 - 17:03 | Saque | - R$30,00
-> 26/07/2025 - 17:01 | Transferência para josesilva@email.com | - R$50,00
```

### Encerramento de conta

```
Encerramento de conta:

Digite a senha da conta: 1234

Conta encerrada com sucesso!
```

### Autenticação de e-mail

```
Acesso a conta:

Insira o e-mail da conta: lucas123@email.com

Email incorreto ou conta inexistente!
Tente novamente.
```

### Autenticação de senha

```
Acesso a conta:

Insira o email da conta: lucaskaua@email.com
Digite a senha da conta: 5678

Senha incorreta. Tente novamente.
```

### Validação de entrada do usuário

```
Depósito:

Valor do depósito: abcde

[Erro] -> Insira um valor numérico válido!

Valor do depósito: 100,00
```

## 🧠 Tecnologias Utilizadas

- Java  
- Programação Orientada a Objetos (Composição de classes, métodos estáticos)
- Tratamento de Exceções

## 📁 Estrutura do Projeto

```
src/
└── entities/
| └── Account.java
| └── Bank.java
| └── Customer.java
├── program/
│ └── Main.java
└── menus/
| └── AccountMenu.java
| └── BankMenu.java
└── service/
| └── AccessService.java
| └── AccountService.java
| └── RegistrationService.java
| └── TransactionSevice.java
└── util/
| └── InputReader.java
| └── ValidateInput.java
```

### 🔍 Descrição das principais classes

- `Main.java`: Classe principal que inicia o programa.
- `Account.java`: Representa os dados da conta bancária e realiza operações básicas como saque e depósito.
- `Bank.java`: Representa o banco, armazenando listas de usuários e contas.
- `AccountMenu.java`: Exibe o menu com as operações disponíveis para uma conta acessada.
- `BankMenu.java`: Exibe o menu inicial do sistema, permitindo registrar ou acessar contas.
- `AccessService.java`: Lida com a autenticação e busca de contas através de email e senha.
- `AccountService.java`: Realiza operações como saque, depósito, exibição do histórico e encerramento da conta.
- `RegistrationService.java`: Gerencia o registro de novos usuários e contas bancárias.
- `TransactionService.java`: Contém a lógica para transferências entre contas.
- `InputReader.java`: Utilitário para leitura segura das entradas do usuário no terminal.
- `ValidateInput.java`: Valida entradas fornecidas pelo usuário, como email, senhas e valores numéricos.


## 🧑‍💻 Autor

Desenvolvido por **Lucas Kauã**.
