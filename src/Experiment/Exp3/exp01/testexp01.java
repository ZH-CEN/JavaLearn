package Experiment.Exp3.exp01;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class testexp01 {
    @ParameterizedTest
    @CsvSource({""})
    public void test1(){
        IceBox iceBox = new IceBox("Media", 12);

    }
}
