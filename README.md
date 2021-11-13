# package-picker

## Requirements
1. Java 11
2. Maven 3.6.3

## Run Test
1. mvn test

## Explanation
First, I read the file and convert it to objects - `List<PackageOfThing>`
Then, choose things to construct package.

Facade pattern is used for mapping file to objects. `PackagePopulator` hide complexities of generating packages and its 
validation process from `FileToPackageMapper`.

The algorithm I used here is Dynamic programming. I can't use greedy here, 
because, I can't pick thing partially. So, I used DP.
- First, sort things of a package
- Then, construct maximum cost matrix with minimum weight
- Then, traverse matrix from last cell to find out which things need to include.

After finding all things in package (optimised things need to send in package) and generate result with indexes.

####Data structure
- I used List to store packages and things inside package, also for holding results
- Also used matrix (2D int array) for calculating the maximum cost with minimum weight

## NB
As this project will be used in other project, so I did not provided any main method to run.
To check workflow, please run unit test.