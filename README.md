# java17-tree

[![Continuous Integration](https://github.com/benoitpas/java17-tree/actions/workflows/main.yml/badge.svg)](https://github.com/benoitpas/java17-tree/actions/workflows/main.yml)

Project to test implementation of a function that adds an id to a tree using the latest java features.

The main change compared to the [java 15 project](https://github.com/benoitpas/java15-tree) is:
* using sealed classes (it was already available in Java15 preview)
* using the 'switch' (from java 17 preview)[https://docs.oracle.com/en/java/javase/17/language/pattern-matching-switch-expressions-and-statements.html]
* replacement of [AbstractMap.SimpleEntry](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/util/AbstractMap.SimpleEntry.html) which was used as a generic tuple by a record

The code is starting to look a bit more functional but the limited type inference makes the function signatures look not very elegant.