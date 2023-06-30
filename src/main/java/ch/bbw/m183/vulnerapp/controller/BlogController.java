package ch.bbw.m183.vulnerapp.controller;

import ch.bbw.m183.vulnerapp.datamodel.BlogEntity;
import ch.bbw.m183.vulnerapp.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/blog")
@Validated
public class BlogController {

	private final BlogService blogService;

	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@GetMapping
	public Page<BlogEntity> getBlogs(Pageable pageable) {
		return blogService.getBlogs(pageable);
	}

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UUID> createBlog(@Valid @RequestBody BlogEntity blog) {
		UUID createdBlogId = blogService.createBlog(blog);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogId);
	}
}
