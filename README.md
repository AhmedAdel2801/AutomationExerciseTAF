# TAF

## Description

**TAF** is a test automation project using Selenium and Rest Assured for web application and API testing. The project is designed to be modular and customizable, allowing easy adaptation to different project requirements. It includes features such as parallel execution, data-driven testing, cross-browser testing, CI/CD integration, and custom reporting.

## Repository Information

- **Owner:** [Ashraaf7](https://github.com/Ashraaf7)
- **Repository URL:** [automation-exercise-test](https://github.com/Ashraaf7/TAF.git)
- **Primary Language:** Java

## 🚀 Features  

- **Web Application Testing:** Utilize Selenium for robust and reliable browser automation.  
- **API Testing:** Leverage Rest Assured for seamless API testing with detailed assertions.   
- **Customizable Framework:** Modular design allows easy adaptation to different project requirements.  
- **Parallel Execution:** Speed up test execution with multi-threading support.  
- **Capture screenshots and video recordings** of test executions for better debugging.
- **Page Object Model (POM):** Implement the POM design pattern for better maintainability and readability.
- **Design Patterns:** Utilize design patterns like Singleton, Factory, and Builder for better code organization.
- **Data-Driven Testing:** Support for data-driven testing using JSON and Excel files.
- **Cross-Browser Testing:** Test across multiple browsers and platforms with ease.
- **CI/CD Integration:** Seamless integration with CI/CD tools like GitHub Actions for automated testing and deployment.
- **Custom Assertions:** Implement custom assertions for specific validation needs.
- **Soft Assertions:** Support for soft assertions to continue test execution even when some assertions fail.
- **Custom Waits:** Implement custom wait strategies for better synchronization.
- **Custom Listeners:** Implement custom listeners for enhanced reporting and logging.
- **Custom Test Environment:** Support for multiple test environments (e.g., dev, staging, production) with environment-specific configurations.
- **Custom Test Reporting:** Generate custom test reports with detailed execution insights.
- **Custom Test Logging:** Implement custom logging strategies for better debugging and analysis.
- **Custom Test Utilities:** Implement custom utility classes for common tasks (e.g., file handling, JSON parsing, etc.).
- **Custom Test Framework:** Build a custom test framework with reusable components and utilities.


## 🛠️ Tools & Technologies  

- **Selenium:** Browser automation for web application testing.  
- **JUnit:** Test case structuring and execution.
- **TestNG:** Test case structuring and execution.  
- **Rest Assured:** API testing with simple and powerful HTTP request validation.  
- **Maven/Gradle:** Dependency management and build automation.  
- **Log4j:** Centralized logging for better debugging and analysis.  
- **Allure Reports:** Rich HTML reports with execution insights.
- **Faker:** Generate fake data for testing purposes.
- **Apache POI:** Read and write Excel files for data-driven testing.
- **JSON:** Data interchange format for API testing and configuration.
- **GitHub Actions:** CI/CD integration for automated testing and deployment.


  

### Prerequisites

- Java Development Kit (JDK) installed
- IDE (eg: IntelliJ IDEA, Eclipse)
- Maven or Gradle installed


### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/Ashraaf7/TAF.git
   ```
2. Navigate to the project directory:
   ```sh
   cd TAF
   ```
3. Install dependencies:
  **If using Maven:**
  ```bash
  mvn clean install  
  ```
  **If using Gradle:**
  ```bash
  gradle build  
  ```

### Run the tests:
  **Execute all tests:**
   ```bash
  mvn clean test
  ```
  **Run specific test classes or methods:**
  ```bash
  mvn -Dtest=TestClassName test 
  ```
   
```bash
## 📄 Project Structure
automation-exercise-test/  
├── src/
    ├── main/
    │   ├── resources/
    │   │   ├── waits.properties
    │   │   ├── db.properties
    │   │   ├── seleniumGrid.properties
    │   │   ├── video.properties
    │   │   ├── META-INF/
    │   │   │   └── services/
    │   │   │   │   └── org.testng.ITestNGListener
    │   │   ├── allure.properties
    │   │   ├── environment.properties
    │   │   ├── extensions/
    │   │   │   └── HaramBlur.crx
    │   │   ├── webapp.properties
    │   │   └── log4j2.properties
    │   └── java/
    │   │   └── com/
    │   │       └── automationexercices/
    │   │           ├── drivers/
    │   │               ├── WebDriverProvider.java
    │   │               ├── UITest.java
    │   │               ├── AbstractDriver.java
    │   │               ├── Browser.java
    │   │               ├── SafariFactory.java
    │   │               ├── GUIDriver.java
    │   │               ├── FirefoxFactory.java
    │   │               ├── EdgeFactory.java
    │   │               └── ChromeFactory.java
    │   │           ├── pages/
    │   │               ├── components/
    │   │               │   ├── LogoutPage.java
    │   │               │   ├── TestCasePage.java
    │   │               │   ├── ContactUsPage.java
    │   │               │   ├── ProductDetails.java
    │   │               │   ├── DeleteAccountPage.java
    │   │               │   ├── PaymentPage.java
    │   │               │   ├── ProductDetailsPage.java
    │   │               │   ├── CartPage.java
    │   │               │   ├── SignupLoginPage.java
    │   │               │   ├── SignupPage.java
    │   │               │   ├── NavigationBarComponent.java
    │   │               │   └── CheckoutPage.java
    │   │               └── ProductsPage.java
    │   │           ├── utils/
    │   │               ├── TimeManager.java
    │   │               ├── OSUtils.java
    │   │               ├── TerminalUtils.java
    │   │               ├── dataReader/
    │   │               │   ├── ExcelReader.java
    │   │               │   ├── JsonReader.java
    │   │               │   └── PropertyReader.java
    │   │               ├── report/
    │   │               │   ├── AllureEnvironmentManager.java
    │   │               │   ├── AllureConstants.java
    │   │               │   ├── AllureAttachmentManager.java
    │   │               │   ├── AllureReportGenerator.java
    │   │               │   └── AllureBinaryManager.java
    │   │               ├── logs/
    │   │               │   └── LogsManager.java
    │   │               ├── WaitManager.java
    │   │               └── actions/
    │   │               │   ├── FrameActions.java
    │   │               │   ├── AlertActions.java
    │   │               │   ├── BrowserActions.java
    │   │               │   └── ElementActions.java
    │   │           ├── apis/
    │   │               ├── Builder.java
    │   │               └── UserManagementAPI.java
    │   │           ├── validations/
    │   │               ├── Verification.java
    │   │               ├── Validation.java
    │   │               └── BaseAssertion.java
    │   │           ├── media/
    │   │               ├── ScreenshotsManager.java
    │   │               └── ScreenRecordManager.java
    │   │           ├── FileUtils.java
    │   │           └── listeners/
    │   │               └── TestNGListeners.java
    └── test/
    │   ├── resources/
    │       └── test-data/
    │       │   ├── cart-data.json
    │       │   ├── login-data.json
    │       │   ├── products-data.json
    │       │   ├── product-details-data.json
    │       │   ├── register-data.json
    │       │   └── checkout-data.json
    │   └── java/
    │       └── com/
    │           └── automationexercices/
    │               └── tests/
    │                   ├── BaseTest.java
    │                   ├── ui/
    │                       ├── CartTest.java
    │                       ├── ProductsTest.java
    │                       ├── ProductDetailsTest.java
    │                       ├── LoginTest.java
    │                       ├── RegisterTest.java
    │                       ├── CheckoutTest.java
    │                       ├── PaymentTest.java
    │                       └── InvoiceTest.java
    │                   └── api/
    │                       └── RegisterAPITest.java
├── .github/
    ├── dependabot.yml
    └── workflows/
    │   └── E2E Regression Pipeline.yml
├── .gitignore
├── pages/
    └── components/
    │   └── NavigationBarComponent.java
└── pom.xml

```


## Contributing

Contributions are welcome! Please fork the repository and create a pull request.

## License

This project is licensed under the MIT License.

## Contact

For questions or support, feel free to reach out to Ahmed Ashraf:
Email: [2801ahmedadel@gmail.com]
GitHub: [AhmedAdel2801](https://github.com/AhmedAdel2801)



