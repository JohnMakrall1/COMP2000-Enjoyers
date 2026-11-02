# Week 13

Notes from at least two presentations by other students. 
One design idea or technique you saw that you wish you had tried. 

## TEAM B 
Drawf fortress, simulates different nations capturing tiles of neighboring landlots with specific tiles that have benefits i.e. tree tiles has benefits for nations that capture areas surrounding. Spoke about incomplete ideas and future design plans

Function: get playable border tiles → convert function into using lambdas and streams

Introducing a forest fire mechanic to utilise strategy design pattern (difference between normal forest and forest fire)

Forest fire mechanic burns neighbouring tree slots and injures nations within it

One design idea or technique we wish we had was…

## Team 2 
Basic predator prey program
Need to work on gui
Three types of animals
Dino pred
Caveman pred pray
Cat solely pray

Used animal as an abstract class with health, damage and hunger

Has states hunger state, wandering state etc

Basic idea of strategies - any given moment if a dinosaur is hungry it will start hunting, when it is full it will just wander around

Cat no damage

Has a volcano that erupts and every animal within the eruption radius dies

Cave is still in progress, if you go into a cave caveman get advantages like armour and weapons for hunting 

Second student from team 2
States and strategies everything related to animal collision


In the animal class states are handled, contains hunger and starvation logic, contains health logic
Animals start with a wandering state - just walks around

While in a wandering state, if the animal becomes hungry it will change to a hunting state which will then move to a corresponding prey (if it exists)

If an animal cannot eat in time, it moves to a dead state. If an animal does find food and eats it, it will move to a wandering state

Collision, combat system and reproduction system
If a collision is detected and combat rules exist between them, then those rules are executed
If a collision is detected, the hunger level is high enough and they are the same species, a new child is born

Combat system
Extensive and shows rules between all entities, cavemen, dinosaurs and cats

Parallel stream 
If a cavemen is being attacked, it can look for allies to help fight dinosaurs 

# Reflection
I learned how the Strategy pattern lets the simulation switch between direct and airborne transmission without putting both rules in one class. I am proud that the airborne mode uses each infected human’s recent movement trail, so random movement can create different patterns of spread. If I started again, I would coordinate branches and integrate shared interfaces earlier to reduce placeholders and make it easier to combine our work.
