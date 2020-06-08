package com.java.project.Controllers;

import com.java.project.Entities.EmailHashing;
import com.java.project.Repositories.EmailHashingRepository;
import com.java.project.Services.Hashing;
import com.java.project.Services.RequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Web controller for handling the email hashing.
 */
@Controller
public class HasherController {

    @Autowired
    private EmailHashingRepository emailHashingRepository;

    //Loads the hasher's view
    @GetMapping("/hasher")
    public String getHashingForm(Model model) {
        model.addAttribute("request_data", new RequestData());
        return "get_hash_code_from_email";
    }

    //Receives the data sent by the hasher's view and returns in another view the obtained email hashing
    @PostMapping("/hasher")
    public String getHashCode(@ModelAttribute RequestData data, Model model) {
        String email = data.getEmail();
        if (emailHashingRepository.existsById(email)) {
            model.addAttribute("hashValue", emailHashingRepository.getHashCodeforGivenEmail(email));
        } else {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("Y-m-d H:m:s");
            String formattedDate = df.format(date);
            String resultedHashCode = Hashing.sha256(email + formattedDate);
            emailHashingRepository.save(new EmailHashing(email, resultedHashCode));
            model.addAttribute("hashValue", resultedHashCode);
        }
        return "hash_code";
    }

}
