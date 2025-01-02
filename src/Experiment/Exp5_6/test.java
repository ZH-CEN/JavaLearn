package Experiment.Exp5_6;

import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void testCeilingFan_setRate() {
        CeilingFan fan = new CeilingFan( 1,"fan");
        fan.setVoltage(0, 220);
        fan.setVoltage(1, 0);
        fan.setRate();
        fan.display();
    }

}
