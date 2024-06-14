package Application._a_Presentation;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Application.business_logic.BoundaryCommand;
import Application.business_logic.BoundaryUser;
import Application.business_logic.ServicesCommand;
import Application.business_logic.ServicesObject;
import Application.business_logic.ServicesUser;

@RestController
@RequestMapping(path = { "/superapp/admin" })
public class ControllerAdminUser {
	private ServicesUser servicesUser;
	private ServicesObject servicesObject;
	private ServicesCommand ServicesCommand;
	public ControllerAdminUser(ServicesUser servicesUser, ServicesObject servicesObject,
			ServicesCommand servicesCommand) {
		this.servicesUser = servicesUser;
		this.servicesObject = servicesObject;
		ServicesCommand = servicesCommand;
	}
	@DeleteMapping
	(path  = {"/users"})
	public void deleteAll() {
		this.servicesUser.deleteAllUsers();
	}
	@DeleteMapping(path = {"/objects"})
	public void deleteAllObjects() {
		this.servicesObject.deleteAllObjs();
	}
	@DeleteMapping(path = {"/miniapp"})
	public void deleteAllMiniAppsCommands() {
		this.ServicesCommand.deleteAllminiAppCommandes();
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			path = {"/users"})
	public BoundaryUser[] getAllUsers() {
		return this.servicesUser
			.getAllUsers()
			.toArray(new BoundaryUser[0]);
	}
	@GetMapping(
			path = { "/miniapp/{miniAppName}" }, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public BoundaryCommand[] getSpecificCommandsFromMiniApp(@PathVariable("miniAppName") String id) {
		
		return this.ServicesCommand.get_All_Mini_App_Commands(id)
				.toArray(new BoundaryCommand[0]);
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,
			path = {"/miniapp"})
	public BoundaryCommand[] getAllMiniAppsCommands() {
		return this.ServicesCommand
			.getAllMiniAppCommands()
			.toArray(new BoundaryCommand[0]);
	}
	@GetMapping(
			path = { "/miniapp/{miniAppName}{id}" }, 
			produces = MediaType.APPLICATION_JSON_VALUE)
		public BoundaryCommand getSpecificCommand(@PathVariable("id") String id) {
			Optional<BoundaryCommand> demoOp = this.ServicesCommand
				.getSpecificMiniAppCommand(id);
			
			if (demoOp.isPresent()) {
				return demoOp.get();
			}else {
				throw new BoundaryIsNotFoundException("could not find Command by id: \n"+ id);
			}
		}
}
