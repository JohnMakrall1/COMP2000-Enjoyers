# Week 11

## Collectors
The Collectors class provides ready-made recipes for collect() that gather stream elements into a container, e.g. toList(), toSet() and toMap(keyFn, valueFn). toMap throws an exception on duplicate keys unless you pass a merge function as a third argument.

## Grouping
groupingBy sorts elements into a Map of lists keyed by a classifier, e.g. groupingBy(Dish::getType). Passing another collector as a second argument allows multi-level categorisation, e.g. groupingBy(Dish::getType, groupingBy(Dish::getCaloricLevel)).

## Summarising
averagingInt returns the average of an int property, while summarizingInt and summarizingDouble return a statistics object holding the count, sum, min, average and max in one pass. They are useful for building quick summaries of data, e.g. collect(summarizingInt
(Dish::getCalories)).

## Custom collectors
Collector.of() builds your own collector from a supplier (creates the container), an accumulator (adds an element), a combiner (merges two containers) and an optional finisher (converts the final result). This is used when no built-in collector fits, e.g. collecting into a custom data structure.

## Additional Notes
In this IRAT, I learned how to use Java Streams to filter, sort, and group data. I also learned about collectors, which can be used to collect data into lists and maps or count elements. I also learned the difference between stateful and stateless operations and that streams need a terminal operation to process their elements.
For our code, we implemented a collector in our updatePopulationCounts method which handled counting all the infected humans, the old method was:
 for (Human human : humans) {
                //     if (human.isInfected()) {
                //         infected++;
                //     } else {
                //         healthy++;
                //     }
                // }

We replaced it with  Map<Boolean, Long> infected = humans.stream().collect(Collectors.groupingBy(human->human.isInfected(),Collectors.counting()));

It does the same function but with a different implementation.

