package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.JANESTREET;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;

public class JsonAdaptedInternshipTest {

    private static final String INVALID_COMPANY_NAME = "Cit@del";
    private static final String INVALID_APPLICATION_STATUS = "Y3t to apply";
    private static final String INVALID_DEADLINE = "123123123/1-11/1";
    private static final String INVALID_DURATION = " ";
    private static final String INVALID_ROLE = "C0ff33 M@k&r";
    private static final String INVALID_START_DATE = "50/50/-1111";
    private static final String INVALID_REQUIREMENT = " ";

    private static final String VALID_COMPANY_NAME = JANESTREET.getCompanyName().toString();
    private static final String VALID_APPLICATION_STATUS = JANESTREET.getApplicationStatus().toString();
    private static final String VALID_DEADLINE = JANESTREET.getDeadline().toString();
    private static final String VALID_DURATION = JANESTREET.getDuration().toString();
    private static final String VALID_ROLE = JANESTREET.getRole().toString();
    private static final String VALID_START_DATE = JANESTREET.getStartDate().toString();
    private static final List<JsonAdaptedRequirement> VALID_REQUIREMENTS = JANESTREET.getRequirements().stream()
            .map(JsonAdaptedRequirement::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(JANESTREET);
        assertEquals(JANESTREET, internship.toModelType());
    }

    @Test
    public void toModelType_invalidCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(
                INVALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE,
                VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullCompanyName_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(
                null, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE,
                VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CompanyName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, INVALID_ROLE,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, null,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                INVALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = ApplicationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullApplicationStatus_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                null, VALID_DEADLINE, VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ApplicationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, INVALID_DEADLINE, VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, null, VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, INVALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = StartDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, null, VALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StartDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE, INVALID_DURATION, VALID_REQUIREMENTS);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE, null, VALID_REQUIREMENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, internship::toModelType);
    }

    @Test
    public void toModelType_invalidRequirements_throwsIllegalValueException() {
        List<JsonAdaptedRequirement> invalidRequirements = new ArrayList<>(VALID_REQUIREMENTS);
        invalidRequirements.add(new JsonAdaptedRequirement(INVALID_REQUIREMENT));
        JsonAdaptedInternship internship = new JsonAdaptedInternship(VALID_COMPANY_NAME, VALID_ROLE,
                VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE, VALID_DURATION, invalidRequirements);
        assertThrows(IllegalValueException.class, internship::toModelType);
    }

}
