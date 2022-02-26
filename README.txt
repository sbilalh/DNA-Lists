CSC 172 - Lab 4
26 February 2022

Bilal Hussain
shussa11@u.rochester.edu

Sammy Potter
spott14@u.rochester.edu

Files:

Node.java
* Node contains the basic implementation of a Generic Linked List Node. Each node has a value, and a pointer to the next node, both of which can be accessed/mutated by the relevant methods.

LList.java
* LList contains the implementation for a Generic Linked List, and utilizes the Node.java file for the implementation. The implementation has all the relevant methods for a linked list.

Sequence.java
* Sequence is an object that represents a single sequence of DNA, RNA, or an EMPTY sequence. It stores the type of the sequence, as well as a Linked List that stores the actual sequence. Sequence contains relevant methods for retrieving the sequence and its type.

DNAList.java
* DNAList utilizes an array of type Sequence to store each strand of DNA/RNA. Arguments provided at runtime provide the length of the array. Commands are read from a text file provided at runtime and applied to the DNAList.