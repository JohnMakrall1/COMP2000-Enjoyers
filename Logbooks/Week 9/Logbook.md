# Week 9 Logbook

## Lambda expressions
Anonymous functions that can be stored in variables or passed to methods like any other value, e.g. s -> s.length(). They are written as parameters, an arrow and a body, and replace bulky anonymous classes.

## Functional Interfaces
Interfaces with exactly one abstract method, which give a lambda its type. java.util.function provides common ones: Predicate (tests a value), Function (transforms a value), Consumer (uses a value) and Supplier (creates a value).

## Method references
A ClassName::methodName shorthand for a lambda that only calls an existing method, e.g. String::length instead of s -> s.length(). They can refer to static methods, instance methods or constructors (ClassName::new).

## Behaviour parameterisation
Passing behaviour into a method as an argument instead of hard-coding it, so one method can handle many cases. For example, a single filter(list, predicate) method can filter by colour, weight or anything else depending on the lambda passed in.

