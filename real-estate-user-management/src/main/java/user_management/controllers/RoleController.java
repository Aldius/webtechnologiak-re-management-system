package user_management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_management.exceptions.RoleNotFoundException;
import user_management.exceptions.UserNotFoundException;
import user_management.services.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public ResponseEntity<?> assignRole(@RequestParam String roleName, @RequestParam String username) {
		try {
			roleService.assignRole(roleName, username);

			return ResponseEntity.ok().build();
		} catch (UserNotFoundException | RoleNotFoundException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public ResponseEntity<?> removeRole(@RequestParam String roleName, @RequestParam String username) {
		try {
			roleService.removeRole(roleName, username);

			return ResponseEntity.ok().build();
		} catch (UserNotFoundException | RoleNotFoundException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(value = "/get-all", method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam String username) {
		try {
			return ResponseEntity.ok(roleService.getAll(username));
		}  catch (UserNotFoundException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
}