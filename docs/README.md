# User Guide

Duke is a Personal Assistant Chatbot that helps a person to organise and keep track of various tasks,
optimized for use via a Command Line Interface (CLI).
The name Duke was chosen in honor of Duke, the Java Mascot.

- Quick start
- Features
    - [Listing all tasks](#listing-all-tasks-list): `list`
    - Adding a new todo: `todo`
    - Adding a new deadline: `deadline`
    - Adding a new event: `event`
    - Finding tasks by a keyword: `find`
    - Marking as done: `done`
    - Deleting a task: `delete`
    - Clearing all tasks: `clear`
    - Exiting the program: `bye`
- Command Summary

<br><br>

## Quick Start

1. Ensure that you have `Java 11` installed in your Computer.
2. Download the latest duke.jar from [here](https://github.com/jusufnathanael/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the home folder.
4. Launch the file using the `java -jar` command (`java -jar duke.jar`).
5. Refer to the Features section below for details on what type of commands you can enter.

<br><br>

## Features

<br>

#### Listing all tasks: `list` 
Shows a list of all current tasks.
Format: `list`

<br>

#### Adding a new todo: `todo`
Adds a new todo item to the task list.
Format: `todo <description>`

Examples:
- todo Do CS2113T assignment 1
- todo Prepare for ST2334 final

<br>

#### Adding a new deadline: `deadline`
Adds a new deadline to the task list.

Format: `deadline <description> by: <time in YYYY-MM-DD>`

Examples:
- deadline Finish CS2101 reflection by: 2020-10-09
- deadline Finish CS2113T team project by: 2020-11-10

<br>

#### Adding a new event: `event`
Adds a new event to the task list.

Format: `deadline <description> at: <time in YYYY-MM-DD>`

Examples:
- event Watch concert in YST at: 2020-10-23
- event CS1010 final exam at: 2020-11-24 

<br>

#### Finding tasks by a keyword: `find`
Finds tasks that contain any of the given keywords.

Format: `find <keyword>`
- Keywords are case-sensitive.


Examples:
- find exam
- find 2020-10

<br>

#### Marking as done: `done`
Marks a certain task as done given the index number.

Format: `done <number>`

Examples:
- done 1 (mark the first task as done)
- done 2 (mark the second task as done)

<br>

#### Deleting a task: `delete`
Deletes a task given its index number.

Format: `delete <number>`

Examples:
- delete 1 (delete the current first task)
- delete 2 (delete the current second task)

<br>

#### Clearing all tasks: `clear`
Clears all the tasks inside the list.

Format: `clear`

<br>

#### Exiting the program: `bye`
Exits the program.

Format: `bye`

<br>


