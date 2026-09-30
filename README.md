# Sistema de Gerenciamento de Estoque em Java

Este repositório contém uma aplicação Java baseada em console projetada para simular o controle básico de mercadorias em um estoque. O programa permite que o usuário visualize o inventário atual, adicione novos produtos e remova itens cadastrados de forma dinâmica.

---

## 📌 Visão Geral do Programa

O **`Sistema_GerenciamentoEstoque`** é um software de terminal focado na manipulação de dados em tempo de execução. A característica central desta aplicação é a sua **natureza volátil**: todos os dados são manipulados diretamente na **Memória RAM**. 

Isso significa que a lista de produtos começa completamente vazia toda vez que o programa é iniciado e deixa de existir assim que a execução é encerrada pelo usuário (os dados não são persistidos em banco de dados ou arquivos).

---

## 🏗️ Estrutura do Código e Importações

O arquivo inicia definindo sua organização lógica e trazendo ferramentas prontas do ecossistema Java para manipulação de dados e entrada de dados via teclado:

* **`package main;`**: Determina o pacote (diretório) onde a classe está inserida, organizando o escopo do projeto.
* **`import java.util.Scanner;`**: Importa a classe responsável por capturar o que o usuário digita no teclado através do terminal.
* **`import java.util.ArrayList;`**: Importa a estrutura de dados de lista dinâmica, fundamental para o armazenamento dos produtos sem a necessidade de definir um limite fixo de tamanho máximo.

---

## ⚙️ Componentes Principais da Inicialização

Dentro do método principal (`main`), três elementos cruciais são instanciados para ditar o comportamento do sistema:

### O Scanner (`sc`)
Responsável pela ponte de comunicação entre o usuário e o sistema. Ele monitora o fluxo de entrada padrão do sistema operativo (`System.in`).

### O ArrayList (`lista`)
A escolha do `ArrayList<String>` é o ponto chave do programa. Ele resolve o problema de criar uma lista que começa sem nenhum produto, mas que pode crescer indefinidamente à medida que novas mercadorias são adicionadas. Ao contrário de um array tradicional (`String[]`), ele gerencia seu próprio tamanho e posições de forma 100% dinâmica.

### A Variável de Controle (`opcao`)
Um número inteiro inicializado em `0` que serve como o gatilho de decisão para o laço de repetição. É ela quem dita qual bloco de código será executado ou se o programa deve parar.

---

## 🔁 O Fluxo de Repetição (`while`)

O coração da aplicação roda sob um laço **`while (opcao != 4)`**. Esse bloco garante que o sistema permaneça ativo em um loop contínuo, exibindo o menu interativo repetidas vezes, até que o usuário decida digitar o número `4` explicitamente.

A cada ciclo do loop, as seguintes ações ocorrem sequencialmente:
1. O menu gráfico textual é renderizado na tela.
2. O prompt aguarda a digitação de um comando numérico através do método `sc.nextInt()`.
3. O valor digitado é guardado na variável `opcao`.
4. O programa avalia as condições (`if`) para determinar a rota correta de execução.

---

## 🔍 Análise Passo a Passo das Opções do Menu

```text
---------------------------------------
Sistema de gerencimanto de estoque
1) - Ver estoque 
2) - Adicionar produto 
3) - Remover produto 
4) - Sair 
---------------------------------------
```

### Opção 1: Ver Estoque
Ativada quando `opcao == 1`. Esse bloco faz o diagnóstico visual do inventário atual utilizando duas abordagens complementares fornecidas pelo `ArrayList`:
* **`lista.size()`**: Retorna um número inteiro representando o total exato de itens contidos no estoque naquele instante. Se a lista estiver vazia, exibirá `0`.
* **`System.out.println(lista);`**: O Java converte internamente o objeto `ArrayList` em uma representação textual legível, exibindo os itens envolvidos por colchetes e separados por vírgulas (Exemplo: `[Arroz, Feijão]`).

### Opção 2: Adicionar Produto
Ativada quando `opcao == 2`. Solicita que o usuário informe o nome do item desejado. 
* A captura é feita por meio do método `sc.next()`, que lê a próxima sequência de caracteres até encontrar um espaço em branco.
* Logo em seguida, o método **`lista.add(item)`** empurra essa nova string para o final da lista dinâmica, expandindo o tamanho do estoque automaticamente.

### Opção 3: Remover Produto
Ativada quando `opcao == 3`. Esta seção implementa a remoção segura de um item por nome e utiliza uma técnica essencial de limpeza de fluxo:
* **`sc.nextLine();`**: Esta linha vazia serve para limpar o "buffer" do teclado. Como a leitura anterior da opção utilizou um número (`sc.nextInt()`), o caractere de quebra de linha (o "Enter") fica preso na memória do Scanner. Sem essa limpeza, o programa pularia a pergunta de remoção instantaneamente.
* **Captura do Alvo**: O método `sc.nextLine()` captura com precisão o nome do item que o usuário deseja deletar do estoque.
* **O Mecanismo de Remoção**: O código utiliza a expressão condicional diretamente no `if (lista.remove(remover))`. O método `.remove()` tenta localizar o texto exato dentro da lista. 
    * *Se encontrar:* Ele deleta o item, rearranja os índices internos da lista, retorna o valor lógico `true` e executa a mensagem de sucesso.
    * *Se não encontrar:* Ele mantém a lista intacta, retorna o valor lógico `false` e desvia o fluxo para o bloco `else`, emitindo o aviso de que o produto não consta no estoque.

### Opção 4: Sair
Ativada quando `opcao == 4`. Aciona a instrução `break`, que quebra o laço de repetição `while` imediatamente, forçando o programa a sair do loop sem precisar reavaliar a condição do topo.

---

## 🛑 Finalização e Encerramento do Ciclo de Vida

Fora do laço de repetição, localizam-se as instruções finais de encerramento do programa:

* **`sc.close();`**: Ocorre o fechamento manual do objeto `Scanner`. Isso avisa ao sistema operacional que os recursos de hardware dedicados à leitura do teclado foram liberados, evitando vazamentos de memória (*resource leaks*).
* **Destruição da Memória**: Ao alcançar a chave de fechamento do método `main`, o ambiente de execução do Java (JVM) encerra o processo. A variável `lista` perde sua referência e toda a coleção de produtos que o usuário cadastrou é permanentemente eliminada da memória RAM do computador.
