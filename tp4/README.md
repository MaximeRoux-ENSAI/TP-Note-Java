# README

## Auteur

Maxime ROUX

--

# 2 Questions

## 2.1 Simulation setup

### 2.1.1 Set the probability that a person will arrive on the ground floor at each step to 0.65

On va dans le fichier de config (**application.yml**), et on change :
```yml
spawn-probability:
    ground: 0.65
```

### 2.1.2 Make sure that the number of floors and elevators can be easily configuredOn se rend compte que dans notre fichier Main on va chercher des variables (global) dans le fichier application.yml, :
```java
final int elevatorCapacity = Config.getInt("hotel.elevator.capacity");
```
On recopie la manière de faire, en créant deux variable : nbFloor, et nbElevator (Qu'on ajotue aussi dans le .yml).

Puis on va s'en servir pour initialise à l'aide de boucle dans notre main.

### 2.1.3 Ensure that this value is retrieved from elsewhere and do not hard-code it

On se ressert de ce qu'on a vu avant avec la config, en réajoutant :


```java
final int nbFloor = Config.getInt("hotel.floors.count");
```

Pour ne pas que ce soit coder de manière forte, mais bien que cela dépend de notre fichier de configuration.

### 2.1.4.1  In the Elevator class: Add the isFull() method

On fait tout simplement un test de la taille de la liste des passagers : 

```java
public boolean isFull(){
        return (this.passengers.size() >= this.capacity);
    }
```

### 2.1.4.2 Use it in another method of the same class

On remplace dans la fonction **loadPassengers**, dans le test de la boucle while :
```java
this.passengers.size() < this.capacity
```
Par ce qu'on a fait avant : 

```java
!this.isFull()
```

Qui permet de mettre de la logique au lieu de simplement un test.

## 2.2 Request an elevator

### 2.2.1 Implement new rules

On change la méthode **requestElevator** de Floor.

On va premièrement vérifier s'il n'y a pas un elevator qui va à cet étage (avec une simple boucle for).
En regardant :
```java
elevator.containDestination(this.number)
```
Si il y a un elevator qui y va on return (c'est bon).
Sinon on va chercher l'elevator avec le moins d'étage avant son arrivé (*leastBusy*).

### 2.2.2 Update the JavaDoc (to be done for each question if necessary)

Fait (avec un anglais approximatif mais fait...)

## 2.3 Target Floor

On va modifier la fonction **generateTargetFloor**, et lui donner en argument notre Floor de départ.

Maintenant, on va utiliser le `do {instruction} while (condition)` qui permet deffectuer une instruction puis après de tester si notre condition est respecté ou pas.
On va donc générer notre Floor d'arriver de manière aléatoire puis tester si on a générer **startFloor**, si c'est le cas on re génère sinon on sort puis return.

## 2.4 Unit test

### 2.4.1 Explain why it is complicated to test the isFull() method of the Elevator class.

Ce qui est compliqué c'est que dans notre déclaration `private List<Person> passengers`, la lsite est privée donc ôn ne peut pas la remplir directement. 
Car `isFull()` utilise cette lsite dans la condition.

### 2.4.2 Add an appropriate method and code unit tests for method isFull()

On va ajouter une méthode permettant d'ajouter des passagers **addPassenger** qui vérifie si c'est full et ajoute le passenger si c'est pas full.

Ensuite on fait les test un **isFull_EmptyElevator** qui est quand c'est vide si on a bien false et l'autre **isFull_FullElevator** qui test si quand c'est plein on a  bien true.

### 2.4.3 Create an run unit tests for method Elevator.addDestination()

On ajoute 3 test (pour être assez exhaustif) :

- *addDestination_shoudlAddFloor_whenFloorGiven* : Basique permet de tester si le Floor qu'on ajoute est bien pris en compte
- *addDestination_shouldNotDuplicated_WhenFloorAddedTwice* : Permet de vérifier qu'on ne duplique pas quand on rajoute el même floor (ça serait inutile)
- *addDestination_AddMultipleFloor* : Vérifie que tout se passe bien s'il y a plusieurs Floor ajouter.


## 2.5 Crazy Elevator

### 2.5.1 Create a class CrazyElevator & Update the appropriate method

On fait hériter (avec `extends`) la classe CrazyELevator de Elevator, on créer avec super un constructeur.

On modifie la fonction  **move()** en ajoutant un random pour choissir entre les 3 possibilité et on fait.
On a du ajouter dans **Elevator** des getter et settet : *getDestinationQueue()*, *setCurrentFloor()*, pour la modification

### 2.5.2 Update the appropriate method : Half the time, a crazyElevator may decide not to unload its passengers.

On va modifier la fonction **unloadPassenger()** et mettre un random, soit elle vide soit pas.

### 2.5.3 If it is full, it will send all its passengers into another dimension.


### 2.3.4 
On rajoute proprement comme au début et on choisit dans la congif .yml de mettre 1.
```java
for(int i = 0; i < nbCrazyElevator; i++){
            elevators.add(new CrazyElevator(nbElevator + i + 1, 0, elevatorCapacity));
        }
```
---