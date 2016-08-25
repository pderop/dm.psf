package prototype.provider.impl;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import prototype.provider.Provider;

public class Activator extends DependencyActivatorBase {

	@Override
	public void init(BundleContext bctx, DependencyManager dm) throws Exception {
		dm.add(createComponent()
		  .setImplementation(ProviderFactory.class)
		  .setInterface(Provider.class.getName(), null));
	}
	
}