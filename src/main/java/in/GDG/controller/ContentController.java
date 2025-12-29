package in.GDG.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.GDG.model.Content;
import in.GDG.service.ContentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    // Constructor Injection
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * Upload content (PDF link, YouTube link, notes, etc.)
     */
    @PostMapping("/upload")
    public ResponseEntity<Content> uploadContent(@RequestBody Content content) {
        Content savedContent = contentService.saveContent(content);
        return new ResponseEntity<>(savedContent, HttpStatus.CREATED);
    }

    /**
     * ✅ FETCH CONTENT BY ID (REQUIRED FOR RECOMMENDATION FLOW)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContentById(@PathVariable Long id) {

        Content content = contentService.getContentById(id);

        return ResponseEntity.ok(content);
    }

    /**
     * Simple health check
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "Content service is running";
    }
}
