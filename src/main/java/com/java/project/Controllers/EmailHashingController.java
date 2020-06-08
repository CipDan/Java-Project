package com.java.project.Controllers;

import com.java.project.Entities.EmailHashing;
import com.java.project.Exceptions.EmailExceptions.EmailNotFoundException;
import com.java.project.Repositories.EmailHashingRepository;
import com.java.project.Services.Hashing;
import com.java.project.Services.RequestData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Rest controller for handling requests regarding a user's email hashing.
 */
@Api(value = "Email Hashing Management API", description = "Handle email-hashing-related tasks")
@RestController
@RequestMapping("/api/v1")
public class EmailHashingController {

    @Autowired
    private EmailHashingRepository emailHashingRepository;

    //Get all email hashings.
    @ApiOperation(value = "View all stored email hashings", response = List.class)
    @GetMapping("/get_hashings")
    public List<EmailHashing> getAllHashings() {
        return emailHashingRepository.findAll();
    }

    //Obtain hashing for given email
    @ApiOperation(value = "Obtain hashing for a given email", response = String.class)
    @PostMapping("/obtain_hashcode_for_email")
    public String getHashCodeForEmail(@ApiParam(value = "email for which we want to obtain a hashcode",
            required = true, example = "anon.canon@dom.com")
                                      @Valid @RequestBody RequestData data) {
        String email = data.getEmail();
        if (emailHashingRepository.existsById(email)) {
            return emailHashingRepository.getHashCodeforGivenEmail(email);
        } else {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("Y-m-d H:m:s");
            String formattedDate = df.format(date);
            String resultedHashCode = Hashing.sha256(email + formattedDate);
            emailHashingRepository.save(new EmailHashing(email, resultedHashCode));
            return resultedHashCode;
        }
    }

    //Delete hashing for given email
    @ApiOperation(value = "Delete hashing for a given email", response = ResponseEntity.class)
    @DeleteMapping("/delete_hashcode/{email}")
    public ResponseEntity<?> deleteHashCode(@ApiParam(value = "email for which we try to delete its hashcode",
            required = true, example = "anon.canon@dom.com")
                                            @PathVariable(value = "email") String email)
            throws EmailNotFoundException {
        EmailHashing emailHashing = emailHashingRepository.findById(email)
                .orElseThrow(() -> new EmailNotFoundException(email));

        emailHashingRepository.delete(emailHashing);

        return ResponseEntity.ok().build();
    }
}
