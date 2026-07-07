# i2i Academy - Test Automation (Homework 10)

This repository contains the source code, configurations, and test suites implemented for the **i2i Academy Test Automation** training homework.

## Project Scope
The project demonstrates automated testing at different layers of an application stack:
1. **Web UI Automation:** Login sequence automation on an e-commerce platform using Selenium WebDriver and JUnit 5.
2. **API Automation:** API request validation (GET and POST methods) using REST Assured.
3. **Performance & Stress Testing:** High concurrent load simulation on a local Nginx mock endpoint using Apache JMeter.

---

## Tech Stack
* **Language:** Java 11
* **Build Tool:** Apache Maven 3.9.6
* **Testing Libraries:** JUnit 5 (Jupiter), REST Assured, Selenium WebDriver, WebDriverManager
* **Performance Tools:** Nginx (running via Docker), Apache JMeter 5.6.3

---

## Getting Started

### Prerequisites
* **Java Development Kit (JDK 11)** or higher.
* **Docker** (for running the Nginx server).
* **Apache JMeter** (to execute stress test scripts).

---

## 1. Running Web UI and API Tests
The tests can be compiled and executed directly from the terminal using Maven:

```bash
./apache-maven-3.9.6/bin/mvn clean test
```

### Web UI Automation (`LoginAutomationTest.java`)
* Navigates to `https://www.saucedemo.com/`.
* Automates the login process by locating username, password, and submit elements.
* Asserts successful login by verifying the redirect URL and shopping cart container presence.

### API Automation (`ApiAutomationTest.java`)
* Tests public endpoints on `https://reqres.in`.
* Sends a `GET` request to retrieve user info and asserts response code (`200 OK`), payload data, and response latency.
* Sends a `POST` request to create a user and asserts request payload reflection, ID creation, and response code (`201 Created`).

---

## 2. Running Nginx & JMeter Stress Tests

### Step 1: Start Nginx Mock Server
Launch the pre-configured Nginx instance in a Docker container:
```bash
docker run -d -p 8080:8080 -v "$(pwd)/nginx-docker.conf:/etc/nginx/nginx.conf" --name nginx-stress-test nginx
```
Verify the server is running by accessing `http://localhost:8080/api/dummy` in your browser.

### Step 2: Run JMeter Load Test
1. Launch **Apache JMeter**.
2. Open `nginx_stress_test.jmx` from this repository.
3. Run the test plan (green Play button) to send **500 requests** (50 concurrent users × 10 loops).
4. Review results in the **View Results Tree** and **Summary Report** listeners.

### Step 3: Tear Down Nginx
Stop and remove the Docker container after testing:
```bash
docker rm -f nginx-stress-test
```
