package org.example;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {
    private final StudentService studentService;

    // Constructor to inject StudentService
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET: Fetch all students
    @GET
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET: Fetch a single student by ID
    @GET
    @Path("/{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return Response.ok(student).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    // POST: Create a new student
    @POST
    public Response createStudent(Student student) {
        studentService.createOrUpdateStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    // DELETE: Delete a student
    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // PUT: Update a student
    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        student.setId(id); // Ensure the student has the correct ID
        studentService.createOrUpdateStudent(student);
        return Response.ok(student).build();
    }
}
