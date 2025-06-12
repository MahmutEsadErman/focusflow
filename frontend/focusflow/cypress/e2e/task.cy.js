describe('Task Form', () => {
  beforeEach(() => {

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

    cy.get('button.p-datepicker-today-button').click(); // PrimeVue's Today button selector
    cy.get('[data-testid="submit-task-button"]').click();


    cy.get('.p-toast-message').should('not.exist');


  });

  it('should show error toast when title is empty (negative test)', () => {

    cy.get('[data-testid="task-title-input"]').clear();


    cy.get('[data-testid="submit-task-button"]').click();


    cy.get('.p-toast-message .p-toast-summary')
        .should('contain.text', 'Error');

    cy.get('.p-toast-message .p-toast-detail')
        .should('contain.text', '');
  });
});
