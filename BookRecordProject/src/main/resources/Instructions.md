# CS112 Group Assessment Specification — 2026

**Submission deadline:** 17:00 on Monday 6th April 2026

This assessment is completed as a group. Only one submission is required per group. The
project is marked out of 10 and contributes 4% of your overall grade.

---

## Overview

In groups of four, create a small Java console program to manage book records. The program
must allow the user to:

- Add a book record
- Display all book records
- Save records to a file
- Load records from a file

Each group member should primarily work on one class. If your group has only three
members, you do not need to complete section 4 (Main class).

## Provided interface

You are provided with the following interface which your records must implement:

```java
import java.io.Serializable;

public interface DisplayableRecord extends Serializable {
    String getId();
    void generateID();
    void getDisplayText();
}
```

This interface represents an object that:

- can be uniquely identified
- can produce text suitable for displaying to a user
- can be serialized to a file

---

## Required classes

You must provide the following classes (one file each):

1. `BookRecord`
2. `RecordManager`
3. `FileHandler`
4. `Main` (not required if your group has 3 members)

Below each class's responsibilities and required members are described.

### 1. BookRecord

The `BookRecord` class must implement the `DisplayableRecord` interface.

Purpose: Represents a single book record.

Required fields:

- `private String id;`
- `private String title;`
- `private String author;`
- `private int year;`

Required constructor:

```java
public BookRecord(String title, String author, int year)
```

Constructor rules:

- Should throw `IllegalArgumentException` if:
  - `title` is empty
  - `author` is empty
  - `year` is less than 0 or greater than 2026
- Should generate the `id` using `generateID()`.

ID generation rule:

The `id` should consist of the first 3 characters of the book title, followed by the year,
followed by the first 3 characters of the author name. Example:

Title: "The Hobbit"; Author: "J.R.R Tolkien"; Year: 1937 → id: `The1937J.R`

Required methods (all overrides where applicable):

- `public String getId()` — returns the ID
- `public void generateID()` — generates and sets the `id` using the rule above
- `public String toString()` — returns formatted display text, e.g.:
  `B101 - The Hobbit by J.R.R. Tolkien (1937)`
- `public void getDisplayText()` — prints out the formatted display text
- `public boolean equals(Object o)` — returns `true` if `o` is a `BookRecord` and the
  `author`, `title` and `id` match; otherwise `false`

---

### 2. RecordManager

Purpose: Stores and manages all book records in memory.

Required field:

- `private ArrayList<DisplayableRecord> records;`

Required constructor:

```java
public RecordManager()
```

Constructor rule: initialize `records` to an empty `ArrayList`.

Required methods:

- `public void addRecord(DisplayableRecord record)`
  - Adds a record and throws `IllegalArgumentException` if a duplicate record exists.
- `public boolean removeRecord(String id)`
  - Removes the record with the matching ID. Returns `true` if removed, `false` if not found.
- `public ArrayList<DisplayableRecord> getAllRecords()` — returns all stored records.
- `public DisplayableRecord findRecordById(String id)` — returns the record if found, else `null`.
- `public void setRecords(ArrayList<DisplayableRecord> records)` — replaces the current list.
- `public void displayAllRecords()` — prints details for every `DisplayableRecord` in `records`.

---

### 3. FileHandler

Purpose: Saves and loads records using Java serialization.

Required field:

- `private String fileName;`

Required constructor:

```java
public FileHandler(String fileName)
```

Constructor rule: set `fileName` to the provided string.

Required methods (in addition to standard getter/setter for `fileName`):

- `public void saveRecords(ArrayList<DisplayableRecord> records)`
  - Saves the provided `ArrayList` to a file named by `fileName` using `ObjectOutputStream`.
- `public ArrayList<DisplayableRecord> loadRecords()`
  - Loads and returns an `ArrayList` from the file named by `fileName` using `ObjectInputStream`.
  - Any exceptions during loading should be caught here; display an error message and return `null`.
- `public void exportReadableRecords(ArrayList<DisplayableRecord> records, String readableFileName)`
  - Writes the details of each record to `readableFileName` in a human-readable text format.

---

### 4. Main

Purpose: Contains the main program and menu.

Required method:

```java
public static void main(String[] args)
```

Main method requirements:

- Create a `Scanner` for reading user input.
- Create a `RecordManager` instance.
- Create a `FileHandler` instance (use an appropriate filename).
- Display a menu repeatedly until the user selects Exit.
- Read and validate the user's menu choice; show an error message for invalid choices.
- Invoke appropriate actions for each menu choice.
- Handle exceptions that may occur during user input or execution.

Design note: keep `main` clear by delegating work to static helper methods that implement
menu logic and actions.

Required menu (present to the user):

1. Add Book
2. Display Books
3. Save Records
4. Load Records
5. Exit

When saving/loading records, prompt the user to specify the filename to use.

---

## Example program run (simplified)

```
1. Add Book
2. Display Books
3. Save Records
4. Load Records
5. Exit

Enter choice: 1
Enter title: The Hobbit
Enter author: J.R.R. Tolkien
Enter year: 1937
Record with ID The1937J.R added successfully.
```

---

## Marking

Each project section is marked out of 10: `BookRecord`, `RecordManager`, `FileHandler` and
`Main`. All group members receive the average of the section marks unless a member has not
made an adequate contribution.

If a member’s section is significantly lower (incomplete/incorrect/missing) that mark will:

- not be included in the group average, and
- that student will receive this individual mark instead.

This ensures strong contributions are recognised and under-performance treated fairly.

You are encouraged to collaborate and discuss ideas with your group members while working on
your individual sections.

---

## Submission

Submit a zip file containing the following files: `BookRecord.java`, `RecordManager.java`,
`FileHandler.java` and `Main.java`. (If you have 3 members you do not need to submit `Main.java`.)

Only one submission is required for the group. Deadline: 17:00 on Monday 6th April 2026.

