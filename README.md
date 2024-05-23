ANALISI funzionale:
Obbiettivo del software:
Il software deve permettere all’utente di gestire una rubrica telefonica. L’utente desidera se effettuare il login o meno, inserendo l’username e la password. La password può essere manualmente dall’utente o può essere richiesta al programma. Quest’ultima (la password) verra richiesta ogni volta che l’utente desidera gestire i contatti nascosti.
Quindi i principali requisiti del software sono:
-Aggiungere, modificare e cancellare contatti.
-Nascondere contatti specifici che posso essere visualizzati solo inserendo una password
-Gestire una lista contatti(anche chiamate recenti, array di lunghezza 5), che rispetti la visibilità dei contati nascosti.
-Tornare alla situazione iniziale in cui i contatti nascosti non sono visibili.

Requisiti Funzionali
1. Gestione Contatti
• Aggiungere Contatto: L'utente può aggiungere un nuovo contatto alla rubrica.
• Modificare Contatto: L'utente può modificare i dettagli di un contatto esistente.
• Cancellare Contatto: L'utente può eliminare un contatto dalla rubrica.
• Nascondi contatto/rendere visibile contatto: l’utente inserendo l’username e la password, può nascondere
2. Visualizzazione Contatti
• Visualizzare Contatti: L'utente può visualizzare tutti i contatti visibili.
• Visualizzare Contatti Nascosti: L'utente può visualizzare i contatti nascosti solo dopo aver inserito una password, e chiederemo prima di visualizzare i contatti se l’utente desidera visualizzare anche i contatti nascosti inserendo username e password
• Visualizzare contatti in maniera ordinata(utilizzando algoritmi di ordinamento del bubble-sort e selection-sort)
• Metodo autenticazione:Questo metodo viene chiamato nei metodi cancella contatto nascondi/rendi visibile contatto in cui chiederà all’utente l’username e la password, se queste sono corrette , l’utente dato che è autentificato può gestire i contatti, mentre se è errata stamperà in output(“USERNAME O PASSWORD ERRATA”).
3. Gestione Ultime Chiamate
• Registro Chiamate: L'utente può visualizzare il registro delle ultime chiamate effettuate, ricevute o perse, che deve rispettare la visibilità dei contatti nascosti.
L’utente quando chiama un contatto,desidera lui quando fermare la chiamata(realizzare metodo chiamate).Sulla gestione ultime chiamate, dobbiamo visualizzare NOME,TELEFONO e DURATA.
4. Autenticazione
• Inserimento Password: L'utente deve poter inserire una password per accedere ai contatti nascosti, la password può essere richiesta dall’utente in maniera tale che venga generata automaticamente dal programma, oppure essere inserita manualmente dall’utente. Gestire caso in cui username o password errata.
5. Reset Visibilità
• Tornare alla Situazione Iniziale: L'utente può tornare alla situazione iniziale in cui i contatti nascosti non sono visibili.
6. Scrivere file e leggere file: L’utente può decidere se creare un nuovo contatto, direttamente dal terminale oppure da un file csv (es. excel), nel primo caso il contatto inserito dal terminale sarà direttamente visibile anche nel file csv, mentre nel secondo caso l’utente inserisce il contatto direttamente dal file csv e una volta che visualizzi la lista contatti da terminale, esso sarà visibile(se non è nascosto).

ANALISI TECNICA:

GESTIONE DEL PROGRAMMA:(metodi e classi)
Gestione rubrica dei contatti:
-Metodo aggiungiContatto: questo metodo permette di inserire un nuovo contatto, chiedendo all’utente il nome,cognome, telefono e la tipologia del contratto che può essere (cellulare, aziendale,…)
•Modificare Contatto
Metodo: modificaContatto(id, nome, telefono, visibile)
questo metodo permette di modificare, in base alla scelta dell’utente: nome , cognome, numero di telefono o tipologia del contratto, e ritornare il contatto.

• Metodo cancella contatto: questo metodo permette di cancellare un contatto, accedendo(nel codice, invochiamo il metodo autentificazione), e mostrando la lista di contatti scegliamo il contatto che desideriamo eliminare in base alla posizione.
• Metodo Nascondere/Rendere Visibile Contatto: questo metodo permette di nascondere o rendere visibile un contatto, richiedendo l’accesso all’utente.

2. Visualizzazione Contatti
• Visualizzare Contatti
3. Metodo visualizza info profilo: questo metodo permette di visualizzare i dati del mio profilo(cioè del profilo utente),con un apposita interfaccia.
4. Metodo modifica profilo, questo metodo permette di modificare le proprie credenziali.
• Metodo: visualizzaContatti()
• Restituisce: Lista di contatti visibili
• Visualizzare Contatti Nascosti
• Metodo: visualizzaContattiNascosti(username, password)
• Parametri: username (stringa), password (stringa)
• Restituisce: Lista di contatti nascosti se l’autenticazione è valida
• Ordinare Contatti
• Metodo: ordinaContatti(metodo)
• Parametri: metodo (stringa: ‘bubble-sort’ o ‘selection-sort’)
• Restituisce: Lista di contatti ordinati
3. Gestione Ultime Chiamate
• Registro Chiamate
• Metodo: visualizzaRegistroChiamate()
• Restituisce: Lista delle ultime 5 chiamate con nome, telefono e durata
• Effettuare Chiamata
• Metodo: effettuaChiamata(id)
• Parametri: id (intero)
• Comportamento: Registra la chiamata e chiede all’utente
