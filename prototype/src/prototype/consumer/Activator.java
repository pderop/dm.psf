package prototype.consumer;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import prototype.provider.Provider;

import static prototype.consumer.PrototypeCB.prototype;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext bc, DependencyManager dm) throws Exception {
		dm.add(createComponent()
		  .setImplementation(Consumer.class)
		  .add(createServiceDependency().setService(Provider.class).setCallbacks(prototype(bc, Consumer::bind), "bind", null)));		
		
		dm.add(createComponent()
		  .setImplementation(Consumer.class)
		  .add(createServiceDependency().setService(Provider.class).setCallbacks(prototype(bc, Consumer::bind), "bind", null)));		
	}

}
