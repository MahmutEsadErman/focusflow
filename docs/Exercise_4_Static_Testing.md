# Master Review Template

[The main reason of the master review document is to provide a comprehensive overview of the project and the review process. It should include all the information about the project, the review process, the participants, the review objects, the reference documents, the checklist, and the additional notes.]

## Review Information

- **Review Number:** 1
- **Project Name:** FocusFlow
- **Project Manager:** Necmettin Bera Çalık
- **Quality Expert:** Ahmad Alhelal

## Review Objects
- FocusFlow introductory text
- The functional system requirements and specification
- Existing code base of the core entities

## Reference Documents
- Introductory Text: README.md
- Specification file: docs/spec/spec.md
- Existing code base: backend

## Checklist

- [ ] Code Style and Formatting
- [ ] Functionality and Logic
- [ ] Error Handling
- [ ] Documentation
- [ ] Performance Considerations
- [ ] Security Considerations
- [ ] Testing Coverage
- [ ] Compliance with Standards

## Participating Reviewers and Roles

- Nicolai Glock - Reviewer
- Ahmad Alhelal - Reviewer
- Mahmut Esad Erman -  Author
- Necmettin Bera Çalık -  Author

## Review Decision

- [Provide information about the review decision, including the decision made and the justification for the decision. Is the review approved or not? If not, what are the reasons for the rejection?]
- [Is a follow-up review required? If yes, what is the next step?]
- [Is a re-inspection required? If yes, what is the next step?]

## Date of Review

- **Date:** 03/04/2025

## Additional notes

- [Provide additional notes if required]


# List of Findings (WIP) (We will do tomorrow)

[The list of findings is a table that contains all the findings found during the review. It should include the following columns: No., Review Object, Finding Location, Description, Checklist/Scenario (found using checklist or a use case scenario), Found By, Severity Level (Major/Minor), Comments, Status (Open/Resolved/Rejected/Deferred/Duplicate), and Responsible Person.]

| No. | Review Object        | Finding Location                       | Description                                                                                   | Checklist/Scenario        | Found By   | Severity Level | Comments                                                                                        | Status                                      | Responsible Person   |
|-----|----------------------|----------------------------------------|-----------------------------------------------------------------------------------------------|---------------------------|------------|----------------|-------------------------------------------------------------------------------------------------|---------------------------------------------|----------------------|
| 1   | [Specifications]     | [3.4 Dashboard]                        | [In the introductory text we couldn't find anything related to this. So this can be unnecessary for the customer.] | [Scenario ]               | [Alhelal]  | [Minor]        | [It is not written in introductory text. That's why it can be an unnecessary feature. (YAGNI) ] | [Open]                                      | [Ahmad Alhelal]      |
| 4   | [Specifications]     | [2.2 User Classes and Characteristics] |                   | [Checklist]      | [Ahmad Alhelal] | [Major]  | [there is no error handling]                                                                                      | [Open] | [Responsible Person] |
| 5   | [Specifications]     | [Location]                             | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 2   | [Task.java]          | [Task Class]                           | [The methods for task filter are missing.]                                                    | [Functionality and Logic] | [Glock]    | [Major]        | [...]                                                                                           | [Open]                                      | [Mahmut Esad Erman]  |
| 3   | [Task.java]          | [Task Class]                           | [The methods for Create tasks,Modify tasks,View tasks
 are missing.
]                                                                                 | [Checklist]      | [Ahmad Alhelal] | [Major]  | [should be implemented]                                                                                      | [Open] | [Glock] |
| 4   | [Task.java] | [Task Class]                             | [Description]                                                                                 | [Checklist]      | [Ahmad Alhelal] | [Major]  | [The methods for knowing the status of the task(Open,Pending,In Review,Closed) are missing.]                                                                                      | [Open] | [Mahmut Esad Erman] |
| 5   | [over all] | [over all]                             | [there is no error handling]                                                                                 | [Checklist]      | [Ahmad Alhelal] | [Major]  | [make try..catchs for the potential error]                                                                                      | [Open] | [Necmettin Bera Çalık] |
| 6   | [Tag] | [Tag.java]                             | [it is not clear what tag class does and it is not explained in Docu]                                                                                 | [Checklist-Documentation]      | [[Ahmad Alhelal] | [Major]  | [we should explain what everything do]                                                                                      | [Open] | [Mahmut Esad Erman] |
| 7   | [TagTest] | [TagTest.java]                             | [there no closing for the testcase with tearDown]                                                                                  | [Checklist-Compliance with Standards]      | [Ahmad Alhelal] | [Major]  | [add tearDown function to clean up, delete, or reset objects/resources]                                                                                      | [Open] | [Glock] |



# Retrospective
The review helped us catch issues early and align as a team. It helped us to see, even if the code is running well, there could be logical errors or implementations which not mach to the requirements of the customer . It’s a good method for improving code quality and clarifying requirements. If more people make the review more opinions and aspects come through, which improve the whole process. We’d use it mainly for critical code, requirement validation, and before major releases to ensure shared understanding and reduce errors.
# Static Code Analysis

## Chosen Tools

### 1- PMD
- #### Purpose
  To automatically detect coding issues such as unused variables, empty blocks, and code duplication during development and ensure code quality by enforcing predefined rules.

- #### Justification
  It supports consistent coding standards and helps catch issues before code reviews.

- #### Integration & Setup Steps:
  1. Open IntelliJ IDEA.
  2. Go to *Settings > Plugins*.
  3. Search and install “PMD Plugin”.
  4. Restart IntelliJ.
  5. Configure ruleset if needed.
  6. Run PMD from the Tools menu on the project.

- #### Impact Evaluation:  
  Useful for early detection of issues and enforcing clean code. Doesn’t slow development since it runs inside IntelliJ.

### 2- [SpotBugs Plugin]

- #### Purpose
  To perform static analysis of Java bytecode within IntelliJ IDEA, identifying potential bugs and coding issues.
- 
- #### Justification
  SpotBugs detects a wide range of bug patterns, helping maintain high code quality and reliability.

- #### Integration & Setup Steps:
  1. Open IntelliJ IDEA.
  2. Navigate to *Settings > Plugins*.
  3. Search for "SpotBugs" and install the plugin.
  4. Restart IntelliJ IDEA to activate the plugin.
  5. Run SpotBugs analysis via the Tools menu on your project.

- **Impact Evaluation**:
  SpotBugs aids in early detection of potential bugs, enhancing code quality. Integrated within IntelliJ IDEA, it operates efficiently without significantly impacting development speed.
- #### Impact Evaluation:  
    SpotBugs improves code reliability by detecting issues like null pointer dereferences, infinite recursive loops, bad uses of Java libraries, and deadlocks. Integrated into IntelliJ, it runs quickly with minimal disruption. It helps catch serious bugs early, supporting cleaner and more stable code.