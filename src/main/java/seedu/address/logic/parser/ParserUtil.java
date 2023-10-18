package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.requirement.Requirement;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    // DELETE AFTER FULL TRANSITION
    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String companyName} into a {@code CompanyName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code companyName} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedCompanyName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedCompanyName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedCompanyName);
    }

    //DELETE AFTER FULL TRANSITION
    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String role} into a {@code Role}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code role} is invalid.
     */
    public static Role parseRole(String role) throws ParseException {
        requireNonNull(role);
        String trimmedRole = role.trim();
        if (!Role.isValidRole(trimmedRole)) {
            throw new ParseException(Role.MESSAGE_CONSTRAINTS);
        }
        return new Role(trimmedRole);
    }

    // DELTE AFTER FULL TRANSITION
    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String applicationStatus} into an {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code applicationStatus} is invalid.
     */
    public static ApplicationStatus parseApplicationStatus(String applicationStatus) throws ParseException {
        requireNonNull(applicationStatus);
        String trimmedApplicationStatus = applicationStatus.trim();
        if (!ApplicationStatus.isValidApplicationStatus(trimmedApplicationStatus)) {
            throw new ParseException(ApplicationStatus.MESSAGE_CONSTRAINTS);
        }
        return new ApplicationStatus(trimmedApplicationStatus);
    }

    /**
     * Parses a {@code String deadline} and {@code String startDate} into an {@code Deadline}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deadline} is invalid.
     */
    public static Deadline parseDeadline(String deadline, String startDate) throws ParseException {
        requireNonNull(deadline);
        String trimmedDeadline = deadline.trim();
        String trimmedStartDate = startDate.trim();

        if (!StartDate.isValidStartDate(trimmedStartDate)) {
            throw new ParseException(StartDate.MESSAGE_CONSTRAINTS);
        }

        if (!Deadline.isValidDeadline(trimmedDeadline, trimmedStartDate)) {
            throw new ParseException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(trimmedDeadline, trimmedStartDate);
    }

    // DELETE AFTER FULL TRANSITION
    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String startDate} into a {@code StartDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startDate} is invalid.
     */
    public static StartDate parseStartDate(String startDate) throws ParseException {
        requireNonNull(startDate);
        String trimmedStartDate = startDate.trim();
        if (!StartDate.isValidStartDate(trimmedStartDate)) {
            throw new ParseException(StartDate.MESSAGE_CONSTRAINTS);
        }
        return new StartDate(trimmedStartDate);
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        String trimmedDuration = duration.trim();
        if (!Duration.isValidDuration(trimmedDuration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        return new Duration(trimmedDuration);
    }

    /**
     * Parses {@code Collection<String> requirements} into a {@code Set<Requirement>}.
     */
    public static Set<Requirement> parseRequirements(Collection<String> requirements) throws ParseException {
        requireNonNull(requirements);
        final Set<Requirement> requirementSet = new HashSet<>();
        for (String requirementName : requirements) {
            requirementSet.add(parseRequirement(requirementName));
        }
        return requirementSet;
    }

    // DELETE AFTER FULL TRANSITION
    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String requirement} into a {@code Requirement}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code requirement} is invalid.
     */
    public static Requirement parseRequirement(String requirement) throws ParseException {
        requireNonNull(requirement);
        String trimmedRequirement = requirement.trim();
        if (!Requirement.isValidRequirementName(trimmedRequirement)) {
            throw new ParseException(Requirement.MESSAGE_CONSTRAINTS);
        }
        return new Requirement(trimmedRequirement);
    }

    // DELETE AFTER FULL TRANSITION
    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }
}
