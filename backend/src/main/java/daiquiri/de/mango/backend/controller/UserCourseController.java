package daiquiri.de.mango.backend.controller;

import daiquiri.de.mango.backend.model.UserCourse;
import daiquiri.de.mango.backend.services.UserCourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-courses")
@CrossOrigin(origins = "*")

public class UserCourseController {

    @Autowired
    private UserCourseServices userCourseServices;

    // POST: matricular un curso
    @PostMapping("/enroll")
    public ResponseEntity<?> enrollCourse(@RequestBody UserCourse userCourse) {
        String result = userCourseServices.enrollCourse(userCourse);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    // GET: obtener todos los cursos de un usuario
    @GetMapping("/{userId}")
    public ResponseEntity<List<UserCourse>> getUserCourses(@PathVariable String userId) {
        return ResponseEntity.ok(userCourseServices.getUserCourses(userId));
    }

    // GET: obtener cursos de un usuario por estado
    @GetMapping("/{userId}/status/{status}")
    public ResponseEntity<List<UserCourse>> getCoursesByStatus(@PathVariable String userId, @PathVariable String status) {
        return ResponseEntity.ok(userCourseServices.getCoursesByStatus(userId, status));
    }

    // PUT: actualizar nota
    @PutMapping("/{userId}/course/{courseCode}/grade")
    public ResponseEntity<?> updateGrade(@PathVariable String userId, @PathVariable String courseCode, @RequestParam double grade) {
        String result = userCourseServices.updateGrade(userId, courseCode, grade);
        if (result.startsWith("Error")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
