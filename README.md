##Lackbase (AGIW project)
This repository contains a project made for the [Web Informations Management course](https://sites.google.com/site/roma3agiw/) at [Roma Tre University](http://www.ingegneria.uniroma3.it/).

###Brief
[Freebase.com](http://www.freebase.com) is a huge knowledge base, but is far from complete. We decided to extend a particular part of it:
* Nationality of the relation: Soccer Player, people.person.nationality, Nationality
* Stadium of the relation: Soccer Team, sports.sports_team.arena_stadium, Stadium

To make this happen we **crawled** a famous and big website about this topic: Transfermarkt.com. Using [XPath](https://en.wikipedia.org/wiki/XPath) queries we **iteratively and automatically** selected the data we wanted and we used it to extend the Freebase relations!

###Results
* **11%** of new Players -> Nationality relations
* **13%** of new Team -> Stadium relations

###Authors
* Davide Violante
* Michele D'Antimi
* Edoardo Carra
