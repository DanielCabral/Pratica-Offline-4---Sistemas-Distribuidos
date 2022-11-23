package test.pages;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class  PortfolioPageTest extends ApplicationTest {
    Stage stage;
    @Override
    public void start (Stage stage) throws Exception {

    }

    @Before
    public void beforeEachTest() throws Exception {

    }


    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testEnglishInput () {
        clickOn("#inputField");
        write("This is a test!");
    }
}