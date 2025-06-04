describe('FocusFlow Task Management UI Tests', () => {
  beforeEach(() => {
    cy.visit('http://localhost:5177'); 
  });

  it('Positive Test: Creates a new task successfully', () => {
    cy.contains('New Task').click();

    cy.get('[data-testid="task-title-input"]').type('Write E2E Tests');
    cy.get('[data-testid="task-desc-input"]').type('Add Cypress tests for task creation');
    cy.get('[data-testid="task-due-input"]').type('2025-06-10');

    cy.get('[data-testid="submit-task-button"]').click();

    cy.contains('Write E2E Tests').should('exist');
    cy.contains('Add Cypress tests for task creation').should('exist');
    cy.contains('2025-06-10').should('exist');
  });

  it('Negative Test: Show error on submitting empty task', () => {
    cy.contains('New Task').click();
    cy.get('[data-testid="submit-task-button"]').click();

    cy.contains('Title is required').should('exist'); // Update based on your actual validation message
  });
});
