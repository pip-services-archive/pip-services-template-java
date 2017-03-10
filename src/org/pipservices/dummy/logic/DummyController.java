package org.pipservices.dummy.logic;

import org.pipservices.dummy.data.Dummy;
import org.pipservices.dummy.persistence.*;
import org.pipservices.runtime.*;
import org.pipservices.runtime.config.Category;
import org.pipservices.runtime.config.ComponentDescriptor;
import org.pipservices.runtime.data.*;
import org.pipservices.runtime.errors.*;
import org.pipservices.runtime.logic.*;
import org.pipservices.runtime.portability.DynamicMap;

public class DummyController extends AbstractController implements IDummyBusinessLogic {
	/**
	 * Unique descriptor for the DummyController component
	 */
	public final static ComponentDescriptor Descriptor = new ComponentDescriptor(
		Category.Controllers, "pip-services-dummies", "*", "*"
	);

	private IDummyPersistence _db;

	public DummyController() throws MicroserviceError {
        super(Descriptor);
    }

	@Override
    public void link(DynamicMap context, ComponentSet components) throws MicroserviceError {
        // Locate reference to dummy persistence component
        _db = (IDummyPersistence)components.getOneRequired(
        	new ComponentDescriptor(Category.Persistence, "pip-services-dummies", "*", "*")
    	);

        super.link(context, components);
        
        // Add commands
        DummyCommandSet commands = new DummyCommandSet(this);
        addCommandSet(commands);
	}

    public DataPage<Dummy> getDummies(String correlationId, FilterParams filter, PagingParams paging) 
		throws MicroserviceError {
    	ITiming timing = instrument(correlationId, "get_dummies");
    	try {
    		return _db.getDummies(correlationId, filter, paging);
    	} finally {
    		timing.endTiming();
    	}
    }
    
    public Dummy getDummyById(String correlationId, String dummyId) throws MicroserviceError {
    	ITiming timing = instrument(correlationId, "get_dummy_by_id");
    	try {
    		return _db.getDummyById(correlationId, dummyId);
    	} finally {
    		timing.endTiming();
    	}
    }
    
    public Dummy createDummy(String correlationId, Dummy dummy) throws MicroserviceError {
    	ITiming timing = instrument(correlationId, "create_dummy");
    	try {
    		return _db.createDummy(correlationId, dummy);
    	} finally {
    		timing.endTiming();
    	}
    }
    
    public Dummy updateDummy(String correlationId, String dummyId, Object dummy) throws MicroserviceError {
    	ITiming timing = instrument(correlationId, "update_dummy");
    	try {
    		return _db.updateDummy(correlationId, dummyId, dummy);
    	} finally {
    		timing.endTiming();
    	}
    }
    
    public void deleteDummy(String correlationId, String dummyId) throws MicroserviceError {
    	ITiming timing = instrument(correlationId, "delete_dummy");
    	try {
    		_db.deleteDummy(correlationId, dummyId);
    	} finally {
    		timing.endTiming();
    	}
    }

}
