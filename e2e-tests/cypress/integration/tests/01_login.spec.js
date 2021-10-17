describe('Login Scenario', () => {
    it('check if login is successful', () => {
        const username = Cypress.env('username');
        const password = Cypress.env('password');

        cy.visit('/');
        cy.get("#username").type(username);
        cy.get("#password").type(password);
        cy.get("#login").click();

        cy.url().should('be.equal',`${Cypress.config("baseUrl")}/dashboard`);
    })
})
