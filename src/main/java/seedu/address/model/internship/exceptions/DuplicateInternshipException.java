package seedu.address.model.internship.exceptions;

/**
 * Signals that the operation will result in duplicate Internships (Internships are considered duplicates if they have
 * the same company name and/or role).
 */
public class DuplicateInternshipException extends RuntimeException {
    public DuplicateInternshipException() {
        super("Operation would result in duplicate internships");
    }

}

