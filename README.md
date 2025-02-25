Web API Project

Descrizione del Progetto
Questa applicazione è una Web API che si connette a un database MySQL.  
Le chiamate API vengono testate tramite Postman.

 Tecnologie Utilizzate
- Java / Spring Boot
- Maven
- MySQL
- Postman

Funzionamento
1. Nel progetto su maven fare clean e poi install
2. Avviare l'applicazione DownTownTourApplication
3. Avviare il database MySQL e crea la struttura del database.
4. Testare le chiamate API con Postman, usando i seguenti endpoint:
   - `GET /api/users` - Recupera tutti gli utenti
   - `POST /api/users` - Crea un nuovo utente
   - `PUT /api/users/{id}` - Aggiorna un utente
   - `DELETE /api/users/{id}` . Elimina un utente
