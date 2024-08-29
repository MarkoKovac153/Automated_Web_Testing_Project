# Automated_Web_Testing_Project -

### Summary -

This project involves creating an automated testing framework for the website Magento Testing Website using the Page Object Model (POM) design pattern. The goal is to ensure that the website's key functionalities are thoroughly tested and validated through automated tests written in Gherkin syntax.

### Project Goal -

Develop a robust, automated test framework using the POM pattern.
Derive user stories from the website's functionality and create Gherkin-based test cases.
Ensure all key user journeys are tested through automated tests.
Track progress and defects using a project board on GitHub.

### Setup Instructions -

**Prerequisites :**

* Java Development Kit (JDK) 8 or higher
* Maven
* ChromeDriver (compatible with your Chrome browser version)
* IDE (e.g., IntelliJ IDEA, Eclipse)

**Setup Steps :**
* Clone the Repository:

```bash
Copy code
git clone https://github.com/yourusername/automated-web-testing.git
```

* Navigate to the Project Directory:
```bash
Copy code
cd automated-web-testing
```

* Install Dependencies:
```bash
Copy code
mvn clean install
```

**Set Up the ChromeDriver Path :**

Ensure the chromedriver.exe file is located in your system’s PATH or specify the path in the test configuration.
Run Tests:

```bash
Copy code
mvn test
```

### What Was Tested -

**User Journeys :**

Viewing product details
Adding products to the shopping cart
Searching for products
Proceeding to checkout
Logging in and account creation

**Test Coverage :**

Positive paths (happy paths) to ensure standard functionality works as expected.

Negative paths (sad paths) to test the system's behavior in failure scenarios.

**Test Metrics :**

(Report here :))

### Collaborative Use of GitHub -
Project Board: Used GitHub Projects to manage user stories, tasks, and issues.
Defect Tracking: Identified defects were documented as issues on the project board, with descriptions and severity levels.
GitHub Actions: Implemented a CI/CD pipeline using GitHub Actions to automate test execution on each pull request.

### GitHub Action Workflow -
Continuous Integration: A GitHub Actions workflow is set up to run tests automatically on every push to the repository. This ensures that the codebase remains stable and that any issues are caught early.

### Conclusion -
This project demonstrates the development of an automated testing framework that ensures the key functionalities of an eCommerce website are rigorously tested. By leveraging the POM pattern and Gherkin syntax, the tests are structured, maintainable, and aligned with user stories derived from actual user journeys.
