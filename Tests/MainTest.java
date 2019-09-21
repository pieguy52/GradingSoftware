import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void fractionToDoubleTest(){
        assertEquals(.5, Main.fractionToDouble("1/2"));
        assertEquals(.3, Main.fractionToDouble("1/3"), .1f);
        assertEquals(.3, Main.fractionToDouble(".3"));
    }

}