# Automated_Web_Testing_Project

### Summary -

This project involves creating an automated testing framework for the website Magento Testing Website using the Page Object Model (POM) design pattern. The goal is to ensure that the website's key functionalities are thoroughly tested and validated through automated tests written in Gherkin syntax.

### Project Goal -

Develop a robust, automated test framework using the POM pattern.
Derive user stories from the website's functionality and create Gherkin-based test cases.
Ensure all key user journeys are tested through automated tests.
Track progress and defects using a project board on GitHub.

### Setup Instructions -

**Prerequisites :**

* Intellij Idea (or any other development environment)
* Java Development Kit (JDK) 8 or higher
* Maven
* ChromeDriver (compatible with your Chrome browser version)
* IDE (e.g., IntelliJ IDEA, Eclipse)

**Setup Steps :**
* Clone the Repository:

```bash
Copy code
git clone https://github.com/yourusername/automated-web-testing.git

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

### WebDriver Configuration:

**ChromeDriver:**
* Ensure chromedriver.exe is either:   
* Placed in the following directory within the project: 'TestingFramework/src/test/resources/drivers'
* If you're using a different location, set the ChromeDriver path in your test configuration:
  
```java
System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
```

**GeckoDriver (for Firefox):**
* Ensure 'geckodriver' is either:
* Located in your system’s PATH, or Placed in the following directory within the project: 'TestingFramework/src/test/resources/drivers'
* If you're using a different location, set the GeckoDriver path in your test configuration:

```java
System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
```

**EdgeDriver:**
* Ensure msedgedriver.exe is either:   
* Placed in the following directory within the project: 'TestingFramework/src/test/resources/drivers'
* If you're using a different location, set the edgedriver path in your test configuration:

```java
System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
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

Report is generated in target folder or viewable through the github actions workflow.

### Collaborative Use of GitHub -
Project Board: Used GitHub Projects to manage user stories, tasks, and issues.
Defect Tracking: Identified defects were documented as issues on the project board, with descriptions and severity levels.
GitHub Actions: Implemented a CI/CD pipeline using GitHub Actions to automate test execution on each pull request.

### GitHub Action Workflow -
Continuous Integration: A GitHub Actions workflow is set up to run tests automatically on every push to the repository. This ensures that the codebase remains stable and that any issues are caught early.

### Conclusion -
This project demonstrates the development of an automated testing framework that ensures the key functionalities of an eCommerce website are rigorously tested. By leveraging the POM pattern and Gherkin syntax, the tests are structured, maintainable, and aligned with user stories derived from actual user journeys.

### Contributing -
* Katherine - Java Tester
* Gilbert - Java Tester
* Devin - Java Tester
* Marko - Scrum Master

### License -
MIT License
Copyright (c) 2024 TEAM A

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
