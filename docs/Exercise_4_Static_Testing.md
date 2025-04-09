# Review

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

- **Review Decision:** The review is not approved. The justification for this decision is based on the identified major issues, including missing methods for task filtering, creation, modification, and status handling in the `Task` class, as well as the lack of error handling and documentation clarity in certain areas. These issues need to be addressed to meet the project requirements and quality standards.

- **Follow-up Review:** Yes, a follow-up review is required. The next step is to implement the missing methods, improve error handling, and enhance documentation as per the findings. Once these changes are made, the updated code should be submitted for re-evaluation.

- **Re-inspection:** Yes, a re-inspection is required. After the follow-up review, the updated code will be re-inspected to ensure all identified issues have been resolved and the code meets the required standards.

## Date of Review

- **Date:** 03/04/2025

# List of Findings (WIP) (We will do tomorrow)

[The list of findings is a table that contains all the findings found during the review. It should include the following columns: No., Review Object, Finding Location, Description, Checklist/Scenario (found using checklist or a use case scenario), Found By, Severity Level (Major/Minor), Comments, Status (Open/Resolved/Rejected/Deferred/Duplicate), and Responsible Person.]

| No. | Review Object    | Finding Location                       | Description                                                                                                        | Checklist/Scenario                    | Found By     | Severity Level | Comments                                                                                        | Status   | Responsible Person     |
|-----|------------------|----------------------------------------|--------------------------------------------------------------------------------------------------------------------|---------------------------------------|--------------|----------------|-------------------------------------------------------------------------------------------------|----------|------------------------|
| 1   | [Specifications] | [3.4 Dashboard]                        | [In the introductory text we couldn't find anything related to this. So this can be unnecessary for the customer.] | [Scenario ]                           | [Alhelal]    | [Minor]        | [It is not written in introductory text. That's why it can be an unnecessary feature. (YAGNI) ] | [Open]   | [Ahmad Alhelal]        |
| 2   | [Task.java]      | [Task Class]                           | [The methods for task filter are missing.]                                                                         | [Functionality and Logic]             | [Glock]      | [Major]        | [...]                                                                                           | [Open]   | [Mahmut Esad Erman]    |
| 3   | [Task.java]      | [Task Class]                           | [The methods for Create tasks,Modify tasks,View tasks are missing.]                                                | [Checklist]                           | [Alhelal]    | [Major]        | [should be implemented]                                                                         | [Open]   | [Glock]                |
| 4   | [Task.java]      | [Task Class]                           | [The methods for knowing the status of the task]                                                                   | [Checklist]                           | [Alhelal]    | [Major]        | [The methods for knowing the status of the task(Open,Pending,In Review,Closed) are missing.]    | [Open]   | [Mahmut Esad Erman]    |
| 5   | [over all]       | [over all]                             | [there is no error handling]                                                                                       | [Checklist]                           | [Alhelal]    | [Major]        | [make try..catchs for the potential error]                                                      | [Open]   | [Necmettin Bera Çalık] |
| 6   | [Tag]            | [Tag.java]                             | [it is not clear what tag class does and it is not explained in Docu]                                              | [Checklist-Documentation]             | [[Alhelal]   | [Major]        | [we should explain what everything do]                                                          | [Open]   | [Mahmut Esad Erman]    |
| 7   | [TagTest]        | [TagTest.java]                         | [there no closing for the testcase with tearDown]                                                                  | [Checklist-Compliance with Standards] | [Alhelal]    | [Major]        | [add tearDown function to clean up, delete, or reset objects/resources]                         | [Open]   | [Glock]                |



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

### 2- SpotBugs Plugin

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
  SpotBugs aids in early detection of potential bugs, enhancing code quality. Integrated within IntelliJ IDEA, it operates efficiently without significantly impacting development speed.
- #### Impact Evaluation:  
    SpotBugs improves code reliability by detecting issues like null pointer dereferences, infinite recursive loops, bad uses of Java libraries, and deadlocks. Integrated into IntelliJ, it runs quickly with minimal disruption. It helps catch serious bugs early, supporting cleaner and more stable code.