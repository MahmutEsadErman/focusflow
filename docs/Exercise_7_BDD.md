# FocusFlow Exercise 7 – BDD & Gherkin Syntax

## Exercise 7.1 : User Stories Definition
see Focusflow.feature

## Exercise 7.3 : BDD Test Automation

### Implementation
- define the features
- create the steps out of the features
- create a automation file with FocusFlowCucumber (FocusFlowCucumber.java)

### Reasoning
- **BDD Focuses on Behavior, Not Implementation**
    - BDD tests describe system behavior from a user or stakeholder perspective, not low-level implementation.
    - This aligns better with integration tests, which validate how components work together.

- **Integration Tests Reflect Real-World Use**
    - BDD scenarios often involve multiple layers (e.g., UI, API, database).
    - These flows require integrated components, which unit tests cannot provide.

- **Supports Communication and Shared Understanding**
    - BDD promotes collaboration among developers, testers, and business stakeholders.
    - Integration-level scenarios are more understandable and verifiable by non-technical stakeholders.

- **Unit Tests Are Too Isolated**
    - Unit tests focus on individual methods or classes.
    - They don’t capture the full scope of user interactions described in BDD scenarios.

- **BDD Tools Fit Better at Integration or E2E Level**
    - Frameworks like Cucumber, Behave, and SpecFlow are typically used in broader testing stages.
    - They often run as part of CI/CD pipelines after unit testing has passed.