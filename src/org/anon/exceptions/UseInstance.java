package org.anon.exceptions;
/**
 * When designing your own higher order functions based on our specific needs, we can specify checked exceptions using the throws class
 * @author averma
 *
 * @param <T>
 * @param <X>
 */
@FunctionalInterface
public interface UseInstance<T, X extends Throwable> {
	void accept (T instance) throws X;
}
