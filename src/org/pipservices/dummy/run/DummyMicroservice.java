package org.pipservices.dummy.run;

import org.pipservices.dummy.build.*;
import org.pipservices.runtime.run.*;

public class DummyMicroservice extends Microservice {
	public DummyMicroservice() {
		super("pip-services-dummy", new DummyFactory());
	}
}
