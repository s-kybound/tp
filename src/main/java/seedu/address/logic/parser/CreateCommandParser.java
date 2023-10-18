package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.CreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;


/**
 * Parses input arguments and creates a new CreateCommand object
 */
public class CreateCommandParser implements InternshipParser<CreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateCommand
     * and returns an CreateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public CreateCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                        PREFIX_DEADLINE, PREFIX_START_DATE, PREFIX_DURATION, PREFIX_REQUIREMENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                PREFIX_DEADLINE, PREFIX_START_DATE, PREFIX_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                PREFIX_DEADLINE, PREFIX_START_DATE, PREFIX_DURATION);

        CompanyName companyName = ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());

        ApplicationStatus applicationStatus = ParserUtil.parseApplicationStatus(
                argMultimap.getValue(PREFIX_APPLICATION_STATUS).get()
        );
        Deadline deadline = ParserUtil.parseDeadline(
                argMultimap.getValue(PREFIX_DEADLINE).get(),
                argMultimap.getValue(PREFIX_START_DATE).get()
        );
        StartDate startDate = ParserUtil.parseStartDate(argMultimap.getValue(PREFIX_START_DATE).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());
        Set<Requirement> requirementList = ParserUtil.parseRequirements(argMultimap.getAllValues(PREFIX_REQUIREMENT));
        Internship internship = new Internship(
                companyName, role, applicationStatus, deadline, startDate, duration, requirementList
        );

        return new CreateCommand(internship);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
