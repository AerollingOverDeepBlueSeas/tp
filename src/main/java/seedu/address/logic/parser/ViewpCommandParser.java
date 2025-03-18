package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ViewpCommand object.
 */
public class ViewpCommandParser implements Parser<ViewpCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewpCommand
     * and returns a ViewpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewpCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewpCommand.MESSAGE_USAGE));
        }

        return new ViewpCommand(trimmedArgs);
    }
}
