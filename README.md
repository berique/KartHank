Executando o projeto
--------------------

No caso de *NIX simlesmente digite:

./mvnw package exec:java

Se for windows:

mvnw.cmd package exec:java

O mvnw e mvnw.cmd irá baixar a versão 3.3.9 do Maven, construir o projeto e executar.

Pontos de melhoria
------------------

* Colocar mais comentário no código.
* Desacoplar mais as classes, apesar delas estarem bem consisas.

Problemas
---------

* Por algum motivo o tempo após o vencedor do VETTEL está todo negativo.

Observação
----------

* O código está compilado para ter compatibilidade com o Java 1.5, se tivesse utilizado o Java >= 1.8 poderia ter utilizado o Lambda e o Streams para algumas ações. 

