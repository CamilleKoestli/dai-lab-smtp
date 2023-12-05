Rapport sur le Projet SMTP
=============

Introduction
----------

Le projet a pour but d'automatiser l'envoi de courriers électroniques à l'aide d'un client SMTP. Il comprend
plusieurs composants essentiels tels que les classes `Email`, `EmailGroup`, `Message`, `SMTPClient`, et `MessageReader`.
L'objectif global est de simplifier le processus d'envoi de courriels en regroupant les destinataires en groupes et en
utilisant des messages préconfigurés.

MockMock
----------
**Description**

**Configuration**

Client SMTP
----------
**Configuration**
Configuration du Projet : Ouvrez le projet dans votre environnement de développement Java préféré. Assurez-vous que les
bibliothèques nécessaires sont présentes.

Configuration des Fichiers d'Entrée : Mettez à jour les chemins d'accès aux fichiers d'adresses e-mail (emails.utf8) et
de messages (messages.utf8) dans la classe Main.

Exécution du Projet : Exécutez la classe Main. Cela déclenchera la création d'objets Email à partir des groupes d'
e-mails et des messages fournis. Les courriers électroniques seront ensuite envoyés au serveur SMTP factice.

Visualisation des Résultats : Suivez la console pour voir les étapes du processus d'envoi de courriers électroniques, y
compris les réponses du serveur SMTP factice.

Implémentation
----------
On peut séparer notre code en 4 parties :

Partie SMTP : S'occupe de l'envoi des messages.
Partie config : Récupère les propriétés et vérifie les entrées.
Partie fileIO : Elle modélise le message, ainsi que les groupes de victimes et les expéditeurs.
Partie Main : 

**Partie SMTP**
* `Email`: Représente un courrier électronique avec un expéditeur, des destinataires, un sujet et un corps. Peut être créé à
partir d'un groupe d'e-mails et d'un message.

* `EmailGroup`: Groupe d'e-mails avec un expéditeur et des destinataires. Peut être créé en lisant les adresses à partir
d'un fichier.

* `Message`: Contient le sujet et le corps d'un message électronique.

* `SMTPClient`: Client SMTP pour envoyer des courriers électroniques. Gère la connexion au serveur SMTP, l'envoi de demandes
SMTP et la réception des réponses.

* `MessageReader`: Hérite de la classe Message. Lit le sujet et le corps d'un courrier électronique à partir d'un fichier.

**Partie fileIO**

**Partie config**

**Partie Main**
La classe `Main` est l'entrée principale du programme. Elle lit les groupes d'e-mails et les messages à partir de
fichiers, crée des objets `Email` et les envoie via le `SMTPClient` au serveur SMTP.

L'implémentation suit une approche modulaire avec des classes distinctes pour la gestion des courriers électroniques,
des groupes d'e-mails, des messages, et de la communication SMTP.

L'utilisation de la classe `MessageReader` permet de simplifier la lecture du contenu du courrier électronique à partir d'
un fichier.

Exemple d'échange
----------

Conclusion
----------
Le projet fournit un moyen automatisé d'envoyer des courriers électroniques simulés à l'aide d'un serveur
SMTP. La séparation des responsabilités dans différentes classes facilite la maintenance et l'extension du
système.