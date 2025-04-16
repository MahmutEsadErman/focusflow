# FocusFlow Test Design

## Exercise 5.1: Black-Box Testing Techniques

### 1. Equivalence Class Partitioning (ECP)

#### 1.1 Identified Equivalence Classes

##### Length Requirement

| Class ID | Description | Type | Example |
|----------|-------------|------|---------|
| L1 | Passwords with length between 10-12 characters | Valid | "Ab#1234567" (10 chars) |
| L2 | Passwords with length < 10 characters | Invalid | "Ab#12345" (8 chars) |
| L3 | Passwords with length > 12 characters | Invalid | "Ab#1234567890" (13 chars) |

##### Uppercase Letter Requirement

| Class ID | Description | Type | Example |
|----------|-------------|------|---------|
| U1 | Passwords containing at least one uppercase letter | Valid | "Abc1234!@#" |
| U2 | Passwords containing no uppercase letters | Invalid | "abc1234!@#" |

##### Lowercase Letter Requirement

| Class ID | Description | Type | Example |
|----------|-------------|------|---------|
| C1 | Passwords containing at least one lowercase letter | Valid | "ABC1234a!@" |
| C2 | Passwords containing no lowercase letters | Invalid | "ABC1234!@#" |

##### Special Character Requirement

| Class ID | Description | Type | Example |
|----------|-------------|------|---------|
| S1 | Passwords containing at least one special character | Valid | "ABCabc123!" |
| S2 | Passwords containing no special characters | Invalid | "ABCabc1234" |

#### 1.2 Boundary Analysis

**Length Requirement Boundaries:**
- Lower boundary: 9 characters (invalid) vs. 10 characters (valid)
- Upper boundary: 12 characters (valid) vs. 13 characters (invalid)

**Character Type Requirements:**
For the character type requirements (uppercase, lowercase, special character), the boundary is between having zero of the required character type (invalid) and having one of the required character type (valid).

#### 1.3 Test Cases Based on Equivalence Classes

| Test ID | Password | Classes Covered | Expected Result | Explanation |
|---------|----------|-----------------|-----------------|-------------|
| TC1 | "Password!1" | L1, U1, C1, S1 | Accept | Valid password with all requirements met (10 chars, uppercase, lowercase, special char) |
| TC2 | "PassWord!12" | L1, U1, C1, S1 | Accept | Valid password with all requirements met (12 chars - upper boundary) |
| TC3 | "Passw0rd!1" | L1, U1, C1, S1 | Accept | Valid password with all requirements met (10 chars - lower boundary) |
| TC4 | "Pass!1" | L2, U1, C1, S1 | Reject | Invalid length (only 6 chars) |
| TC5 | "Passw0rd!123456" | L3, U1, C1, S1 | Reject | Invalid length (17 chars) |
| TC6 | "passw0rd!12" | L1, U2, C1, S1 | Reject | Missing uppercase letter |
| TC7 | "PASSW0RD!12" | L1, U1, C2, S1 | Reject | Missing lowercase letter |
| TC8 | "Passw0rd123" | L1, U1, C1, S2 | Reject | Missing special character |
| TC9 | "password123" | L1, U2, C1, S2 | Reject | Missing uppercase letter and special character |
| TC10 | "PASSWORD!12" | L1, U1, C2, S1 | Reject | Missing lowercase letter |
| TC11 | "Pass1" | L2, U1, C1, S2 | Reject | Too short and missing special character |
| TC12 | "passwordpasswordpassword!" | L3, U2, C1, S1 | Reject | Too long and missing uppercase letter |

### 2. Decision Table Testing

Decision Table Testing identifies all possible combinations of conditions and their corresponding actions.

#### 2.1 Conditions and Outcomes

**Conditions:**
1. Is the password length between 10 and 12 characters?
2. Does the password contain at least one uppercase letter?
3. Does the password contain at least one lowercase letter?
4. Does the password contain at least one special character?

**Outcomes:**
- Accept: Password is valid (all conditions are met)
- Reject: Password is invalid (at least one condition is not met)

#### 2.2 Decision Table

| Rule | C1: Valid Length (10-12) | C2: Has Uppercase | C3: Has Lowercase | C4: Has Special Char | Outcome |
|------|--------------------------|-------------------|-------------------|----------------------|---------|
| R1   | True                     | True              | True              | True                 | Accept  |
| R2   | False                    | True              | True              | True                 | Reject  |
| R3   | True                     | False             | True              | True                 | Reject  |
| R4   | True                     | True              | False             | True                 | Reject  |
| R5   | True                     | True              | True              | False                | Reject  |
| R6   | False                    | False             | True              | True                 | Reject  |
| R7   | False                    | True              | False             | True                 | Reject  |
| R8   | False                    | True              | True              | False                | Reject  |
| R9   | True                     | False             | False             | True                 | Reject  |
| R10  | True                     | False             | True              | False                | Reject  |
| R11  | True                     | True              | False             | False                | Reject  |
| R12  | False                    | False             | False             | True                 | Reject  |
| R13  | False                    | False             | True              | False                | Reject  |
| R14  | False                    | True              | False             | False                | Reject  |
| R15  | True                     | False             | False             | False                | Reject  |
| R16  | False                    | False             | False             | False                | Reject  |

#### 2.3 Rationale

The decision table represents all possible combinations of the four conditions. There is only one rule (R1) that leads to password acceptance, which is when all conditions are met. This aligns with the requirement that all criteria must be satisfied for a valid password. All other combinations (R2-R16) result in password rejection.
# FocusFlow Password Validation - Detailed Test Cases

## Test Cases List

### 1. Equivalence Class Partitioning Test Cases

| Test ID | Test Password | Equivalence Classes | Expected Outcome | Explanation |
|---------|---------------|---------------------|------------------|-------------|
| ECP-01  | "Password!1"  | L1, U1, C1, S1      | Accept           | This password is 10 characters long (within the valid range of 10-12), contains an uppercase letter ('P'), lowercase letters ('assword'), and a special character ('!'). All validation criteria are met. |
| ECP-02  | "Pa$$w0rD!12" | L1, U1, C1, S1      | Accept           | This password is 12 characters long (at the upper boundary of valid length), contains uppercase letters ('P', 'D'), lowercase letters ('a', 'w', 'r'), and multiple special characters ('$', '$', '!'). All validation criteria are met. |
| ECP-03  | "Passw0rd!1"  | L1, U1, C1, S1      | Accept           | This password is 10 characters long (at the lower boundary of valid length), contains an uppercase letter ('P'), lowercase letters ('assword'), and a special character ('!'). All validation criteria are met. |
| ECP-04  | "Pass!1"      | L2, U1, C1, S1      | Reject           | This password is only 6 characters long, which is below the minimum required length of 10. It contains all required character types but fails on the length requirement. |
| ECP-05  | "Passw0rd!12345" | L3, U1, C1, S1   | Reject           | This password is 14 characters long, which exceeds the maximum allowed length of 12. It contains all required character types but fails on the length requirement. |
| ECP-06  | "passw0rd!12" | L1, U2, C1, S1      | Reject           | This password is 11 characters long (within valid range), contains lowercase letters and a special character ('!'), but lacks an uppercase letter. It fails the uppercase requirement. |
| ECP-07  | "PASSWORD!12" | L1, U1, C2, S1      | Reject           | This password is 11 characters long (within valid range), contains uppercase letters and a special character ('!'), but lacks a lowercase letter. It fails the lowercase requirement. |
| ECP-08  | "Passw0rd123" | L1, U1, C1, S2      | Reject           | This password is 11 characters long (within valid range), contains uppercase and lowercase letters, but lacks a special character. It fails the special character requirement. |
| ECP-09  | "Passwor"     | L2, U1, C1, S2      | Reject           | This password is 7 characters long (too short) and lacks a special character. It fails on both the length and special character requirements. |

### 2. Decision Table Test Cases

| Test ID | Test Password | Rule Covered | Expected Outcome | Explanation |
|---------|---------------|--------------|------------------|-------------|
| DT-01   | "SecureP@ss1" | Rule 1       | Accept           | Valid password with 11 characters (valid length between 10-12), has uppercase ('S', 'P'), has lowercase ('ecure', 'ass'), and has a special character ('@'). Meets all requirements. |
| DT-02   | "Sec@1"       | Rule 2       | Reject           | Password has only 5 characters (invalid length < 10), but has uppercase ('S'), lowercase ('ec'), and a special character ('@'). Fails due to invalid length despite meeting other requirements. |
| DT-03   | "secure@pass1" | Rule 3      | Reject           | Password has 12 characters (valid length), has lowercase letters, has a special character ('@'), but lacks uppercase letters. Fails the uppercase requirement. |
| DT-04   | "SECURE@PASS1" | Rule 4      | Reject           | Password has 12 characters (valid length), has uppercase letters, has a special character ('@'), but lacks lowercase letters. Fails the lowercase requirement. |
| DT-05   | "SecurePass12" | Rule 5      | Reject           | Password has 12 characters (valid length), has uppercase ('S'), has lowercase ('ecurePass'), but lacks a special character. Fails the special character requirement. |
| DT-06   | "sec@1"       | Rule 6       | Reject           | Password has 5 characters (invalid length < 10), lacks uppercase letters, but has lowercase letters and a special character. Fails both length and uppercase requirements. |
| DT-07   | "SEC@1"       | Rule 7       | Reject           | Password has 5 characters (invalid length < 10), has uppercase letters and a special character, but lacks lowercase letters. Fails both length and lowercase requirements. |
| DT-08   | "SEC12"       | Rule 8       | Reject           | Password has 5 characters (invalid length < 10), has uppercase letters, lacks lowercase letters, and lacks a special character. Fails on length, lowercase, and special character requirements. |
| DT-09   | "12345@#$%^&" | Rule 9       | Reject           | Password has 11 characters (valid length), has special characters, but lacks both uppercase and lowercase letters. Fails both letter type requirements. |
| DT-10   | "abcdef12345" | Rule 10      | Reject           | Password has 11 characters (valid length), has lowercase letters, but lacks uppercase letters and special characters. Fails on uppercase and special character requirements. |
| DT-11   | "ABCDEF12345" | Rule 11      | Reject           | Password has 11 characters (valid length), has uppercase letters, but lacks lowercase letters and special characters. Fails on lowercase and special character requirements. |
| DT-12   | "!@#$%"       | Rule 12      | Reject           | Password has 5 characters (invalid length < 10), has special characters, but lacks both uppercase and lowercase letters. Fails on length and both letter type requirements. |
| DT-13   | "abcde"       | Rule 13      | Reject           | Password has 5 characters (invalid length < 10), has lowercase letters, but lacks uppercase letters and special characters. Fails on length, uppercase, and special character requirements. |
| DT-14   | "ABCDE"       | Rule 14      | Reject           | Password has 5 characters (invalid length < 10), has uppercase letters, but lacks lowercase letters and special characters. Fails on length, lowercase, and special character requirements. |
| DT-15   | "1234567890"  | Rule 15      | Reject           | Password has 10 characters (valid length), but lacks uppercase letters, lowercase letters, and special characters. Only contains numbers, failing all character type requirements. |
| DT-16   | "123"         | Rule 16      | Reject           | Password has 3 characters (invalid length < 10), and lacks uppercase letters, lowercase letters, and special characters. Fails all requirements. |

## Exercise 5.2: White-Box Testing Techniques

This part presents a whitebox test case analysis for the `createTask` method in the `TaskServiceImpl.java` file, with examples to illustrate each test case.

---

## Valid Test Cases

| Test ID | Description | Example | Expected Outcome |
|--------|-------------|---------|------------------|
| **TC1** | All valid inputs, only `assigneeId` set | `title="Fix Bug"`, `shortDescription="Fix login issue"`, `dueDate=now+2d`, `priority=HIGH`, `assigneeId=UUID1`, `teamId=null`, `createdById=UUID2` | Task created |
| **TC2** | All valid inputs, only `teamId` set | Same as TC1, but `assigneeId=null`, `teamId=UUID3` | Task created |
| **TC3** | Valid inputs, `tagIds=null` | Same as TC1, `tagIds=null` | Task created without tags |
| **TC4** | Valid inputs, empty tag set | Same as TC1, `tagIds=emptySet()` | Task created without tags |
| **TC5** | Valid inputs, multiple valid tag IDs | `tagIds={UUID4, UUID5}` | Task created with 2 tags |

---

## Invalid Test Cases: Missing Required Fields

| Test ID | Description | Example | Expected Error |
|--------|-------------|---------|----------------|
| **TC6** | `title` is null | `title=null`, rest valid | `"Title is required"` |
| **TC7** | `shortDescription` is null | `shortDescription=null`, rest valid | `"Short description is required"` |
| **TC8** | `dueDate` is null | `dueDate=null`, rest valid | `"Due date is required"` |
| **TC9** | `priority` is null | `priority=null`, rest valid | `"Priority is required"` |
| **TC10** | `createdById` is null | `createdById=null`, rest valid | `"Created by user ID is required"` |

---

## Invalid Test Cases: Field Constraints

| Test ID | Description | Example | Expected Error |
|--------|-------------|---------|----------------|
| **TC11** | `title.length > 100` | `title="a".repeat(101)` | `"Title must be between 1 and 100 characters"` |
| **TC12** | `title.length == 0` | `title=""` | `"Title must be between 1 and 100 characters"` |
| **TC13** | `shortDescription.length > 200` | `shortDescription="x".repeat(201)` | `"Short description must be at most 200 characters"` |
| **TC14** | `dueDate` in the past | `dueDate=now.minusDays(1)` | `"Due date must be in the future"` |

---

## Invalid Test Cases: Assignment Conflicts

| Test ID | Description | Example | Expected Error |
|--------|-------------|---------|----------------|
| **TC15** | Both `assigneeId` and `teamId` set | `assigneeId=UUID1`, `teamId=UUID2` | `"A task cannot be assigned to both a user and a team simultaneously"` |

---

## Invalid Test Cases: Entity Lookups

| Test ID | Description | Example | Expected Error |
|--------|-------------|---------|----------------|
| **TC16** | `createdById` does not exist | `createdById=nonexistentUUID` | `"Created by user not found"` |
| **TC17** | `assigneeId` does not exist | `assigneeId=nonexistentUUID` | `"Assignee not found"` |
| **TC18** | `teamId` does not exist | `teamId=nonexistentUUID` | `"Team not found"` |
| **TC19** | `tagIds` contains nonexistent ID | `tagIds={nonexistentUUID}` | `"Tag with ID <uuid> not found"` |

---
