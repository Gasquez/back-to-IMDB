Narrative: 
In order to add something to the database
As a user
I want to add a review

Scenario: add a review that doesn't exist
Given la revue Terminator n'existe pas
When j'ajoute la revue Terminator avec la date de diffusion "1984-12-31 00:00:00" au système 
Then la revue Terminator est accessible et sa date de création est égale à la date de dernière modification

Scenario: add a review that already exists
Given la revue Terminator existe
When j'ajoute la revue Terminator avec la date de diffusion "1984-12-31 00:00:00" au système 
Then il n'existe que 1 revue nommée Terminator en bdd et sa date de création est antérieure à T