package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class RemarkCommandTest {

    private static final String MESSAGE_NOT_IMPLEMENTED_YET = "This command is not implemented yet";

    @Test
    public void execute() {
        Model model = new ModelManager();
        assertCommandFailure(new RemarkCommand(), model, MESSAGE_NOT_IMPLEMENTED_YET);
    }


}
