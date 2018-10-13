package myTests;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Метод помеченный данной аннотацией выполняется после исполнения всех тестов 
 * с использованием аннотации {@link Test}
 * @author skhanov
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface AfterSuite {
	
}
