# Application - BilletsEnLigne

* *Author* : [Giovanni Victorette](https://github.com/victorette)
* *Level* : Intermediate
* *Technologies* : Java EE 6 (XSTREAM, Bean Validation 1.0, JSF 2.0, Primefaces)
* *Application Servers* : Jetty 9
* *Summary* : Projet de session - ULaval

# Styles architecturaux présents dans l’application

Au niveau de nos vues (la couche interfaces + fichiers .xhtml), nous utilisons le style Model-View- Controller (MVC), qui est relativement standard. Nos vues (fichiers .xhtml) sont sans logique, mise à part celle liée à l’affichage, et nos beans (dans le package : ca.ulaval.ift6003.interfaces.beans) agissent comme contrôleurs. Ils sont, eux aussi, très limités en terme de logique. Ils servent plutôt à contacter les façades présentes dans la couche application pour effectuer des requêtes pour les données sous forme de DTO (Data Transfer Object), ou pour envoyer des requêtes CRUD (Create-Read-Update-Delete), ou autres. Pour ce qui est du composant modèle du MVC, nous utilisons des DTO (ou viewmodels) en plus d’assembleurs de DTO, ainsi que des UIExceptions, Constantes et énumérations.
Le coeur de notre application est sous forme d’une architecture guidée par le Domain-driven-design, fortement inspirée par DDDSamples.
