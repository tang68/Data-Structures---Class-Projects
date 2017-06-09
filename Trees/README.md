Purpose: Understand the structure and application of a binary search tree.

Task Description:

Your program should read from the standard input a sequence of integer values, with each value

separated by a space. Your task is to:

• Build a binary search tree using these values in the order they are entered.

• Print 3 traversals: pre-, in-, and post-order.

• Allow the user to insert/delete a value. Once a new tree is generated, print it in-order.

• Find predecessor of a given value. The predecessor is the node that appears right before

the given value in an in-order traversal.

• Find successor of a given value. The successor is the node that appears right after the given

value in an in-order traversal.

In your BST implementation, the add and delete methods must be implemented using recursion.

You will lose major points for using a non-recursive implementation.

Note that no duplicates are allowed in this BST. Your program should use an interactive interface

with the format shown below (the user inputs are underlined):

Please enter the initial sequence of values:

51 29 68 90 36 40 22 59 44 99 77 60 27 83 15 75 3

Pre-order: X X X ... X

In-order: X X X ... X

Post-order: X X X ... X

Command? H

 I Insert a value
 
 D Delete a value
 
 P Find predecessor
 
 S Find successor
 
 E Exit the program
 
 H Display this message
 
