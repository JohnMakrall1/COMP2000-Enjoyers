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