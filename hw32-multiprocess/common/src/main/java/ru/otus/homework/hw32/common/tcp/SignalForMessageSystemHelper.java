package ru.otus.homework.hw32.common.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import static ru.otus.homework.hw32.common.tcp.SignalTypeForMessageSystem.ERROR;

@Slf4j
class SignalForMessageSystemHelper {

    private SignalForMessageSystemHelper() {
    }

    static <R> R processAnswerClient(Signal signal, SignalTcpClient client, Function<Signal, R> converter) throws InterruptedException, TimeoutException, IOException {
        return processAnswer(signal, client.sendAndGetAnswer(signal), converter);
    }

    static <R> R processAnswerServer(Signal signal, UUID uuid, SignalTcpServer server, Function<Signal, R> converter) throws InterruptedException, TimeoutException, IOException {
        return processAnswer(signal, server.sendAndGetAnswer(uuid, signal), converter);
    }

    private static <R> R processAnswer(Signal signal, Signal answer, Function<Signal, R> converter) {
        if (ERROR.name().equals(answer.getTag())) {
            throw new RuntimeException(String.valueOf(answer.getBody()));
        }
        if (signal.getTag().equals(answer.getTag())) {
            if (converter != null) {
                return converter.apply(answer);
            }
            return null;
        }
        throw new RuntimeException("Unknown answer: " + answer);
    }

    static Signal answerOk(Signal signal) {
        return answerOk(signal, null);
    }

    static Signal answerOk(Signal signal, Serializable body) {
        Signal answer = new Signal(signal.getTag(), signal.getUuid(), body);
        log.debug("signal: '{}' answer: '{}'", signal, answer);
        return answer;
    }

    static Signal answerError(Signal signal, String error) {
        Signal answer = new Signal(ERROR.name(), signal.getUuid(), error);
        log.debug("signal: '{}' answer: '{}'", signal, answer);
        return answer;
    }
}