package ru.otus.homework.provoker.impl.mockup;

import ru.otus.homework.provoker.api.BeforeAll;
import ru.otus.homework.provoker.api.Test;

public class MockupSinglePublicTestAnnotationWithTwoBeforeAll {

    @BeforeAll
    public static void before0() {
    }

    @BeforeAll
    public static void before1() {
    }

    @Test
    public void realTest() {
    }

    public void notRealTest() {
    }
}
