CS542 Design Patterns
Spring 2016
PROJECT <5> README FILE

Due Date: <PROJECT DUE DATE, IN FORMAT: Sunday, May 8, 2016>
Submission Date: <DATE YOU SUBMIT, IN FORMAT: Sunday, May 8, 2016>
Grace Period Used This Project: 0 Days
Grace Period Remaining: 0 Days
Author(s): RISHI SANAS
e-mail(s): rsanas1@binghamton.edu


PURPOSE:

[
 The purpose of this assignment is to create a generic library to serialize and deserialize objects
]

PERCENT COMPLETE:

[
  Couldnt apply Strategy Pattern successfully.(read method)
]




FILES:

[
  MyAllTypesFirst.java
  MyAllTypesSecond.java
  StoreRestoreHandler.java
]

SAMPLE OUTPUT:

[
  	MyAllTypesFirst [myInt=2, myLong=4, myString=Hey0, myBool=true]
	MyAllTypesSecond [myDoubleT=3.45, myFloatT=4.65, myShortT=54, myCharT=a]
	MyAllTypesFirst [myInt=14, myLong=48, myString=Hey1, myBool=true]
	MyAllTypesSecond [myDoubleT=3.45, myFloatT=4.65, myShortT=55, myCharT=b]
	MyAllTypesFirst [myInt=26, myLong=92, myString=Hey2, myBool=true]
	MyAllTypesSecond [myDoubleT=3.45, myFloatT=4.65, myShortT=56, myCharT=c]
	
	
	0 mismatched objects
]

TO COMPILE:

[
  On terminal,
1) Go to studentRecordsBackup folder.
2) Type 'ant -buildfile src/build.xml clean' and press Enter.
3) Type 'ant -buildfile src/build.xml all' to compile and press Enter.

TO RUN:

1) Type 'ant -buildfile src/build.xml run -Darg0=<mode> -Darg1=<number of objects> -Darg2=<output file>  ' to compile and press Enter


  Just extract the files and then do a "make".
]

