package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Adds an appointment to a particular patient.
 */
public class RemoveApptCommand extends Command {

    public static final String COMMAND_WORD = "rmappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an appointment from a patient. "
            + "Parameters: "
            + "NRIC stuff goes here once implemented";

    public static final String MESSAGE_SUCCESS = "Appointment removed for the patient: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This appointment is already recorded";

    private final String toAdd;

    /**
     * Creates an AddApptCommand to add the specified {@code Person}
     */
    public RemoveApptCommand(String appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO actually pinpoint person via NRIC
        // Test person to be placed here while the implementation of NRIC is ongoing
        Person person = new Person(new Name("Test Crackers"), new Phone("123456789"),
                new Email("example@email.com"), new Address("here"), new HashSet<>());

        /*
        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
         */
        // TODO Actually remove the appointment
        person.getAppointments().removeAppointment(1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(person)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddApptCommand)) {
            return false;
        }

        RemoveApptCommand otherRemoveApptCommand = (RemoveApptCommand) other;
        return toAdd.equals(otherRemoveApptCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
