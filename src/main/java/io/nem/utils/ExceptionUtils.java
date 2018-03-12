package io.nem.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * Static class that contains helper functions for dealing with exceptions.
 */
public class ExceptionUtils {

	private ExceptionUtils() {
	}


	/**
	 * Propagates checked exceptions as a specific runtime exception.
	 *
	 * @param callable The function.
	 * @param wrap A function that wraps an exception in a runtime exception.
	 * @param <T> The function return type.
	 * @param <E> The specific exception type.
	 * @return The function result.
	 */
	public static <T, E extends RuntimeException> T propagate(
			final Callable<T> callable,
			final Function<Exception, E> wrap) {
		try {
			return callable.call();
		} catch (final ExecutionException e) {
			if (RuntimeException.class.isAssignableFrom(e.getCause().getClass())) {
				throw (RuntimeException)e.getCause();
			}
			throw wrap.apply(e);
		} catch (final RuntimeException e) {
			throw e;
		} catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new IllegalStateException(e);
		} catch (final Exception e) {
			throw wrap.apply(e);
		}
	}
}
