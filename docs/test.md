# Unit Test Documentation

## TaskTest Class

### Overview
The `TaskTest` class contains unit tests for the `Task` class, ensuring its methods function correctly using JUnit 5.

### Test Methods

#### 1. testTaskCreationWithValidData
- **Description:** Verifies `Task` creation with valid data.
- **Assertions:**
    - Task object is not null.
    - Title, short description, and long description match expected values.

#### 2. testAssignUser
- **Description:** Checks user assignment to a task.
- **Assertions:**
    - Assigned user matches expected user.

#### 3. testUnassignUser
- **Description:** Ensures user unassignment from a task.
- **Assertions:**
    - Task’s assigned user is null after unassigning.

#### 4. testChangePriority
- **Description:** Validates priority change in a task.
- **Assertions:**
    - Priority updates correctly.

#### 5. testChangeStatus
- **Description:** Ensures task status modification.
- **Assertions:**
    - Status changes as expected.

#### 6. testUpdateDetails
- **Description:** Checks if task details update correctly.
- **Assertions:**
    - Title, short description, long description, and due date update correctly.

#### 7. testIsOverdue
- **Description:** Tests overdue task identification.
- **Assertions:**
    - Task is overdue if the due date is in the past.
    - Task is not overdue if the due date is in the future.

#### 8. testStatusTransitions
- **Description:** Validates task status transitions.
- **Assertions:**
    - Status transitions correctly between `PENDING` and `CLOSED`.

#### 9. testDueDateValidation
- **Description:** Checks due date validation.
- **Assertions:**
    - Task is overdue when due date is in the past.
    - Task is not overdue when due date is in the future.

#### 10. testPriorityManagement
- **Description:** Verifies priority modifications.
- **Assertions:**
    - Priority changes correctly between `LOW` and `HIGH`.

### Setup and Teardown
- **setUp():** Initializes a `Task` and a `User` before each test.
- **tearDown():** Cleans up by setting `Task` and `User` objects to null after each test.

---

## TeamTest Class

### Overview
The `TeamTest` class ensures `Team` class functionalities work correctly.

### Test Methods

#### 1. testTeamCreationWithValidData
- **Description:** Verifies `Team` creation with valid data.
- **Assertions:**
    - Team object is not null.
    - Name and description match expected values.

#### 2. testAddMember
- **Description:** Ensures a member can be added to a team.
- **Assertions:**
    - Member is in the team.
    - User’s team reference is set correctly.

#### 3. testRemoveMember
- **Description:** Checks member removal from a team.
- **Assertions:**
    - Member is no longer in the team.
    - User’s team reference is null.

#### 4. testAddTask
- **Description:** Tests adding a task to a team.
- **Assertions:**
    - Task is in the team’s task list.
    - Task’s team reference is correct.

#### 5. testRemoveTask
- **Description:** Verifies task removal from a team.
- **Assertions:**
    - Task is no longer in the team’s task list.
    - Task’s team reference is null.

#### 6. testGetAllMembers
- **Description:** Ensures correct retrieval of all team members.
- **Assertions:**
    - Team member count is correct.
    - Expected members are in the set.

#### 7. testGetAllTasks
- **Description:** Tests retrieval of all tasks assigned to a team.
- **Assertions:**
    - Team task count is correct.
    - Expected tasks are in the set.

### Setup and Teardown
- **setUp():** Initializes `Task`, `Team`, and `User` before each test.
- **tearDown():** Cleans up by nullifying the objects.

---

## UserTest Class

### Overview
The `UserTest` class tests `User` class functionality.

### Test Methods

#### 1. testUserCreationWithValidData
- **Description:** Verifies `User` creation with valid data.
- **Assertions:**
    - User object is not null.
    - Email, password, first name, and last name match expected values.

#### 2. testJoinTeam
- **Description:** Tests user joining a team.
- **Assertions:**
    - User's team reference is updated.
    - Team's `addMember` method is called.

#### 3. testLeaveTeam
- **Description:** Ensures user can leave a team.
- **Assertions:**
    - User’s team reference is null.
    - Team’s `removeMember` method is called.

#### 4. testIsPartOfTeam
- **Description:** Tests if user is part of a team.
- **Assertions:**
    - Returns `true` when the user is in a team.
    - Returns `false` when the user is not in a team.

#### 5. testAssignTask
- **Description:** Ensures a user can be assigned a task.
- **Assertions:**
    - Task’s `setAssignedUser` method is called with the user.

#### 6. testUnassignTask
- **Description:** Tests task unassignment.
- **Assertions:**
    - Task’s `setAssignedUser` method is called with `null`.

#### 7. testIsAssignedToTask
- **Description:** Checks if user is assigned to a task.
- **Assertions:**
    - Returns `true` when the task’s assigned user is the user.
    - Returns `false` when the task’s assigned user is `null`.

#### 8. testGetAssignedTasks
- **Description:** Retrieves all tasks assigned to a user.
- **Assertions:**
    - Correct tasks are returned.
    - Only tasks assigned to the user are included.

### Setup and Teardown
- **setUp():** Initializes `User`, `Team`, and `Task` (using mocks where necessary) before each test.
- **tearDown():** Cleans up by nullifying the objects.

