# DisjointSets
App for sort list of disjoint sets

This application reads lines from the view file:
A1;B1;C1
A2;B2;C2
...
Further, it finds a set of unique lines and breaks it into DisjointSets according to the following criterion:
If two rows have matching non-blank values in one or more columns, they belong to the same group.
For example, the lines
111;123;222
200;123;100
300;;100
all belong to the same group, since the first two rows have the same value 123 in the second column, and the last two have the same value 100 in the third column

Output data to a separate *.txt file:

Group 1
line1
line2
line3
...
Group 2
line1
line2
line3

The output is sorted from the groups with the most rows to the least. Also, information about the number of groups and the time of the algorithm execution is displayed in the console.
After completing the task, you must send the number of received groups.
