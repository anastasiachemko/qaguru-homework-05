package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.components.CalendarComponent;

public class RegistrationFormTestsWithFaker {

    RegistrationPage registrationPage = new RegistrationPage();

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String userEmail = faker.internet().emailAddress();
    String userNumber = faker.phoneNumber().subscriberNumber(10);
    String userAddress = faker.address().fullAddress();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillFormTest() {

        registrationPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(userEmail)
                .setGender()
                .typeNumber(userNumber);

        CalendarComponent calendarComponent = new CalendarComponent();
        calendarComponent.setDate("10", "October", "2000");

        registrationPage
                .setSubject("English")
                .setHobbiesSports()
                .uploadFile("rafting.jpg")
                .typeAddress(userAddress)
                .setState("Haryana")
                .setCity("Karnal")
                .clickSubmit();

        registrationPage
                .checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", userEmail)
                .checkResultsValue("Gender","Female")
                .checkResultsValue("Mobile", userNumber)
                .checkResultsValue("Date of Birth","10 October,2000")
                .checkResultsValue("Subjects", "English")
                .checkResultsValue("Hobbies", "Sports")
                .checkResultsValue("Picture", "rafting.jpg")
                .checkResultsValue("Address", userAddress)
                .checkResultsValue("State and City", "Haryana Karnal");

    }
}