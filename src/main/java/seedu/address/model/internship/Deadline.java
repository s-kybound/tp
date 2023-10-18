package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Internship's application deadline in Flagship.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String, String)}
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should only contain numbers and slashes. It must follow the form DD/MM/YYYY. It must be earlier"
                    + "than the Internship's StartDate.";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final LocalDate deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline, String startDate) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline, startDate), MESSAGE_CONSTRAINTS);
        this.deadline = LocalDate.parse(deadline, DATE_FORMATTER);
    }

    /**
     * Returns true if the given strings for deadline and start date are valid and the deadline is earlier than the
     * start date.
     *
     * @param deadlineTest A valid deadline.
     * @param startDateTest A valid start date.
     */
    public static boolean isValidDeadline(String deadlineTest, String startDateTest) {
        try {
            LocalDate deadline = LocalDate.parse(deadlineTest, DATE_FORMATTER);
            LocalDate startDate = LocalDate.parse(startDateTest, DATE_FORMATTER);
            return deadline.isBefore(startDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.deadline.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Deadline otherDeadLine = (Deadline) other;
        return this.deadline.equals(otherDeadLine.deadline);
    }

    @Override
    public int hashCode() {
        return this.deadline.hashCode();
    }
}
