# package-picker
## Problem 
You want to send your friend a package with different things. 

Each thing you put inside the package has such parameters as index number, weight and cost. The 
package has a weight limit. Your goal is to determine which things to put into the package so that the 
total weight is less than or equal to the package limit and the total cost is as large as possible. 

You would prefer to send a package which weighs less in case there is more than one package with the 
same price.

##### Input	sample
Your API should accept as its first argument a path to a filename. The input file contains several lines. 
Each line is one test case. 

Each line contains the weight that the package can take (before the colon) and the list of items you need 
to choose. Each item is enclosed in parentheses where the 1st number is a item’s index number, the 2nd
is its weight and the 3rd is its cost. E.g.
```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
8 : (1,15.3,€34)
75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
```
##### Output sample
For each set of items that you put into a package provide a new row in the output string (items’ index 
numbers are separated by comma). E.g. 
```
4
-
2,7
8,9
```

##### Constraints
You should write a class com.arifng.packer.Packer with a static API method named pack. This 
method accepts the absolute path to a test file as a String. The test file will be in UTF-8 format. The pack 
method returns the solution as a String.

Your method should throw an com.arifng.exception.APIException if incorrect parameters are being 
passed. Therefore your signature should look like 
```public static String pack(String filePath) throws APIException```

Additional constraints:
1. Max weight that a package can take is ≤ 100
2. There might be up to 15 items you need to choose from
3. Max weight and cost of an item is ≤ 100

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

#### Data structure
- I used List to store packages and things inside package, also for holding results
- Also used matrix (2D int array) for calculating the maximum cost with minimum weight

## NB
As this project will be used in other project, so I did not provided any main method to run.
To check workflow, please run unit test.