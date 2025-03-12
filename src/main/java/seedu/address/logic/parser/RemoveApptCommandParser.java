package seedu.address.logic.parser;

import seedu.address.logic.commands.RemoveApptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveApptCommand object
 */
public class RemoveApptCommandParser implements Parser<RemoveApptCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveApptCommand
     * and returns an RemoveApptCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveApptCommand parse(String args) throws ParseException {
        // TODO Parse command properly once NRIC implemented
        return new RemoveApptCommand("12/03/2025 16:30");
    }

}
