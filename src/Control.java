/************************************************************
 * Control Class                                            *
 *                                                          *
 * Eoghan McGough                                           *
 *                                                          *
 * This is the main class that initializes and starts the   *
 * application, creating the main screen and setting up the *
 * program flow.                                            *
 ************************************************************/

public class Control {
    public static void main(String[] args) throws Exception {
        Screen screen = new Screen("My Screen", 1080, 200);
        screen.createScreen();
    }
}