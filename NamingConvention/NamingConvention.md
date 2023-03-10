# Naming Convention

In questo file voglio raccogliere tutte le naming convention che ho deciso di adottare in questo progetto.
E' la mia prima desktop application, quindi ho deciso di spendere un po' di tempo per stabilire una serie di 
convenzioni per i diversi linguaggi utilizzati. Mi ritornerà sicuramente molto utile in progetti futuri.

Cercherò di adattarmi agli standard di ogni linguaggio, scegliendo il case più adatto. I case più utilizzati 
saranno lo _snake case_ e il _camel case_. In rari casi utilizzerò il _kebab case_. 

**Java**

- utilizzare sempre il camel case nelle variabili e nei metodi
- le costanti deveono essere scritte tutte in maiuscolo e in snake case
- nomi delle classi e delle interfacce devono avere l'iniziale maiuscola
- una dichiarazione di variabile per ogni riga
- tutte le inizializzazioni devono essere inserite all'inizio del metodo o dell' if

**SQL**

- utilizzare il singolare per i nomi delle tabelle e delle colonne
- utilizzare lo snake case sia per i nomi delle tabelle sia per le colonne
- per identificare gli id delle tabelle, utilizzare la convenzione id_nome_colonna
- per identificare le foreign key delle tabelle, utilizzare la convenzione fk_nome_colonna
- nelle query utilizzare le paroli chiavi del linguaggio in maiuscolo