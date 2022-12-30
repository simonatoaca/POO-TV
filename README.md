## POO TV
### Copyright 2022 Toaca Alexandra Simona

## Implementation

### Entry Point
```Streaming Service Class```
- Contains the ```start``` method which handles actions
- Stores the state of the StreamingService (current user, current page, etc)
- The getters and setters are ```static``` because this class needs to
be modified internally from multiple places in the program
- I did not make it a singleton because every time I create a new instance it
clears the old one. If I made it a Singleton I would have needed a
```clear()``` method

### Storing the input
```Database Class```
- This is implemented as a **Singleton** to be accessed from anywhere in the
program
- Contains the users registered and the movies available on the platform

### I/O
```InputHandler Class```
- Loads the JSON text input into an Input object
- Loads the users and movies from Input into the Database
```loadInputIntoDatabase```
- the ```getActions``` method uses an **ActionFactory** to
return the actions as proper objects -> used for the Visitor pattern
explained later

```OutputWriter Class```
- Writes the output to a file in JSON format
- Adds a new ```objectNode``` (containing an ```Output``` object)
to an output ```ArrayNode```
- The ```ArrayNode``` is written to the output file as JSON at the
end of the program

### Actions and Pages
- There are classes for every type of page and action,
each having a base class Page or Action
- An action is permitted only on certain pages, an invalid action
producing an error -> I used a modified **Visitor** pattern to have a default
behavior for the actions (error) and then overriding it for the cases in which
the action is permitted.
- The construction in the entry point is as follows:
```currentPage.accept(action)```
- Pages are _visited_ by Actions
- The correct behavior of the action is selected at runtime depending on
the current page
- Also, an action has access to the page it was called on without checking
what page the user is on (for example, in ```ChangePageAction```, we check
if the next page is a valid sub page of the current page by using
```page.getSubPages()```, referring automatically to the page the action was
called on).

### Notification system
- used the **Observer** design pattern to notify users when a movie is
added to/deleted from the database.
- The users are the **observers** and the database is **observable**
- By subscribing to a genre, a user is added as an observer and the ```update```
function handles whether the notification will be relevant or not to the user
  (the genre of the movie is one the user subscribed to)

### Recommendations
```RecommendationGenerator class```
- computes the number of likes that every movie genre got
- creates a list of the genres in descending order by number of likes
- searches through the unwatched movies in the database for a recommendation
- returns the notification containing the recommendation for the user
