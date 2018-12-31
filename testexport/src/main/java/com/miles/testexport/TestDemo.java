package com.miles.testexport;

import com.miles.testexport.com.miles.demo.ForkJoinDemo1;
import com.miles.testexport.com.miles.demo.MapDBDemo1;
import com.miles.testexport.com.miles.demo.WordCounter;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Miles Hoo
 * @version v1.0.0
 * @time 2018/8/28 10:20
 */
public class TestDemo {

    @Test
    public void test1() {
        ForkJoinDemo1.main(null);
    }

    @Test
    public void test2() {
        try {
            WordCounter.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test3() {
        try {
            MapDBDemo1.main(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
