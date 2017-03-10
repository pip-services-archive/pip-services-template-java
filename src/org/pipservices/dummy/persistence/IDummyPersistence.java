package org.pipservices.dummy.persistence;

import org.pipservices.dummy.data.Dummy;
import org.pipservices.runtime.*;
import org.pipservices.runtime.data.*;
import org.pipservices.runtime.errors.*;

public interface IDummyPersistence extends IPersistence {
    DataPage<Dummy> getDummies(String correlationId, FilterParams filter, PagingParams paging) throws MicroserviceError;
    Dummy getDummyById(String correlationId, String dummyId) throws MicroserviceError;
    Dummy createDummy(String correlationId, Dummy dummy) throws MicroserviceError;
    Dummy updateDummy(String correlationId, String dummyId, Object dummy) throws MicroserviceError;
    void deleteDummy(String correlationId, String dummyId) throws MicroserviceError;
}
