package Application._a_Presentation;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Application.business_logic.BoundaryCommand;

import Application.business_logic.servicesCommand;

@RestController
@RequestMapping(path = { "/superapp/miniapp/{MiniAppName}" })
public class ControllerCommand {
	private servicesCommand servicesCommand;
	
	public ControllerCommand(servicesCommand servicesCommand) {
		this.servicesCommand = servicesCommand;
	}

	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public BoundaryCommand store(@RequestBody BoundaryCommand message) {
		return this.servicesCommand.createMiniAppCommand(message);
	}

	@GetMapping(
		path = { "/Credit_Card_Benefit_app/admin/miniapp/{miniAppName}{id}" }, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public BoundaryCommand getSpecificMessage(@PathVariable("id") String id) {
		Optional<BoundaryCommand> demoOp = this.servicesCommand
			.getSpecificMiniAppCommand(id);
		
		if (demoOp.isPresent()) {
			return demoOp.get();
		}else {
			throw new RuntimeException("could not find message by id: " + id);
		}
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public BoundaryCommand[] getAllMessages() {
		return this.servicesCommand
			.getAllMiniAppCommands()
			.toArray(new BoundaryCommand[0]);
	}
	
	@DeleteMapping
	public void deleteAll() {
		this.servicesCommand.deleteAllminiAppCommandes();
	}
	

}



