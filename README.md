Rapport laboratoire 4 : SMTP
=============

Introduction
----------

Le projet a pour but d'automatiser l'envoi de courriers électroniques à l'aide d'un client SMTP. Il comprend
plusieurs composants essentiels tels que les classes `Email`, `EmailGroup`, `Message`, `SMTPClient`, et `MessageReader`.
L'objectif global est de simplifier le processus d'envoi de courriels en regroupant les destinataires en groupes et en
utilisant des messages préconfigurés.

MockMock
----------

### Description

MockMock appartient à la catégorie des serveurs conçus pour reproduire un service réel. Dans notre contexte, nous
l'utilisons pour simuler un serveur SMTP. La version de MockMock que nous avons adoptée n'est pas
la version originale ; il s'agit d'un clone provenant d'un dépôt [GitHub MailDev](https://github.com/maildev/maildev).

Cette solution nous permet de voir les différentes parties des courriers électroniques que nous envoyons, notamment
les en-têtes, le sujet et le corps.

### Configuration

**//TODO à compléter**
Pour construire l'image du container, nous devons lancer la commande

    ./build-image.sh

Une fois que l'image est prête, on peut l'exécuter en utiliser le code

    docker run -d -p 1080:1080 -p 1025:1025 maildev/maildev

Il est possible de confirmer que le conteneur s'exécute correctement en consultat la liste des container. Le statut "
running" doit apparaître

    docker ps -a

Pour accéder à l'interface web fournie par MockMock, il suffit de se rendre à l'adresse `localhost:8080` dans un
navigateur. Sur cette page, vous pourrez consulter tous les e-mails reçus par le serveur et examiner en détail leurs
informations.

Client SMTP
----------

### Configuration

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

### Diagramme UML

![diagram.png](main/java/ch/heigvd/diagram.png)

On peut séparer notre code en 4 parties :

* Partie SMTP : S'occupe de l'envoi des messages.
* Partie config : Récupère les propriétés et vérifie les entrées.
* Partie fileIO : Elle modélise le message, ainsi que les groupes de victimes et les expéditeurs.
* Partie Main : Entrée principale du programme

### Partie SMTP

* `Email`: Représente un courrier électronique avec un expéditeur, des destinataires, un sujet et un corps. Peut être
  créé à
  partir d'un groupe d'e-mails et d'un message.

* `EmailGroup`: Groupe d'e-mails avec un expéditeur et des destinataires. Peut être créé en lisant les adresses à partir
  d'un fichier.

* `Message`: Contient le sujet et le corps d'un message électronique.

* `SMTPClient`: Client SMTP pour envoyer des courriers électroniques. Gère la connexion au serveur SMTP, l'envoi de
  demandes
  SMTP et la réception des réponses.

* `MessageReader`: Hérite de la classe Message. Lit le sujet et le corps d'un courrier électronique à partir d'un
  fichier.

### Partie fileIO

* `MessageManager` : La méthode `readMessagesFromJsonFile` lit les messages à partir d'un fichier JSON. Chaque
  message est représenté par un nœud JSON contenant les champs "subject" et "body". Ces informations sont ensuite
  utilisées pour créer des objets Message. L'utilisation de la bibliothèque Jackson facilite la manipulation des données
  JSON. La méthode `getGroupMailsFromJsonFile` va fonctionner de la même manière mais pour les e-mails. Chaque e-mail
  est
  encapsulé dans un nœud JSON avec le champ "mail". Ces adresses sont ensuite mélangées de manière aléatoire et
  regroupées
  par lot de 3 pour former des objets EmailGroup. Cette approche offre une flexibilité accrue en matière de gestion des
  données d'e-mails.

### Partie config

La configuration joue un rôle central dans notre application, regroupant la liste des courriels et des messages utilisés
pour les campagnes d'envoi simulées. Cette section s'inspire des pratiques établies dans le laboratoire précédent pour
garantir la lisibilité et la modularité du code.

* `emails.json` : Ce fichier classe contient l'adresse de la personne qui envoie les e-mails ainsi que les groupes d'
  e-mails qui seront les victimes.

* `messages.json` : Ce fichier représente un courrier électronique avec son sujet et son corps.

### Partie Main

La classe Main est l'entrée principale du programme. Elle lit les groupes d'e-mails et les messages à partir de
fichiers, crée des objets Email et les envoie via le SMTPClient au serveur SMTP.

L'implémentation suit une approche modulaire avec des classes distinctes pour la gestion des courriers électroniques,
des groupes d'e-mails, des messages, et de la communication SMTP.

L'utilisation de la classe MessageReader simplifie la lecture du contenu du courrier électronique à partir d'un fichier.
La classe `Main` est l'entrée principale du programme. Elle lit les groupes d'e-mails et les messages à partir de
fichiers, crée des objets `Email` et les envoie via le `SMTPClient` au serveur SMTP.

L'implémentation suit une approche modulaire avec des classes distinctes pour la gestion des courriers électroniques,
des groupes d'e-mails, des messages, et de la communication SMTP.

L'utilisation de la classe `MessageReader` permet de simplifier la lecture du contenu du courrier électronique à partir
d'un fichier.

Exemple d'échange
----------
Pour réaliser l'échange, il faut se rendre dans le dossier qui contient l'exécutable, c'est-à-dire le
dossier [target](target) et faire la commande suivante depuis le dossier dai-lab-smtp

    java -jar .\target\App-1.0-SNAPSHOT.jar

Sur le serveur MockMock, nous retrouverons les mails qui correspondent aux nombres de groupes dans l'interface web.

**//TODO ajouter échange**

Conclusion
----------
Le projet fournit un moyen automatisé d'envoyer des courriers électroniques simulés à l'aide d'un serveur
SMTP. La séparation des responsabilités dans différentes classes facilite la maintenance et l'extension du
système.