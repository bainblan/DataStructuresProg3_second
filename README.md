# DataStructuresProg3
3rd Programming Assignment

Baines Blanton and Caleb Cort collaborated on this project.

Baines did the BinarySearchTree.java and NodeType.java classes.
Caleb did the Driver class.

Baines.Blanton@uga.edu
Caleb.Cort@uga.edu
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
getSingleParent Pseudocode:

void getSingleParent() 
    call the helper recursive method, pass in the root

void getSingleParentHelper(Node root) ... T(n)
    if root exists
        if root has only one child, then print the child's value ... T(1)
        else 
            recursively check left side ... T(n/2)
            recursively check right side ... T(n/2)

Recurrence Relation: T(n) = 2T(n/2) + C

Explanation: The recursive method's base case is O(1) because checking a condition and printing takes a constant amount of time
The general case runs the operation down the root nodes left side, then right side, so assuming the tree is somewhat balanced,
we can assume that the length of the left side and right side are each half the length of the whole tree.

Master Theorem: a = 2, b = 2, d = 0. 2 > 2^0, so a > b^d; therefore, O(n^Log_2(2)) = O(n^1) = O(n)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
getNumLeafNodes Pseudocode:

int getNumLeafNodes() 
    call recursive helper and pass it the root node

int getNumLeafNodesHelper(Node root) ... T(n)
    if root is null, return 0 ... T(1)
    if root has no children, return 1 ... T(1)
    else return the recursive results of the left child plus the recursive results of the right child ... T(n/2) + T(n/2)

Recurrence Relation: T(n) = 2T(n/2) + C

Explanation: This works essentially the same way as the getSingleParent method in that the only big change
is that we're checking if it has no children instead of checking if it has one child. It visits every node 
once and does a constant amount of work per node, such as returning 1 or 0.

Master Theorem: a = 2, b = 2, d = 0. 2 > 2^0, so a > b^d; therefore, O(n^Log_2(2)) = O(n^1) = O(n)
--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
getCousins Pseudocode:

void getCousins(Node node) 
    call recursive helper, pass it the root node, the node we want the cousins for, and the level of the node

void getCousinsHelper(Node root, Node node, int level)
    if root is null or we're on the current level, then return ... T(1)
    if we're on the second level, then ... T(1)
        if the root's left child or right child equals node, then return ... T(1)
        if the left child exists, print its value ... T(1)
        if the right child exists, print its value ... T(1)
    else, 
        run the method down the left subtree ... T(n/2)
        run the method down the right subtree ... T(n/2)

int getLevel(Node root, Node node, int level) 
    if root is null, return 0 ... T(1)
    if root equals node, return level ... T(1)
    search the left subtree for that node, put its level in a variable ... T(n/2)
    if the node wasn't in left subtree (level return 0), ... T(1)
    then check the right subtree ... T(n/2)

Recurrence Relation: T(n) = 2T(n/2) + C

Explanation: This function uses a helper function to find the level of the desired node,
so it first runs a 2T(n/2) + O(1) operation because it has to check both sides of the 
whole tree at the worst, in order to find the node and return its level.
Then, the getCousinsHelper method does a similar thing, but it decrements the level;
thereby, going up the tree to check for cousins, and once it gets back to the original
root node, it's time to stop. 

Master Theorem: a = 2, b = 2, d = 0. 2 > 2^0, so a > b^d; therefore, O(n^Log_2(2)) = O(n^1) = O(n)

If we have one operation that runs in O(n) time, then the next runs in O(n) time, adding them together still gives you O(n) time complexity.




