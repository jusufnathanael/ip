# User Guide

Duke is a Personal Assistant Chatbot that helps a person to organise and keep track of various tasks,
optimized for use via a Command Line Interface (CLI).
The name Duke was chosen in honor of Duke, the Java Mascot.

- Quick start
- Features
    - [Listing all tasks](#listing-all-tasks-list): `list`
    - [Adding a new todo](#adding-a-new-todo-todo): `todo`
    - [Adding a new deadline](#adding-a-new-deadline-deadline): `deadline`
    - [Adding a new event](#adding-a-new-event-event): `event`
    - [Finding tasks by a keyword](#finding-tasks-by-a-keyword-find): `find`
    - [Marking as done](#marking-as-done-done): `done`
    - [Deleting a task](#deleting-a-task-delete): `delete`
    - [Clearing all tasks](#clearing-all-tasks-clear): `clear`
    - [Exiting the program](#exiting-the-program-bye): `bye`
- Command Summary

<br>

## Quick Start

1. Ensure that you have `Java 11` installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/jusufnathanael/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder.
4. Launch the file using the `java -jar` command (`java -jar duke.jar`).
5. Refer to the Features section below for details on what type of commands you can enter.

<br>

## Features

#### Listing all tasks: `list` 
Shows a list of all current tasks.

Format: `list`

Output:
```
1. [T][✘] Create Zoom account
```

<br>

#### Adding a new todo: `todo`
Adds a new todo item to the task list.
Format: `todo <description>`

Examples:
```
todo Do CS2113T assignment 1
todo Prepare for ST2334 final
```
Output:
```
Got it. I've added this task:
    [T][✘] Do CS2113T assignment 1
Now you have 1 task in your list.
```

<br>

#### Adding a new deadline: `deadline`
Adds a new deadline to the task list.

Format: `deadline <description> by: YYYY-MM-DD`

Examples:
```
deadline Finish CS2101 reflection by: 2020-10-09
deadline Finish CS2113T team project by: 2020-11-10
```
Output:
```
Got it. I've added this task:
    [D][✘] Finish CS2101 reflection by: 2020-10-09
Now you have 3 task in your list.
```

<br>

#### Adding a new event: `event`
Adds a new event to the task list.

Format: `event <description> at: YYYY-MM-DD`

Examples:
```
event Watch concert in YST at: 2020-10-23
event CS2113T final exam at: 2020-12-01 
```
Output:
```
Got it. I've added this task:
    [E][✘] Watch concert in YST at: 2020-10-23
Now you have 5 task in your list.
```

<br>

#### Finding tasks by a keyword: `find`
Finds tasks that contain any of the given keywords.

Format: `find <keyword>`
- Keywords are case-sensitive.


Examples:
```
find CS2101
find 2020-10
```
Output:
```
We found 2 items in the list:
1. [D][✘] Finish CS2113T team project by: 2020-11-10
2. [E][✘] CS2113T final exam at: 2020-12-01 
```

<br>

#### Marking as done: `done`
Marks a certain task as done given the index number.

Format: `done <index>`

Examples:
```
done 1 (mark the first task as done)
done 2 (mark the second task as done)
```
Output:
```
Nice! I've marked this task as done:
[✓] Finish CS2101 reflection by: 2020-10-09
```

<br>

#### Deleting a task: `delete`
Deletes a task given its index number.

Format: `delete <index>`

Examples:
```
delete 1 (delete the current first task)
delete 2 (delete the current second task)
```
Output:
```
Got it. I've removed this task:
    [T][✓] Finish CS2101 reflection by: 2020-10-09
Now you have 5 task in your list.
```

<br>

#### Clearing all tasks: `clear`
Clears all the tasks inside the list.

Format: `clear`

Output:
```
List cleared! Currently there are no tasks in the list.
```

<br>

#### Exiting the program: `bye`
Exits the program.

Format: `bye`

Output:
```
Bye. Hope to see you again soon!
```
<br><br>

## Command Summary

Command | Format, Examples
------- | ----------------
Bye | `bye`
Clear | `clear`
Deadline | `deadline <description> by: YYYY-MM-DD`, e.g. `deadline Finish CS1010 Assignment 2 by: 2020-10-10`
Delete | `delete <index>`, e.g. `delete 1`
Done | `done <index>`, e.g. `done 2`
Event | `event <description> at: YYYY-MM-DD`, e.g. `event CS1010 Exam at: 2020-11-25`
Find | `find <keyword>`, e.g. `find exam`
List | `list`
Todo | `todo <description>`, e.g. `todo Prepare for CS1010 Exam`

<br>

_Reference: https://se-education.org/addressbook-level3/UserGuide.html_

<br>
