# focusflow

# UI / E2E Testing – Exercise 10.2

## Test Cases

### 1. Positive Test – Task Creation

- Fills out the task form (title, description, due date).
- Clicks the submit button.
- Verifies success message is shown.

### 2. Negative Test – Empty Title

- Leaves the title empty.
- Fills the other fields.
- Clicks submit.
- Verifies that an error toast or validation message appears.

##  Test Tool

- Cypress

##  How to Run the Tests

1. Install Cypress if not already:
   ```bash
   npm install cypress --save-dev
   ```
# Open Cypress UI:
npx cypress open

# UI/E2E Testing Documentation – Exercise 10.2


# How to Run the Tests
Prerequisites
Make sure you have Node.js installed, then install Cypress:

npm install cypress --save-dev
# Run in Interactive Mode (GUI)

npx cypress open
This opens the Cypress Test Runner.

Select task.cy.js under cypress/e2e.

Click to run the tests in a browser.

Run in Headless Mode (Terminal)

npx cypress run --spec "cypress/e2e/task.cy.js"

Test Cases (Exercise 10.2)

1. Positive Test – Task Creation
   Scenario: User fills in task title, description, due date, and submits the form.

Expectation: A success message like "Task created successfully" appears.

2. Negative Test – Missing Title
   Scenario: User leaves the task title empty and submits the form.

Expectation: An error message like "Title is required" appears.

Selectors used: data-testid="..." attributes to locate inputs and buttons.

# Test Output & Result (Exercise 10.3)
Tool: Cypress

Test Status: Both tests failed

Reason: The element with data-testid="task-title-input" was not found in the DOM.

Cypress Error:

Timed out retrying after 4000ms: Expected to find element: [data-testid="task-title-input"], but never found it.
# Insights & Reflection
# What Was Easy
Writing the test cases in Cypress using describe, it, and cy.get.

Mapping BDD user stories to real test flows.

# What Was Difficult
The app UI did not render the form inputs (task-title-input) as expected.

Debugging why the element didn’t appear was challenging.

Timing issues or missing routing state might be preventing rendering.

# Takeaways
Cypress is simple to write but depends heavily on predictable UI behavior.

Conditional rendering and routing need to be tested interactively first.

Good test structure can still be demonstrated even if the UI is incomplete.






This template should help get you started developing with Vue 3 in Vite.

## Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).

## Customize configuration

See [Vite Configuration Reference](https://vite.dev/config/).

## Project Setup

```sh
npm install
```

### Compile and Hot-Reload for Development

```sh
npm run dev
```

### Compile and Minify for Production

```sh
npm run build
```

### Run Unit Tests with [Vitest](https://vitest.dev/)

```sh
npm run test:unit
```

### Run End-to-End Tests with [Cypress](https://www.cypress.io/)

```sh
npm run test:e2e:dev
```

This runs the end-to-end tests against the Vite development server.
It is much faster than the production build.

But it's still recommended to test the production build with `test:e2e` before deploying (e.g. in CI environments):

```sh
npm run build
npm run test:e2e
```
