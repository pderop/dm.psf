package prototype.consumer;

import prototype.provider.Provider;

public class Consumer {
	
	void bind(Provider provider) {
		System.out.println("Consumer.bind: provider=" + provider);
	}
	
}
