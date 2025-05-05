# FocusFlow Feature Scenarios

## Feature: **Task Management**

### Background

```
Given the user has logged into the FocusFlow application  
And the user has an active workspace
```

---

### Scenario: **User adds a new task**

```
Given the user is on the task dashboard  
When the user clicks the "Add Task" button  
And the user enters "Complete project report" in the task name field  
And selects a due date of "2025-05-10"  
And clicks "Save"  
Then the new task "Complete project report" should be visible in the task list  
And the due date should be displayed as "2025-05-10"
```

---

### Scenario: **User marks a task as complete**

```
Given the user is on the task dashboard  
And the task "Respond to client emails" is marked as pending  
When the user clicks the checkbox next to "Respond to client emails"  
Then the task "Respond to client emails" should be marked as completed  
And the task status should update visually
```

---

### Scenario: **User deletes a task**

```
Given the user is on the task dashboard  
And the task "Prepare for team meeting" exists  
When the user clicks the "Delete" icon next to "Prepare for team meeting"  
And confirms the deletion in the popup  
Then the task "Prepare for team meeting" should no longer appear in the task list
```

---

## Feature: **Productivity View**

### Scenario: **User views tasks due today**

```
Given the user is on the task dashboard  
And there are tasks with due dates including "2025-05-05"  
When the user selects the "Today" filter  
Then only tasks with a due date of "2025-05-05" should be displayed  
And tasks with other due dates should not appear
```

---

### Scenario: **User views overdue tasks**

```
Given the user is on the task dashboard  
And there are tasks with due dates before "2025-05-05"  
When the user selects the "Overdue" filter  
Then only tasks with due dates before "2025-05-05" should be displayed  
And tasks with due dates on or after "2025-05-05" should not appear
```

---

## Feature: **Team Collaboration**

### Scenario: **User assigns a task to a team member**

```
Given the user is on the task dashboard  
And the user has created a task named "Organize team brainstorming session"  
When the user clicks the "Assign" icon next to the task  
And selects "John Doe" from the team member list  
Then the task "Organize team brainstorming session" should be assigned to "John Doe"  
And "John Doe" should receive a notification about the assigned task
```

---

## Feature: **Task Prioritization**

### Scenario: **User sets a task priority**

```
Given the user is on the task dashboard  
And the task "Design homepage layout" exists  
When the user clicks the "Priority" dropdown next to the task  
And selects "High"  
Then the priority of "Design homepage layout" should be updated to "High"  
And the task should be visually marked with a priority indicator
```

---

## Feature: **Reminder Notifications**

### Scenario: **User sets a reminder for a task**

```
Given the user is on the task dashboard  
And the task "Submit expense report" exists  
When the user clicks the "Set Reminder" button next to the task  
And selects a reminder time of "1 day before the due date"  
Then a reminder should be scheduled for "Submit expense report"  
And the user should receive a confirmation notification
```

---

## Feature: **Task Filtering**

### Scenario: **User filters tasks by priority**

```
Given the user is on the task dashboard  
And there are tasks with various priority levels  
When the user selects "High Priority" from the filter dropdown  
Then only tasks with a priority of "High" should be displayed  
And tasks with other priorities should not appear
```

---

### Scenario: **User filters tasks by assignee**

```
Given the user is on the task dashboard  
And there are tasks assigned to multiple team members  
When the user selects "John Doe" from the "Assignee" filter  
Then only tasks assigned to "John Doe" should be displayed  
And tasks assigned to other team members should not appear
```

---

## Feature: **Task Comments**

### Scenario: **User adds a comment to a task**

```
Given the user is on the task dashboard  
And the task "Plan quarterly review meeting" exists  
When the user clicks the "Add Comment" button next to the task  
And enters "Don't forget to include budget updates" in the comment field  
And clicks "Post Comment"  
Then the comment "Don't forget to include budget updates" should be added to the task  
And the comment should be visible under the task
```

---

## Feature: **Task Dependencies**

### Scenario: **User sets a dependency between tasks**

```
Given the user is on the task dashboard  
And there are tasks "Write project proposal" and "Review project proposal"  
When the user clicks the "Set Dependency" button next to "Review project proposal"  
And selects "Write project proposal" as the dependent task  
Then "Review project proposal" should be marked as dependent on "Write project proposal"  
And the dependency should be visually indicated on the dashboard
```

---

## Feature: **Task Editing**

### Scenario: **User edits a task**

```
Given the user is on the task dashboard  
And the task "Prepare client presentation" exists  
When the user clicks the "Edit" button next to the task  
And updates the task name to "Prepare presentation for client meeting"  
And updates the due date to "2025-05-15"  
And clicks "Save"  
Then the task should be updated with the new name "Prepare presentation for client meeting"  
And the new due date "2025-05-15" should be displayed
```

---

## Feature: **Task Archiving**

### Scenario: **User archives a completed task**

```
Given the user is on the task dashboard  
And the task "Submit project report" is marked as completed  
When the user clicks the "Archive" button next to the task  
Then the task "Submit project report" should be moved to the archive  
And it should no longer appear in the active task list
```

---
