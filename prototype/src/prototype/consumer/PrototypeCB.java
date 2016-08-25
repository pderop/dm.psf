package prototype.consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.apache.felix.dm.Component;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;

/**
 * Proxy object used to inject a dependency instance copy for each service consumers.
 *
 * @param <T>
 *            the type of the service consumer
 * @param <U>
 *            the type of the service dependency.
 */
public class PrototypeCB<T, U> {
	private final BiConsumer<T, U> consumer;
	private final Consumer<U> consumerInstance;
	private final BundleContext bc;
	private volatile Map<ServiceReference<U>, U> instances = new HashMap<>();

	static <U, V> PrototypeCB<U, V> prototype(BundleContext bc, BiConsumer<U, V> consumer) {
		return new PrototypeCB<U, V>(bc, consumer);
	}

	static <V> PrototypeCB<?, V> prototype(BundleContext bc, Consumer<V> consumer) {
		return new PrototypeCB<Object, V>(bc, consumer);
	}

	public PrototypeCB(BundleContext bc, BiConsumer<T, U> consumer) {
		this.bc = bc;
		this.consumer = consumer;
		this.consumerInstance = null;
	}

	public PrototypeCB(BundleContext bc, Consumer<U> consumer) {
		this.bc = bc;
		this.consumerInstance = consumer;
		this.consumer = null;
	}

	void bind(Component component, ServiceReference<U> ref) {
		T componentInstance = component.getInstance();
		ServiceObjects<U> objects = bc.getServiceObjects(ref);
		U service = objects.getService();
		instances.put(ref, service);
		if (consumer != null) {
			// inject service instance to the component implementation
			consumer.accept(componentInstance, service);
		} else {
			// inject service instance to any service dependency consumer
			consumerInstance.accept(service);
		}
	}

	void unbind(Component component, ServiceReference<U> ref) {
		U service = instances.remove(ref);
		bc.getServiceObjects(ref).ungetService(service);
	}
}
