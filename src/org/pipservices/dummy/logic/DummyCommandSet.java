package org.pipservices.dummy.logic;

import org.pipservices.dummy.data.Dummy;
import org.pipservices.runtime.commands.*;
import org.pipservices.runtime.data.*;
import org.pipservices.runtime.errors.*;
import org.pipservices.runtime.portability.*;
import org.pipservices.runtime.validation.*;

public class DummyCommandSet extends CommandSet {
	private IDummyBusinessLogic _logic;
	
	public DummyCommandSet(IDummyBusinessLogic logic) {
		// Set reference to the business logic
		this._logic = logic;

        // Register commands to the database
		addCommand(makeGetDummiesCommand());
		addCommand(makeGetDummyByIdCommand());
		addCommand(makeCreateDummyCommand());
		addCommand(makeUpdateDummyCommand());
		addCommand(makeDeleteDummyCommand());
	}

	private ICommand makeGetDummiesCommand() {
		return new Command(
			_logic,
			"get_dummies",
			new Schema()
				.withOptionalProperty("filter", "FilterParams")
				.withOptionalProperty("paging", "PagingParams")
			,
			new ICommandFunction() {
				public Object execute(String correlationId, DynamicMap args) throws MicroserviceError {
					FilterParams filter = FilterParams.fromValue(args.get("filter"));
					PagingParams paging = PagingParams.fromValue(args.get("paging"));
					return _logic.getDummies(correlationId, filter, paging);
				}
			}
		);
	}

	private ICommand makeGetDummyByIdCommand() {
		return new Command(
			_logic,
			"get_dummy_by_id",
			new Schema()
				.withProperty("dummy_id", "string"),
			new ICommandFunction() {
				public Object execute(String correlationId, DynamicMap args) throws MicroserviceError {
					String dummyId = args.getNullableString("dummy_id");
					return _logic.getDummyById(correlationId, dummyId);
				}
			}
		);
	}

	private ICommand makeCreateDummyCommand() {
		return new Command(
			_logic,
			"create_dummy",
			new Schema()
				.withProperty("dummy", "Dummy"),
			new ICommandFunction() {
				public Object execute(String correlationId, DynamicMap args) throws MicroserviceError {
					Dummy dummy = (Dummy)args.get("dummy");
					return _logic.createDummy(correlationId, dummy);
				}
			}
		);
	}

	private ICommand makeUpdateDummyCommand() {
		return new Command(
			_logic,
			"update_dummy",
			new Schema()
				.withProperty("dummy_id", "string")
				.withProperty("dummy", "any"),
			new ICommandFunction() {
				public Object execute(String correlationId, DynamicMap args) throws MicroserviceError {
					String dummyId = args.getNullableString("dummy_id");
					Object dummy = args.get("dummy");
					return _logic.updateDummy(correlationId, dummyId, dummy);
				}
			}
		);
	}
	
	private ICommand makeDeleteDummyCommand() {
		return new Command(
			_logic,
			"delete_dummy",
			new Schema()
				.withProperty("dummy_id", "string"),
			new ICommandFunction() {
				public Object execute(String correlationId, DynamicMap args) throws MicroserviceError {
					String dummyId = args.getNullableString("dummy_id");
					_logic.deleteDummy(correlationId, dummyId);
					return null;
				}
			}
		);
	}

}
