package test;

import wrappers.*;
import levenshtein.*;


import static levenshtein.StructuralLevenshtein.DetailLevel.*;
import static levenshtein.StructuralLevenshtein.structuralTestFactory;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import levenshtein.LevenshteinTest;

import java.util.List;



@LevenshteinTest
public class TestManager {

    static MainWrapper<?> mainClz;

    public static MainWrapper<?> mainClz() {
        return mainClz;
    }

    @BeforeAll
    static void beforeAll() {
        mainClz = new MainWrapper<>();
    }

    void testCompilationAndSetup() {
        assertThat(mainClz).isNotNull();
        assertThat(mainClz).isInstanceOf(MainWrapper.class);

    }
    
    @TestFactory
    List<DynamicTest> strukturTests() {
        testCompilationAndSetup();
        return structuralTestFactory(
            ONE_FOR_EVERYTHING,
            mainClz
        );
    }

    @Test
    void testSmileyCtr_Speed() {
        try {
            Tests.testSmileyCtr_Speed();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testSmileyCtr_Objects() {
        try {
            Tests.testSmileyCtr_Objects();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testSmileyCtr_Layout() {
        try {
            Tests.testSmileyCtr_Layout();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testBewegen() {
        try {
            Tests.testBewegen();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testRumfliegen() {
        try {
            Tests.testRumfliegen();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testRandErreicht() {
        try {
            Tests.testRandErreicht();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }

}    

