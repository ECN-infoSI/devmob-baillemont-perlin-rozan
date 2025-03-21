Les deux vues disponibles sont TimerScreen et SummaryScreen. 
La vue TimerScreen : 

![Timer](https://github.com/user-attachments/assets/1891a597-df8f-4fc7-af9f-9ccef0a8dc55)

Le timer est défini à 45 minutes. On peut démarrer le timer, le mettre en pause, et l'arrêter. On peut choisir la matière révisée grâce à un menu déroulant : 

![menu_déroulant](https://github.com/user-attachments/assets/8487e1a5-6107-4a45-8c65-ed2a52daf494)

Lorsque l'on arrête le timer, un Objet Revision est crée, qui contient les informations suivantes :
- Heure de début de révision
- Durée de révision
- Matière révisée
C'est la liste de ces objets révisions crées qui est passée entre les deux vues. Cela permet de faire des statistiques sur les révisions dans la vue SummaryScreen.
On peut passer à la vue SummaryScreen grâce à un drawer menu :

![drawer_menu](https://github.com/user-attachments/assets/aaa22a8c-eccd-4e80-b619-23e25d5ac393)

La vue SummaryScreen est alors la suivante : 

![recap_revisions](https://github.com/user-attachments/assets/04c754ad-642d-45a5-b71d-6e4d634142ac)

Le partage des données est fait grâce à un TimerViewModel partagé entre les vues et défini dans le MainActivity.

Le lien du GitHub est le suivant : https://github.com/ECN-infoSI/devmob-baillemont-perlin-rozan.git
