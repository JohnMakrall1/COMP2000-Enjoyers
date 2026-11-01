# Week 10

## Stream pipelines
A pipeline has three parts: a source (e.g. list.stream()), zero or more intermediate operations that return a new stream (e.g. filter, map), and one terminal operation that produces a result (e.g. collect, count). This lets you describe what to do with the data rather than writing the loop yourself.

## Lazy evaluation
Intermediate operations don't run when they are called; they are only recorded until a terminal operation triggers the pipeline. This allows optimisations such as short-circuiting, e.g. filter(...).findFirst() stops as soon as a match is found.

## Common operations
filter keeps elements matching a predicate, map transforms each element, flatMap flattens nested streams into one, and sorted orders elements. reduce combines all elements into a single value, e.g. reduce(0, Integer::sum).

## Primitive streams
IntStream, LongStream and DoubleStream work directly with primitives, avoiding the cost of boxing to Integer and similar. They add numeric methods like sum(), average() and range(1, 10), and convert from object streams with mapToInt (or back with boxed()).