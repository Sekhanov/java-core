package myTests;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс TestingSystem принимает тестируемый класс, методы которого должны быть 
 * помечены аннотациями {@link BeforeSuite}, {@link Test}, {@link AfterSuite}
 * и исполняет данные методы в соответствии со спецификацией аннотаций.
 * @author skhanov
 *
 * @param <T> Тестируемый класс
 */
public class TestingSystem<T> {

	private Class<T> testClass;
	private Method[] methods;

	public TestingSystem(Class<T> testClass) {
		this.testClass = testClass;
		methods = testClass.getMethods();
	}

	public void start() {
		T testObject = instanceTestClass();
		runAnnotaderMethod(testObject, BeforeSuite.class);
		runTestMethods(testObject);
		runAnnotaderMethod(testObject, AfterSuite.class);
	}

	private void runTestMethods(T testObject) {
		List<Method> testMethods = new ArrayList<>();
		for (Method method : methods) {
			if (method.getAnnotation(Test.class) != null)testMethods.add(method);
			testMethods.sort((m1, m2) -> Integer.compare(m1.getAnnotation(Test.class).priority(),
					m2.getAnnotation(Test.class).priority()));
		}
		testMethods.forEach((m) -> {
			try {
				m.invoke(testObject);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		});
	}

	private void runAnnotaderMethod(T testObject, Class<? extends Annotation> annotationClass) {
		Method beforeMethod = null;
		if ((beforeMethod = getAnnotatedMethod(annotationClass)) != null) {
			try {
				beforeMethod.invoke(testObject);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	private Method getAnnotatedMethod(Class<? extends Annotation> annotationClass) {
		int annotatedMethodCount = 0;
		Method result = null;
		for (Method method : methods) {
			if (method.getDeclaredAnnotation(annotationClass) != null) {
				annotatedMethodCount++;
				result = method;
			}
			if (annotatedMethodCount > 1)
				throw new RuntimeException("@BeforeSuite annotation can by only one");

		}
		return result;
	}

	private T instanceTestClass() {
		T testObject = null;
		try {
			Constructor<T> constructor = testClass.getConstructor();
			testObject = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		return testObject;
	}

}
