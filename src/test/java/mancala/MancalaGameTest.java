/***************
 * this test does nothing but check that methods exist.  It does not check the types of
 * expected return values, but it does check that a method has a return type if one is expected.
 * This type of testing is not what you need to do for unit testing.   It has been created only
 * to give you an easy way to determine if you have missing methods in your implementation */

package mancala;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;


public class MancalaGameTest {

    @Test
    void testMancalaGameMethodsExist() {
        Method[] methods = MancalaGame.class.getDeclaredMethods();

        // Assertions for void methods
        assertAll(
            () -> assertTrue(hasVoidMethod(methods, "setPlayers", Player.class, Player.class), "setPlayers method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "setCurrentPlayer", Player.class), "setCurrentPlayer method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "setBoard", Board.class), "setBoard method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "startNewGame"), "startNewGame method is missing")
        );

        // Assertions for methods with non-void return types
        assertAll(
            () -> assertTrue(hasMethod(methods, "getPlayers"), "getPlayers method is missing"),
            () -> assertTrue(hasMethod(methods, "getCurrentPlayer"), "getCurrentPlayer method is missing"),
            () -> assertTrue(hasMethod(methods, "getBoard"), "getBoard method is missing"),
            () -> assertTrue(hasMethod(methods, "getNumStones", int.class), "getNumStones method is missing"),
            () -> assertTrue(hasMethod(methods, "move", int.class), "move method is missing"),
            () -> assertTrue(hasMethod(methods, "getStoreCount", Player.class), "getStoreCount method is missing"),
            () -> assertTrue(hasMethod(methods, "getWinner"), "getWinner method is missing"),
            () -> assertTrue(hasMethod(methods, "isGameOver"), "isGameOver method is missing"),
            () -> assertTrue(hasMethod(methods, "toString"), "toString method is missing")
        );
    }

    private boolean hasVoidMethod(Method[] methods, String methodName, Class<?>... parameterTypes) {
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getReturnType() == void.class &&
                parameterTypesMatch(method.getParameterTypes(), parameterTypes)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasMethod(Method[] methods, String methodName, Class<?>... parameterTypes) {
        for (Method method : methods) {
            if (method.getName().equals(methodName) && method.getReturnType() != void.class &&
                parameterTypesMatch(method.getParameterTypes(), parameterTypes)) {
                return true;
            }
        }
        return false;
    }

    private boolean parameterTypesMatch(Class<?>[] parameterTypes, Class<?>... expectedTypes) {
        if (parameterTypes.length != expectedTypes.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].equals(expectedTypes[i])) {
                return false;
            }
        }
        return true;
    }
}
