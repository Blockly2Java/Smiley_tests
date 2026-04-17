package test;

import wrappers.*;


import static levenshtein.StructuralLevenshtein.DetailLevel.*;
import static levenshtein.StructuralLevenshtein.structuralTestFactory;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import levenshtein.LevenshteinTest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;



@LevenshteinTest
public class TestManager {

    static MainWrapper<?> mainClz;
    static SmileyWrapper<?> smileyWrapper;
    static Map<String, Executable> strukturExecutables;

    public static MainWrapper<?> mainClz() {
        return mainClz;
    }

    @BeforeAll
    static void beforeAll() {
        mainClz = new MainWrapper<>();
        smileyWrapper = new SmileyWrapper<>();
    }

    static void testCompilationAndSetup() {
        assertThat(mainClz).isNotNull();
        assertThat(mainClz).isInstanceOf(MainWrapper.class);
        assertThat(smileyWrapper).isNotNull();
        assertThat(smileyWrapper).isInstanceOf(SmileyWrapper.class);

    }
    
    static Stream<Arguments> strukturTestsData() {
        testCompilationAndSetup();
        strukturExecutables = new LinkedHashMap<>();
        return structuralTestFactory(
            ONE_PER_MEMBER_CATEGORY,
            mainClz, smileyWrapper
        ).stream().map(test -> {
            strukturExecutables.put(test.getDisplayName(), test.getExecutable());
            return Arguments.of(test.getDisplayName());
        });
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("strukturTestsData")
    void strukturTests(String displayName) throws Throwable {
        Executable executable = strukturExecutables.get(displayName);
        if (executable == null) {
            fail("Kein struktureller Test gefunden: " + displayName);
        }
        executable.execute();
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

