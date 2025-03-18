package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertFilteredCommandFailure;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NricPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for {@code ViewpCommand}.
 */
public class ViewpCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullNric_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewpCommand(null));
    }

    @Test
    public void execute_noNric_listAllPersons() {
        ViewpCommand viewpCommand = new ViewpCommand();
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        assertCommandSuccess(viewpCommand, model, ViewpCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validNric_personFound() throws Exception {
        Person personWithNric = new PersonBuilder().withName("John Doe").withNric("S1234567A").build();
        model.addPerson(personWithNric);
        expectedModel.addPerson(personWithNric);

        ViewpCommand viewpCommand = new ViewpCommand("S1234567A");
        expectedModel.updateFilteredPersonList(new NricPredicate("S1234567A"));

        assertCommandSuccess(viewpCommand, model,
            String.format(ViewpCommand.MESSAGE_PATIENT_FOUND, personWithNric), expectedModel);
    }

    @Test
    public void debugModelContents() {
        System.out.println("Model contains the following persons:");
        for (Person p : model.getFilteredPersonList()) {
            System.out.println("Person: " + p.getName() + ", NRIC: " + p.getNric());
        }

        // Test the specific NRIC value that's failing
        model.updateFilteredPersonList(new NricPredicate("S9999999Z"));
        System.out.println("After filtering by S9999999Z, found "
                        + model.getFilteredPersonList().size() + " persons");
    }

    @Test
    public void execute_invalidNric_throwsCommandException() {
        // Make sure we're using an NRIC that doesn't exist in the test data
        String nonExistentNric = "T0000000X"; // Different format from existing test data NRICs
        ViewpCommand viewpCommand = new ViewpCommand(nonExistentNric);
        assertFilteredCommandFailure(viewpCommand, model,
                String.format(ViewpCommand.MESSAGE_PATIENT_NOT_FOUND, nonExistentNric));
    }

    @Test
    public void equals() {
        ViewpCommand viewpFirstCommand = new ViewpCommand("S1234567A");
        ViewpCommand viewpSecondCommand = new ViewpCommand("S7654321B");
        ViewpCommand viewpThirdCommand = new ViewpCommand("S1234567A");
        ViewpCommand listCommand = new ViewpCommand();
        ViewpCommand anotherListCommand = new ViewpCommand();

        // same object -> returns true
        assertTrue(viewpFirstCommand.equals(viewpFirstCommand));

        // same values -> returns true
        assertTrue(viewpFirstCommand.equals(viewpThirdCommand));
        assertTrue(listCommand.equals(anotherListCommand));

        // different types -> returns false
        assertFalse(viewpFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewpFirstCommand.equals(null));

        // different NRIC -> returns false
        assertFalse(viewpFirstCommand.equals(viewpSecondCommand));

        // one with NRIC, one without -> returns false
        assertFalse(viewpFirstCommand.equals(listCommand));
    }
}
