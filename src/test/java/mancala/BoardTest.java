/***************
 * This test serves the sole purpose of verifying the existence of methods in the class.
 * It does not validate the types of expected return values, but it does confirm the presence of a return type if expected.
 * Please note that this form of testing is not suitable for comprehensive unit testing.
 * It has been designed solely as a tool to identify missing methods within your implementation.
 */

package mancala;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class BoardTest {

    @Test
    void testBoardMethodsExist() {
        Method[] methods = Board.class.getDeclaredMethods();

        // Assertions for void methods
        assertAll(
            () -> assertTrue(hasVoidMethod(methods, "resetBoard"), "resetBoard method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "initializeBoard"), "initializeBoard method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "setUpPits"), "setUpPits method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "setUpStores"), "setUpStores method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "registerPlayers", Player.class, Player.class), "registerPlayers method is missing")
        );

        // Assertions for methods with non-void return types
        assertAll(
            () -> assertTrue(hasMethod(methods, "moveStones", int.class, Player.class), "moveStones method is missing"),
            () -> assertTrue(hasMethod(methods, "distributeStones", int.class), "distributeStones method is missing"),
            () -> assertTrue(hasMethod(methods, "captureStones", int.class), "captureStones method is missing"),
            () -> assertTrue(hasMethod(methods, "getPits"), "getPits method is missing"),
            () -> assertTrue(hasMethod(methods, "getStores"), "getStores method is missing"),
            () -> assertTrue(hasMethod(methods, "isSideEmpty",int.class), "isSideEmpty method is missing"),            
            () -> assertTrue(hasMethod(methods, "getNumStones", int.class), "getNumStones method is missing"),
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
