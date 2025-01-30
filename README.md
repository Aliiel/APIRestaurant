# APIRestaurant
📌 Application de Gestion des Commandes pour un Restaurant

🎯 Objectif

Développer une API REST permettant de gérer :

📌 Les clients

🍽️ Les plats du menu

🛒 Les commandes

🚀 Fonctionnalités attendues

---

L’application doit permettre :

👤 Ajouter, modifier et supprimer des clients

📜 Gérer un catalogue de plats

🛍️ Passer des commandes en associant des plats à un client

📂 Récupérer l’historique des commandes d’un client

---

🛠️ Contraintes techniques

🏗️ Spring Boot 3

🗄️ JPA & Hibernate

📦 DTOs pour structurer les échanges

⚠️ Gestion des erreurs

✅ Validation des données

---

📜 Exigences métier

🏷️ Un client peut passer plusieurs commandes.

🍽️ Une commande peut contenir plusieurs plats.

❌ Un plat ne peut pas être supprimé s'il est déjà dans une commande.

⚠️ Une commande ne peut pas être validée si elle ne contient aucun plat.

---

🏛️ Exigences techniques

🗃️ Utilisation d’une base de données relationnelle.

🔄 Définition correcte des relations entre les entités.

🚨 Gestion des erreurs dans les services et contrôleurs.

---

🎓 Objectif pour les étudiants

🏗️ Concevoir le modèle de données à partir des exigences

⚙️ Développer les entités, services et contrôleurs

🔍 Gérer les erreurs et validations