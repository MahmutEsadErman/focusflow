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
| 4   | [Specifications]     | [2.2 User Classes and Characteristics] |                   | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 5   | [Specifications]     | [Location]                             | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 2   | [Task.java]          | [Task Class]                           | [The methods for task filter are missing.]                                                    | [Functionality and Logic] | [Glock]    | [Major]        | [...]                                                                                           | [Open]                                      | [Mahmut Esad Erman]  |
| 3   | [User.java]          | [User Class]                           | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 6   | [File/Document/Code] | [Location]                             | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 7   | [File/Document/Code] | [Location]                             | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 8   | [File/Document/Code] | [Location]                             | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |
| 9   | [File/Document/Code] | [Location]                             | [Description]                                                                                 | [Checklist/Scenario]      | [Found By] | [Major/Minor]  | [Comments]                                                                                      | [Open/Resolved/Rejected/Deferred/Duplicate] | [Responsible Person] |



# Retrospective

# Static Code Analysis

## Chosen Tools

### 1- [Tool]
- #### Purpose
- #### Justification
- [Integrate the selected tools into the project. Document the steps you took to set up and configure
   the tools. ]
- [Evaluate the impact of these tools on the project. Do you think they are useful for improving code
  quality and catching issues early, or do they potentially slow down the development process?
  Provide your reasoning. ]

### 2- [Tool]
- #### Purpose
- #### Justification
- [Integrate the selected tools into the project. Document the steps you took to set up and configure
  the tools. ]
- [Evaluate the impact of these tools on the project. Do you think they are useful for improving code
  quality and catching issues early, or do they potentially slow down the development process?
  Provide your reasoning. ]