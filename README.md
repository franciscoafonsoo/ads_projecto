# Introdução

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

## Modelo de Casos de Uso

### Tabela dos Casos de Uso

### Narrativa dos Casos de uso

**Gerir lista de empregados do supermercado:**

O gerente responsável por uma loja faz login no sistema e abre a lista de funcionários da loja. Aqui deve ter duas opções: a opção de criar um novo empregado ou de consultar/arquivar um empregado. Para criar um novo empregado, o gerente insere a informação básica do mesmo (nome, idade e contacto). Deve também escolher que tipo de empregado é (sub-gerente ou funcionário), e em que loja/secção vai trabalhar. No caso do novo empregado ser um gerente de loja, esta acção deve ser realizada por um gerente distrital, que deve ter a opção extra de registar um gerente de loja. Para consultar/arquivar informações sobre um funcionário, deve clicar num funcionário espeficio na lista apresentada.

**Consultar loja e secção de um empregado:** 

Qualquer um dos gerentes (responsável por loja, subgerente e gerente distrital) pode fazer login no sistema e abrir a lista de funcionários da loja. Ao clicar num empregado, deve ser possivél consultar a loja e secção onde o mesmo trabalha.

**Submeter as vagas disponíveis das secções por loja:**



- Consulta/Pedido de transferência de um empregado para uma secção de uma nova loja.
- Atribuir pontuação a um empregado;
- Processar tranferência de um empregado entre lojas.

## Diagramas de Sequencia do Sistem

## Modelo de Dóminio

## Modelos de Desenho

## Diagramas de Colaboração

## Diagramas de Sequência


## Notas e Apontamentos (Para remover no fim)

- Organização da empresa e dos seus empregados
- Lojas
  - Gerente
  - Gerente Distrital
  - Empregados
  - Secções
    - Vendas
      - Sub Gerente
    - Administração
      - Sub Gerente
- Gerir empregados. 
  - Adicionar
  - Actualizar
  - Arquivar
  - Transferencias
- Tabela com a entrada: Loja, Secção, Vaga
- Gerente Responsavel pela loja
- Gerente Distrital das lojas do distrito
- ​