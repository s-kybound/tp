//package seedu.address.logic.parser;
//
//import seedu.address.logic.Messages;
//import seedu.address.logic.commands.CreateCommand;
//import seedu.address.model.person.Address;
//import seedu.address.model.person.Email;
//import seedu.address.model.person.Name;
//import seedu.address.model.internship.Internship;
//import seedu.address.model.person.Phone;
//import seedu.address.model.tag.Tag;
//import seedu.address.testutil.InternshipBuilder;
//
//import org.junit.jupiter.api.Test;
//
//import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_ROLE_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_APPLICATION_STATUS_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_REQUIREMENT_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_JANESTREET;
//import static seedu.address.logic.commands.CommandTestUtil.COMPANY_NAME_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_JANESTREET;
//import static seedu.address.logic.commands.CommandTestUtil.ROLE_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
//import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_JANESTREET;
//import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_OPTIVER;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_JANESTREET;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_OPTIVER;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.address.testutil.TypicalInternships.JANESTREET;
//import static seedu.address.testutil.TypicalInternships.OPTIVER;
//
//public class CreateCommandParserTest {
//    private CreateCommandParser parser = new CreateCommandParser();
//
//    @Test
//    public void parse_allFieldsPresent_success() {
//        Internship expectedInternship = new InternshipBuilder(OPTIVER).build();
//
//        // whitespace only preamble
//        assertParseSuccess(parser, PREAMBLE_WHITESPACE + COMPANY_NAME_DESC_OPTIVER + ROLE_DESC_OPTIVER
//                + APPLICATION_STATUS_DESC_OPTIVER + DEADLINE_DESC_OPTIVER + START_DATE_DESC_OPTIVER
//                + DURATION_DESC_OPTIVER + REQUIRE,
//                new CreateCommand(expectedInternship));
//
//
//        // multiple tags - all accepted
//        Internship expectedInternshipMultipleTags = new InternshipBuilder(OPTIVER).withTags(VALID_TAG_FRIEND,
//        VALID_TAG_HUSBAND)
//                .build();
//        assertParseSuccess(parser,
//                NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER + ADDRESS_DESC_OPTIVER + TAG_DESC_HUSBAND
//                + TAG_DESC_FRIEND,
//                new CreateCommand(expectedInternshipMultipleTags));
//    }
//
//    @Test
//    public void parse_repeatedNonTagValue_failure() {
//        String validExpectedInternshipString = NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER
//                + ADDRESS_DESC_OPTIVER + TAG_DESC_FRIEND;
//
//        // multiple names
//        assertParseFailure(parser, NAME_DESC_JANESTREET + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // multiple phones
//        assertParseFailure(parser, PHONE_DESC_JANESTREET + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // multiple emails
//        assertParseFailure(parser, EMAIL_DESC_JANESTREET + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // multiple addresses
//        assertParseFailure(parser, ADDRESS_DESC_JANESTREET + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//
//        // multiple fields repeated
//        assertParseFailure(parser,
//                validExpectedInternshipString + PHONE_DESC_JANESTREET + EMAIL_DESC_JANESTREET + NAME_DESC_JANESTREET
//                + ADDRESS_DESC_JANESTREET
//                        + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL,
//                PREFIX_PHONE));
//
//        // invalid value followed by valid value
//
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // invalid address
//        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedInternshipString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//
//        // valid value followed by invalid value
//
//        // invalid name
//        assertParseFailure(parser, validExpectedInternshipString + INVALID_NAME_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, validExpectedInternshipString + INVALID_EMAIL_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, validExpectedInternshipString + INVALID_PHONE_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // invalid address
//        assertParseFailure(parser, validExpectedInternshipString + INVALID_ADDRESS_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//    }
//
//    @Test
//    public void parse_optionalFieldsMissing_success() {
//        // zero tags
//        Internship expectedInternship = new InternshipBuilder(JANESTREET).withTags().build();
//        assertParseSuccess(parser, NAME_DESC_JANESTREET + PHONE_DESC_JANESTREET + EMAIL_DESC_JANESTREET +
//        ADDRESS_DESC_JANESTREET,
//                new CreateCommand(expectedInternship));
//    }
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, VALID_NAME_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER +
//        ADDRESS_DESC_OPTIVER,
//                expectedMessage);
//
//        // missing phone prefix
//        assertParseFailure(parser, NAME_DESC_OPTIVER + VALID_PHONE_OPTIVER + EMAIL_DESC_OPTIVER +
//        ADDRESS_DESC_OPTIVER,
//                expectedMessage);
//
//        // missing email prefix
//        assertParseFailure(parser, NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + VALID_EMAIL_OPTIVER +
//        ADDRESS_DESC_OPTIVER,
//                expectedMessage);
//
//        // missing address prefix
//        assertParseFailure(parser, NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER +
//        VALID_ADDRESS_OPTIVER,
//                expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_NAME_OPTIVER + VALID_PHONE_OPTIVER + VALID_EMAIL_OPTIVER +
//        VALID_ADDRESS_OPTIVER,
//                expectedMessage);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER + ADDRESS_DESC_OPTIVER
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
//
//        // invalid phone
//        assertParseFailure(parser, NAME_DESC_OPTIVER + INVALID_PHONE_DESC + EMAIL_DESC_OPTIVER + ADDRESS_DESC_OPTIVER
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
//
//        // invalid email
//        assertParseFailure(parser, NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + INVALID_EMAIL_DESC + ADDRESS_DESC_OPTIVER
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
//
//        // invalid address
//        assertParseFailure(parser, NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER + INVALID_ADDRESS_DESC
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser, NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER + ADDRESS_DESC_OPTIVER
//                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
//
//        // two invalid values, only first invalid value reported
//        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER + INVALID_ADDRESS_DESC,
//                Name.MESSAGE_CONSTRAINTS);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_OPTIVER + PHONE_DESC_OPTIVER + EMAIL_DESC_OPTIVER
//                        + ADDRESS_DESC_OPTIVER + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
//    }
//}
