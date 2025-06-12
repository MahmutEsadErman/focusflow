describe('Task Form', () => {
  beforeEach(() => {
    // Adjust URL to your app's dev server
    cy.visit('http://localhost:5173');
    cy.get('[data-testid="createTask"]').click();
    cy.wait(1000);
  });

  it('should create a task successfully (positive test)', () => {
    // Fill the form
    cy.get('[data-testid="task-title-input"]').type('Test task title');
    cy.get('[data-testid="task-desc-input"]').type('Short desc');
    cy.get('[data-testid="task-long-desc-input"]').type('Long description for testing.');
    cy.get('[data-testid="task-due-input"]').click();
    // Select a date/time - example: select current date/time or customize based on your DatePicker implementation
    cy.get('button.p-datepicker-today-button').click(); // PrimeVue's Today button selector
    cy.get('[data-testid="submit-task-button"]').click();

    // Since your component emits 'afterSave', you might want to check if no error toast is shown:
    cy.get('.p-toast-message').should('not.exist');

    // Or check for some effect of 'afterSave', like redirect or message — depends on your app
  });

  it('should show error toast when title is empty (negative test)', () => {
    // Leave title empty
    cy.get('[data-testid="task-title-input"]').clear();

    // Fill other required fields if needed, or leave empty depending on validation
    cy.get('[data-testid="submit-task-button"]').click();

    // Check if error toast appears
    cy.get('.p-toast-message .p-toast-summary')
        .should('contain.text', 'Error');
    // Also check error detail if you want:
    cy.get('.p-toast-message .p-toast-detail')
        .should('contain.text', ''); // put expected error message here or check it dynamically
  });
});
