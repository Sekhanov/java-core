package myTests;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * Метод помеченный аннотацией выполняется до исполнения всех тестов 
 * с использованием аннотации {@link Test}. 
 * Метод с аннотацией может присутствовать в единственном экземпляре в тестируемом
 * классе, иначе выбрасывается {@link RuntimeException}
 * Метод на который применяется аннотация должен быть публичным, возвращать void
 * и не должен иметь параметров
 * @author skhanov
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface BeforeSuite {
	
}
