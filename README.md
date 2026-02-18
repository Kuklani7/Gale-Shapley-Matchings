# Gale-Shapley-Matchings
This is a repository that holds the solution to the popular problem of producing stable marriages from a set of preferences made by men and women but may also be extended to other domains.

# Exhaustive Stable Matching Finder for every Instance:
The program reads structured input in the form of multiple text files and extracts the input using file parsers in HashMaps and Arrays to then be used to solve the famous problem.
# How it works:
The program takes an input n for the number of men and women and the text files that represent their respective preferences. The data is then used to create all possible permutations and then a validator is used to detect blocking pairs and the instances with the blocking pair are subsequently removed. The remaining marriage instances remain stable.

# Features:
•  Custom input file parser
•  Use of multiple data structures for organized preference storage
•  Exhaustive permutation generation
•  Explicit stability verification
•  Outputs all valid stable matchings
