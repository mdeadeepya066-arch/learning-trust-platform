package in.GDG.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiController {

    @PostMapping("/ask")
    public Map<String, Object> askAI(@RequestBody Map<String, Object> req) {

        String question = (String) req.get("question");

        // Backend no longer gives real answer — Frontend handles subject-specific replies
        String answer = "I am analyzing your question... Please try asking about Java, DBMS, or OS 🤓";

        return Map.of(
                "answer", answer
        );
    }
}
