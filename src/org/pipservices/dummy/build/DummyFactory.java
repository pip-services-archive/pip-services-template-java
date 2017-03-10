package org.pipservices.dummy.build;

import org.pipservices.dummy.clients.*;
import org.pipservices.dummy.logic.*;
import org.pipservices.dummy.persistence.*;
import org.pipservices.dummy.services.*;
import org.pipservices.runtime.build.*;

public class DummyFactory extends ComponentFactory {
	public final static DummyFactory Instance = new DummyFactory();
	
	public DummyFactory() {
		super(DefaultFactory.Instance);
		
		register(DummyFilePersistence.Descriptor, DummyFilePersistence.class);
		register(DummyMemoryPersistence.Descriptor, DummyMemoryPersistence.class);
		register(DummyController.Descriptor, DummyController.class);
		register(DummyRestClient.Descriptor, DummyRestClient.class);
		register(DummyRestService.Descriptor, DummyRestService.class);
	}
	
}
