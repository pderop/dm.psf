package prototype.provider.impl;

import org.osgi.framework.Bundle;
import org.osgi.framework.PrototypeServiceFactory;
import org.osgi.framework.ServiceRegistration;

import prototype.provider.Provider;

public class ProviderFactory implements PrototypeServiceFactory<Provider> {

	@Override
	public Provider getService(Bundle bundle, ServiceRegistration<Provider> registration) {
		return new ProviderImpl();
	}

	@Override
	public void ungetService(Bundle bundle, ServiceRegistration<Provider> registration, Provider service) {
	}

}
