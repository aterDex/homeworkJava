package ru.otus.homework.hw06.test;

import ru.otus.homework.provoker.api.Before;
import ru.otus.homework.provoker.api.BeforeAll;
import ru.otus.homework.provoker.api.Test;

public class Test03 {

    @BeforeAll
    public void beforeAll() {
        throw new RuntimeException("Bad before!");
    }

    @Test
    public void skipTest0() {
    }

    @Test
    public void skipTest1() {
    }

    @Test
    public void skipTest2() {
    }
}