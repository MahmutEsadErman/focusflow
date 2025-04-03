# FocusFlow Domain Model Documentation

This document provides an overview of the core domain model classes in the FocusFlow application.

## Class Overview

FocusFlow is built around three primary domain entities:

1. **Task** - Represents work items to be completed
2. **User** - Represents system users who can be assigned tasks
3. **Team** - Represents groups of users who work together

- A **Team** contains multiple **Users** and multiple **Tasks**
- A **User** can be assigned to a single **Team**
- A **Task** can be assigned to a single **User** and belongs to a single **Team**

## Task Class

### Purpose
Represents a work item or action item that needs to be completed.

### Properties
- `id` - Unique identifier for the task
- `title` - The name or title of the task
- `shortDescription` - Brief summary of the task
- `longDescription` - Detailed description of the task
- `dueDate` - Deadline for task completion
- `priority` - Enum indicating task importance (HIGH, MEDIUM, LOW)
- `status` - Enum indicating the current state (TODO, IN_PROGRESS, DONE, etc.)
- `assignedUser` - Reference to the user responsible for the task
- `team` - Reference to the team this task belongs to

### Key Methods
- `assignUser(User user)` - Assigns a user to this task
- `unassignUser()` - Removes user assignment
- `changePriority(TaskPriority newPriority)` - Updates task priority
- `changeStatus(TaskStatus newStatus)` - Updates task status
- `updateDetails(...)` - Updates task details (title, descriptions, due date)
- `isOverdue()` - Checks if the task has passed its due date

## Team Class

### Purpose
Represents a group of users who work together on a set of tasks.

### Properties
- `id` - Unique identifier for the team
- `name` - Unique name of the team
- `description` - Information about the team
- `createdAt` - Timestamp when the team was created
- `members` - Set of users belonging to this team
- `tasks` - Set of tasks assigned to this team

### Key Methods
- `addMember(User user)` - Adds a user to the team
- `removeMember(User user)` - Removes a user from the team
- `hasMember(User user)` - Checks if a user is a member
- `addTask(Task task)` - Adds a task to the team
- `removeTask(Task task)` - Removes a task from the team
- `getAllMembers()` - Returns all team members
- `getAllTasks()` - Returns all team tasks

## User Class

### Purpose
Represents a person who uses the system and can be assigned tasks.

### Properties
- `id` - Unique identifier for the user
- `email` - User's email address (unique)
- `password` - User's password (stored securely)
- `firstName` - User's first name
- `lastName` - User's last name
- `createdAt` - Timestamp when the user was registered
- `lastLogin` - Timestamp of the user's last login
- `team` - Reference to the team the user belongs to

### Key Methods
- `joinTeam(Team team)` - Adds the user to a team
- `leaveTeam()` - Removes the user from their current team
- `isPartOfTeam()` - Checks if the user is part of a team
- `assignTask(Task task)` - Assigns a task to this user
- `unassignTask(Task task)` - Unassigns a task from this user
- `isAssignedToTask(Task task)` - Checks if a task is assigned to this user
- `getAssignedTasks(Set<Task> allTasks)` - Returns all tasks assigned to this user
