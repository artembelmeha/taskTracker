package eurekaserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EurekaServerApplicationTest {

    @Test
    public void TestExample(){
        assertThat("bre".contains("r")).isTrue();
    }

    @Test
    public void TestExample2(){
        assertThat("bre".contains("e")).isTrue();
    }
}

