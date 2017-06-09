Purpose: 
Implement a graph data structure for a practical application.



Task Description:

Input: Two files - one contains city data and the other contains road data.

• city.dat: This file contains information about cities, where each line has 5 attributes: City Number, City_Code (2 letters), Full_City_Name, Population, and Elevation.

• road.dat: This file contains information about roads, 
  where each line has 3 attributes: From_City, To_City, and Distance. Note that all roads are assumed to be one-way.
  
Output: A menu driven system which has the following options.

• Read the original data files and store the data to appropriate data structures.

• Let the user of this program enter a City Code and your program should print out the city information (the whole record).

• Find the connection between two cities.

• The user will be asked to enter two City Codes. The program finds the shortest distance between the two cities.

• Insert a road (edge) between two cities

• The user will be asked to enter two City Codes and its Distance. 

  Note that if a pair of City Codes already exists or if the City Code doesn't exist, print out a warning message.
  
• Delete a road (edge)

• The user will be asked to enter two City Codes for a road. Note that if the road entered doesn't exist, print out a warning message.

• Exit.


Your program should resemble the following output (the user inputs are underlined):
% java Project3
Command? H
Q Query the city information by entering the city code.
D Find the minimum distance between two cities.
I Insert a road by entering two city codes and distance.
R Remove an existing road by entering two city codes.
H Display this message.
E Exit.
Command? Q

Programming Guides
Create a Java Digraph (directed graph) class to store the city and road information.
Use Dijkstra's algorithm discussed in class for finding the shortest distances between cities.
