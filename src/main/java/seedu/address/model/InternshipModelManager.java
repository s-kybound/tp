package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.internship.Internship;

/**
 * Represents the in-memory model of the internship book data.
 */
public class InternshipModelManager implements InternshipModel {
    private static final Logger logger = LogsCenter.getLogger(InternshipModelManager.class);

    private final InternshipBook internshipBook;
    private final InternshipUserPrefs userPrefs;

    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public InternshipModelManager(ReadOnlyInternshipBook internshipBook, ReadOnlyInternshipUserPrefs userPrefs) {
        requireAllNonNull(internshipBook, userPrefs);

        logger.fine("Initializing with internship book: " + internshipBook + " and user prefs " + userPrefs);

        this.internshipBook = new InternshipBook(internshipBook);
        this.userPrefs = new InternshipUserPrefs(userPrefs);

        this.filteredInternships = new FilteredList<>(this.internshipBook.getInternshipList());
    }

    public InternshipModelManager() {
        this(new InternshipBook(), new InternshipUserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyInternshipUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyInternshipUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getInternshipBookFilePath() {
        return userPrefs.getInternshipFilePath();
    }

    @Override
    public void setInternshipBookFilePath(Path internshipBookFilePath) {
        requireNonNull(internshipBookFilePath);
        userPrefs.setInternshipFilePath(internshipBookFilePath);
    }

    @Override
    public void setInternshipBook(ReadOnlyInternshipBook internshipBook) {
        this.internshipBook.resetData(internshipBook);
    }

    @Override
    public ReadOnlyInternshipBook getInternshipBook() {
        return internshipBook;
    }


    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return this.internshipBook.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internshipBook.removeInternship(target);
    }


    @Override
    public void createInternship(Internship internship) {
        internshipBook.createInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);

        internshipBook.setInternship(target, editedInternship);
    }


    //=========== Filtered Internship List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Internship} backed by the internal list of
     * {@code versionedInternshipBook}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;

    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        this.filteredInternships.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipModelManager)) {
            return false;
        }

        InternshipModelManager otherModelManager = (InternshipModelManager) other;
        return internshipBook.equals(otherModelManager.internshipBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredInternships.equals(otherModelManager.filteredInternships);
    }

}
