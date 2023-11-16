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
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void testPlayerMethodsExist() {
        Method[] methods = Player.class.getDeclaredMethods();

        // Assertions for void methods
        assertAll(
            () -> assertTrue(hasVoidMethod(methods, "setName", String.class), "setName method is missing"),
            () -> assertTrue(hasVoidMethod(methods, "setStore", Store.class), "setStore method is missing")
        );

        // Assertions for methods with non-void return types
        assertAll(
            () -> assertTrue(hasMethod(methods, "getName"), "getName method is missing"),
            () -> assertTrue(hasMethod(methods, "getStore"), "getStore method is missing"),
            () -> assertTrue(hasMethod(methods, "getStoreCount"), "getStoreCount method is missing"),
            () -> assertTrue(hasMethod(methods, "toString"), "toString method is missing")
        );
    }

    @Test
    void testPlayerConstructors() {
        Player player1 = new Player();
        Player player2 = new Player("Alice");

        assertNotNull(player1, "Default constructor should create an instance");
        assertNotNull(player2, "Parameterized constructor should create an instance");
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
