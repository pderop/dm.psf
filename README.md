A work around example showing how DM4 can deal with service scopes.

In the example, a Provider service is registered as a PrototypeServiceFactory.

At the Consumer side, a "PrototypeCB" helper class is used by the Consumer Activator in order to proxy the
injection of the Provider into the Consumer component (using a java8 method reference). So, when the consumers
arrives, the PrototypeCB helper instantiates a consumer instance using getServiceObjects method in order to
allocate a copy of the dependency instance. Then the PrototypeCB injects the Provider instance copy to the
Consumer.bind method (using a j8 method reference).




