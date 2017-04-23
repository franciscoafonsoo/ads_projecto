# Introdução

​	O ponto de partida para arealização deste trabalho é contribuir para o desenvolvimentoiterativo do sistema de vendas POS da cadeia de supermercados µPreço.

​	Esta primeira etapa consistena análise e na planificação da organização da empresa bem comoem três casos de uso:

​	- Inserir um novo empregadona empresa;

​	- Pedir transferência de umempregado entre lojas;

​	- Processar essatransferência;

Depois de uma primeira análiseao universo do problema, concluímos que cada loja possui uma morada,um distrito, um contacto telefónico, um número de fax e um email.Existem também funcionários que representam todos os trabalhadoresda loja (empregados e gerentes) que possuem um número defuncionário, um nome, uma data de nascimento, um contactotelefónico, a data em que entraram na empresa e o salário mensal.Na classe dos funcionários temos então os empregados da loja, ogerente da loja, o subgerente de uma secção e o gerente distritaldas lojas de um distrito. Existem duas secções em cada loja, sendoelas as vendas e a administração. 

# Artefactos

## Caso de Desenvolvimento (feito, rever)

|    Disciplina     |        Artefacto        | Arranque | Elaboração 1 |
| :---------------: | :---------------------: | :------: | :----------: |
| Modelo de Negócio |    Modelo de Dóminio    |          |      S       |
|    Requisitos     | Caso de Desenvolvimento |    S     |      R       |
|                   |   Lista de Requisitos   |    S     |              |
|                   |        Glossário        |    S     |      R       |
|                   | Modelos de Casos de Uso |    S     |      R       |
|                   | Tabela de Casos de Uso  |    S     |      R       |
|      Desenho      |    Modelo de Desenho    |          |      S       |
|                   | Diagramas de interação  |          |      S       |

## Lista de Requisitos (feito, rever)

### Funcionais

- Efectuar o login no sistema. Funcionalidade disponível para: Gerente de Loja, Gerente Distrital e SubGerente.
- Gerir lista de empregados do supermercado: adicionar, actualizar e arquivar dados dos funcionários;
- Consultar loja e secção de um empregado;
- Submeter as vagas disponíveis das secções por loja;
- Consulta/Pedido de transferência de um empregado para uma secção de uma nova loja.
- Atribuir pontuação a um empregado;
- Processar tranferência de um empregado entre lojas.

### Usabilidade

- Correr uma plataforma gráfica;
- Incluir tutorial sobre funcionamento da plataforma.

### Fiabilidade

- O sistema deve ser capaz de lidar com vários pedidos de transferência concorrrentes à mesma secção/loja, fazendo o desempate de uma maneira eficaz e rápida;
- O sistema deve ser capaz de lidar com várias consultas a lojas/empregados/vagas concorrentes;
- O sistema deve ser capaz de várias consultas/submissões paralelas (vários gerentes a atribuir pontuação, vários empregados a serem adicionados/actualizados/arquivados).

### Desempenho

- Durante o uso do sistema, os tempos de resposta devem ser mínimos. Este tempo de resposta deve ser independente do número de funcionários a aceder ao sistema e a efectuar tarefas ao mesmo tempo.

### Suporte

- Capacidade de adaptar o sistema, podendo posteriormente adicionar outro tipo de funcionalidades (p.e gerir caixas, stock, etc).

## Glossário

**Funcionário**:

​	Representa todos osfuncionários da loja, gerentes e empregados.

**Gerente**:

​	Empregado responsável porgerir a loja.

**Sub-gerente**:

​	Empregado responsável porgerir uma secção.

**Gerente Distrital**:

​	Empregado responsável porgerir as lojas de um distrito.

**Loja**:

​	Empresa onde trabalham osfuncionários.

**Secção**:

​	Departamento da empresa ondeos funcionários trabalham.

**Vagas**:

​	Número de lugaresdisponíveis em cada secção em cada loja.

**Pontuação**:

​	Avaliação do funcionáriono fim do ano fiscal, 1 a 5 estrelas. A pontuação é dada pelamédia ponderada dos últimos três anos (o ultimo ano tem peso 3, openúltimo ano tem peso 2, e o antepenúltimo tem peso 1). Estaavaliação é dada pelo subgerente da secção, ou no caso de oempregado ser subgerente, pelo gerente da loja. A pontuação servepara efeitos de desempate entre empregados a quererem transferir-separa o mesmo lugar.

**Pedido de Transferência**:

​	Um empregado efetua um pedidode transferência de uma loja para outra loja, indicando a secçãoque pretende. Se a transferência for para a mesma secção, apontuação é multiplicada por 1.5, dado que o empregado tem maisexperiência nesse papel.

## Modelo de Casos de Uso

### Tabela dos Casos de Uso

| **Ator Principal**                       | Objectivo                                | Risco | Esforço | Prioridade |
| ---------------------------------------- | ---------------------------------------- | :---: | :-----: | :--------: |
| Administrador                            | Consultar vagas                          | Médio |  Médio  |  Elevada   |
| Administrador/funcionarios               | Consultar vagas para uma posição de hierarquia inferor | Baixo |  Médio  |   Média    |
|                                          | Consultar todas as vagas para a posição de empregado | Baixo |  Médio  |   Média    |
|                                          | Consultar vagas por distrito             | Baixo |  Médio  |   Baixa    |
|                                          | Consultar vagas por loja                 | Baixo |  Médio  |   Baixa    |
|                                          | Consultar vagas por secção               | Baixo |  Médio  |   Baixa    |
| Administrador/Gerente Distrital/Gerente de loja/Sub-gerente | Consultar funcionarios de heirarquia inferior | Médio |  Baixo  |  Elevada   |
|                                          | Actualizar funcionário de heirarquia inferior | Baixo |  Baixo  |   Baixa    |
|                                          | Arquivar funcionário de heirarquia inferior | Médio |  Baixo  |   Média    |
|                                          | Adicionar funcionário de heirarquia inferior | Médio |  Baixo  |   Média    |
|                                          | Adicionar vagas de heirarquia inferior   | Médio |  Médio  |  Elevada   |
|                                          | Atribuir pontuação de heirarquia inferior | Médio |  Médio  |  Elevada   |
|                                          | Consultar pontuações de heirarquia inferior | Médio |  Médio  |   Baixa    |
|                                          | Consultar pedidos de transfrência        | Médio |  Médio  |   Média    |
|                                          | Confirmar pedido de transferência        | Baixo |  Médio  |  Elevada   |
|                                          | Confirmar ordem de seleção em caso de empate | Baixo |  Médio  |   Média    |
|                                          | Atualizar dados de um funcionário        | Baixo |  Baixo  |   Baixa    |
| Empregado                                | Consultar pontuação própria              | Baixo |  Baixo  |   Baixa    |
|                                          | Pedir transferência                      | Médio |  Baixo  |  Elevada   |

### Narrativa dos Casos de uso

**Gerir lista de empregados do supermercado:**

O gerente responsável por uma loja faz login no sistema e abre a lista de funcionários da loja. Aqui deve ter duas opções: a opção de criar um novo empregado ou de consultar/arquivar um empregado. Para criar um novo empregado, o gerente insere a informação básica do mesmo (nome, idade e contacto). Deve também escolher que tipo de empregado é (sub-gerente ou funcionário), e em que loja/secção vai trabalhar. No caso do novo empregado ser um gerente de loja, esta acção deve ser realizada por um gerente distrital, que deve ter a opção extra de registar um gerente de loja. Para consultar/arquivar informações sobre um funcionário, deve clicar num funcionário espeficio na lista apresentada.

**Consultar loja e secção de um empregado:** 

Qualquer um dos gerentes (responsável por loja, sub gerente e gerente distrital) pode fazer login no sistema e abrir a lista de funcionários da loja. Ao clicar num empregado, deve ser possível consultar a loja e secção onde o mesmo trabalha.

**Submeter as vagas disponíveis das secções por loja:**

- Consulta/Pedido de transferência de um empregado para uma secção de uma nova loja.
- Atribuir pontuação a um empregado;
- Processar tranferência de um empregado entre lojas.



## Diagramas de Sequencia do Sistema

## Modelo de Dóminio

## Modelos de Desenho

## Diagramas de Colaboração

## Descrição de casos de uso

INSERIR EMPREGADO

	Ator Primário:

    		Gerente de Loja	

	Interessados e Interesses:
    		Gerente (loja ou distrital) – Quer inserir um novo empregado no sistema, para que este fique registado na cadeia da loja.
    		Empregado – Quer começar a trabalhar na empresa estando registado no sistema para que as suas ações/atividades fiquem registadas.


	Pré-Condições:
    		Gerente tem que estar identificado e autenticado no sistema.

	Pós-Condições:
    		Empregado é inserido no sistema da empresa, ficando associado um identificador único ao mesmo e uma password.




Cenário Principal de Sucesso:

    1. O gerente inicia o registo de um novo empregado no sistema;
    2. O sistema apresenta campos para preencher o nome, número de identificação fiscal, data de nascimento, telemóvel e secção do novo empregado.
    3. Depois do gerente preencher os dados necessários no formulário, o mesmo submete o registo no sistema.
    4. Após a submissão, o sistema verifica que os campos foram preenchidos corretamente e apresenta os campos submetidos anteriormente, a loja em que se está a adicionar o empregado  (baseada na loja do ator primário) e o salário (calculado baseado na secção), pedindo a confirmação desta informação de modo a concluir o registo do novo empregado no sistema sem erros e com sucesso.
    5. Aquando a confirmação do registo pelo gerente, o sistema verifica que o novo empregado ainda não existe na lista de funcionários registados e que os campos são válidos.
    6. O sistema apresenta o resultado da submissão (Empregado registado com sucesso), apresentando o número de identificação único do novo empregado e uma password temporária para que o funcionário consiga aceder ao sistema pela primeira vez, para que depois possa alterar.

Extensões:

    4a. Um dos campos do formulário não é devidamente bem preenchido e por isso não é validado (ex: nº de identificação fiscal não tem 9 dígitos). 
        1. O sistema mostra uma mensagem a avisar que houve um erro no preenchimento do formulário, discriminando-o.
        2.i. O gerente confirma a mensagem, voltando ao ponto 2 de modo a emendar o campo e prossegue para os passos seguintes.
        2.ii. O gerente cancela a criação de um novo empregado, voltando ao menu principal.

    5a. O sistema verifica que o empregado já existe na lista de empregados.
        1. O sistema mostra uma mensagem a informar que o empregado já estava registado na empresa e cancela a operação.
        2.i. O gerente confirma a mensagem, voltando ao ponto 2 de modo a emendar o campo e prossegue para os passos seguintes.
        2.ii. O gerente cancela a criação de um novo empregado, voltando ao menu principal.

    5b. O sistema verifica que um dos campos únicos do formulário (ex: número de identificação fiscal) já existe associado a um empregado registado no sistema.
        1. O sistema mostra uma mensagem a avisar que houve um erro no preenchimento do formulário, discriminando-o.
        2.i. O gerente confirma a mensagem, voltando ao ponto 2 de modo a emendar o campo e prossegue para os passos seguintes.
        2.ii. O gerente cancela a criação de um novo empregado, voltando ao menu principal.

## Diagramas de Sequência

Ator Primário:

    Gerente / Gerente Distrital
Interessados e Interesses:
    Gerente – Quer inserir um novo empregado no sistema, para que este fique registado.
    Funcionário – Quer começar a trabalhar na empresa.
Pré-Condições:
    Gerente tem que estar identificado e autenticado no sistema.
    Se o funcionário a inserir for um gerente de loja, o gerente tem que ser distrital.
Pós-Condições:
    Funcionário é inserido no sistema da empresa.
Cenário Principal de Sucesso:
    1. O gerente inicia o registo de um novo funcionário no sistema;
    2. O sistema apresenta campos para preencher o nome, número de identificação fiscal, data de nascimento, telemóvel e secção do novo funcionário.
    3. Depois dos dados submitidos, o sistema deve apresentar os campos submitidos mais a loja (baseada na loja do actor primário) e o salário (calculado baseado na secção) e pedir para o actor primário confirmar os dados apresentados.
    4. O sistema apresenta o resultado da submissão (Funcionário registado com sucesso) e o número de funcionário.
Extensões:
    2a. O número de identificação não tem 9 dígitos. 
        1. O sistema mostra uma mensagem a avisar que o número de identificação não está correcto.
        2.i. O actor confirma a mensagem, e volta ao ponto 2.
        2.ii. O actor cancela a criação de um novo funcionário, voltando ao menu principal.
    3a. O actor não preenche todos os campos.
        1. O sistema mostra uma mensagem a dizer que não preencheu um campo específico.
        2.i. O actor confirma a mensagem, e volta ao ponto 2.
        2.ii. O actor cancela a criação de um novo funcionário, voltando ao menu principal.
    4a. O sistema verifica que o funcionário já existe na lista de funcionários.
        1. O sistema mostra uma mensagem a informar que o funcionário já estava registado na empresa e conclui a operação.
        2.i. O actor confirma a mensagem, e volta ao ponto 2.
        2.ii. O actor cancela a criação de um novo funcionário, voltando ao menu principal.
    5a. O sistema verifica que o número de identificação fiscal já existe agregado a um funcionário.
        1. O sistema mostra uma mensagem a informar que o número de identificação fiscal já se encontra agregado a outro funcionário.
        2.i. O actor confirma a mensagem, e volta ao ponto 2.
        2.ii. O actor cancela a criação de um novo funcionário, voltando ao menu principal.


Ator Primário:
​	Funcionário
Interessados e Interesses:
​	Funcionário – Quer efetuar um pedido de transferência para outra loja ou para outra secção.

​	Sub-gerente – Quer funcionários para preencher as vagas na sua secção.

​	Gerente - Quer funcionários para preencher as vagas na sua loja.

Pré-Condições:

​	Funcionário tem que estar identificado e autenticado no sistema.

​	O pedido só pode ser efetuado até ao dia 10 do mês corrente. 

​	Tem de existir vagas disponiveis para onde o funcionário pode transferir.

Pós-Condições:

​	O pedido de transferência é efetuado com sucesso. 

​	Calculo da pontuação específica para uma vaga é guardado com sucesso.

Cenário Principal de Sucesso:
​	1- O actor consulta as vagas disponíveis;

​	2- O sistema apresenta todas as vagas disponíveis, com a seguinte informação: identificação da loja, nome da secção, numéro de vagas e mês de abertura da vaga. 

​	3- O actor indica ao sistema a que vaga se pretende registar.

​	4- O sistema confirma o pedido, mostrando o numero de identificação do processo.

Extensões:

​	2a. O actor pede ao sistema para filtrar as vagas por loja ou secção.

​		1. o sistema apresenta todas as vagas por loja ou secção escolhida pelo 
​	


**Extensões:**



​	2- O funcionário filtra as vagas disponíveis por loja ou secção.
