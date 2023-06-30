package ch.bbw.m183.vulnerapp.controller;

import ch.bbw.m183.vulnerapp.datamodel.UserEntity;
import ch.bbw.m183.vulnerapp.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin123")
@Validated
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public UserEntity createUser(@Valid @RequestBody UserEntity newUser) {
		return adminService.createUser(newUser);
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<UserEntity> getUsers(Pageable pageable) {
		return adminService.getUsers(pageable);
	}

	@DeleteMapping("/delete/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteUser(@PathVariable String username) {
		adminService.deleteUser(username);
	}
}
