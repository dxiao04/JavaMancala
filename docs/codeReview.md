## Code Review
#### Are the variable and method names meaningful and descriptive? Give specific examples to support your observation. How could the variable and method names be improved?
The variable and method names in AI mancala are meaningful and descriptive. For example, displayBoard(), makeMove(), and determineWinner() all do exactly what their names suggest.
#### Does the code follow coding conventions and formatting standards? Has it made appropriate use of includes? Are static members used properly? Give specific examples of how you would improve the coding conventions used.
Static members are used properly (only used for main) and the Scanner class is properly included (the code specifically includes java.util.Scanner and not the entire util package).
#### Are the classes properly encapsulated? Are member variables private? Are accessor and mutator methods used? How could you improve the encapsulation of this code?
While member variables are private, every method is public. Accessors and mutators are used. Since every file used in in AI mancala is in the same package, all methods (except main) can be made package private at the very least. 
#### Is there any duplication of code in this project? Are there methods that do essentially the same thing, or parts of the same thing that could be made into smaller methods?
There is no duplication of code in the AI mancala.
#### Does each class and method have a single, obvious purpose or responsibility? Are there any long methods that should be broken up into smaller methods? Give specific examples of how you could improve the code with respect to responsibilities.
Some classes and methods represent multiple things/have multiple responsibilities. For example, the Board class not only represents a "board" (an arrangement of pits and stores), but also the pits and stores themselves in the form of an int array. makeMove() also handles stone capturing in addition to regular stone distribution. For better modularity and testing in isolation, Pit and Store classes should be added and makeMove() should call a separate function for stone capturing.
#### Functionality and Correctness
The AI mancala is mostly functional and correct. However, it does not implement the extra turn rule, and more crucially, the isGameOver method. To check for the end of a game, its faulty loop checks if the entire board is empty and only returns "true" if so. 
