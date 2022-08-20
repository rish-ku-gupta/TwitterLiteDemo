# Versions used in this Scala IML project

Scala SDK 2.12

Scalatest 2.12

Oracle openjdk 1.8

# Installation
Just import the repo in any IDE like vscode or intellij.

Use SSH or HTTPS mode while importing as per your GitHub settings.

# Steps and Guidelines on using the application
```bash
For posting a message, use the command format: <user name> -> <message>
For reading a user's messages, use the command format: <user name>
For following a user, use the command format: <user name> follows <another user name>
For looking at a user's wall, use the command format: <user name> wall
Type exit to close the session anytime.
```

# Sample Test Commands

```bash
Alice -> I love the weather today
Bob -> Damn! We lost!
Bob -> Good game though.
Bob
Charlie -> I'm in New York today! Anyone want to have a coffee?
Charlie follows Alice
Charlie wall
Alice
Charlie follows Bob
Charlie wall
Bob -> Nice meeting you all!
Alice -> same here Bob!
Charlie wall
```

# Sample Output
```bash
Welcome to TwitterLite!! Starting New Session...
Session Started! Please follow these usage instructions:
1. For posting a message, use the command format: <user name> -> <message>
2. For reading a user's messages, use the command format: <user name>
3. For following a user, use the command format: <user name> follows <another user name>
4. For looking at a user's wall, use the command format: <user name> wall
5. Type exit to close the session anytime.
Alice -> I love the weather today
This user does not exist. A new user with username Alice has now been created.
Bob -> Damn! We lost!
This user does not exist. A new user with username Bob has now been created.
Bob -> Good game though.
Charlie -> I'm in New York today! Anyone want to have a coffee?
This user does not exist. A new user with username Charlie has now been created.
Charlie follows Alice
Charlie has successfully started following Alice
Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (13 seconds ago)
Alice - I love the weather today (31 seconds ago)
Charlie follows Bob
Charlie has successfully started following Bob
Bob -> Nice meeting you all!
Charlie
I'm in New York today! Anyone want to have a coffee? (1 minute ago)
Alice -> same here Bob!
Charlie wall
Charlie - I'm in New York today! Anyone want to have a coffee? (2 minutes ago)
Bob - Nice meeting you all! (49 seconds ago)
Bob - Good game though. (2 minutes ago)
Bob - Damn! We lost! (2 minutes ago)
Alice - same here Bob! (7 seconds ago)
Alice - I love the weather today (2 minutes ago)
exit

Process finished with exit code 0
```
