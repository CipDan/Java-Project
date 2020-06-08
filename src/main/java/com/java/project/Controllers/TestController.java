package com.java.project.Controllers;

import com.java.project.Exceptions.HashCodeExceptions.InvalidHashCodeException;
import com.java.project.Exceptions.EmailExceptions.EmailNotFoundException;
import com.java.project.Repositories.EmailHashingRepository;
import com.java.project.Repositories.TestQuestion.TestQuestionRepository;
import com.java.project.Services.RequestData;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Web controller for handling the online test.
 */
@Controller
public class TestController {

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private EmailHashingRepository emailHashingRepository;

    private String currentEmail;
    private boolean userChecked;

    //Loads the authentication view
    @GetMapping("/multiple_choice_test")
    public String getHashingForm(Model model) {
        model.addAttribute("request_data", new RequestData());
        return "authenticate_for_test";
    }

    //Verifies the user and loads each question from his test in order or his score if he has answered all the questions
    @PostMapping("/multiple_choice_test")
    public String multipleChoiceTest(@ModelAttribute RequestData data, Model model) {
        if (data.getEmail() != null) {
            currentEmail = data.getEmail();
            userChecked = false;
        }
        if (!userChecked) {
            if (emailHashingRepository.existsById(currentEmail)) {
                String hashCode = data.getHashCode();
                if (emailHashingRepository.getHashCodeforGivenEmail(currentEmail).equals(hashCode)) {
                    userChecked = true;
                } else {
                    model.addAttribute("error_message", new InvalidHashCodeException(currentEmail, hashCode).getMessage());
                    return "show_error_message";
                }
            } else {
                model.addAttribute("error_message", new EmailNotFoundException(data.getEmail()).getMessage());
                return "show_error_message";
            }
        }
        String sqlFunctionResult = testQuestionRepository.returnNextQuestionOrScoring(currentEmail, data.getAnswers());
        JSONObject jsonResult = new JSONObject(sqlFunctionResult);
        if (jsonResult.has("Total")) {
            model.addAttribute("score", jsonResult.get("Total"));
            return "results";
        } else {
            model.addAttribute("question", jsonResult);
            model.addAttribute("question_order_number", testQuestionRepository.
                    getOrderNumberOfGivenQuestion(currentEmail, (Integer) jsonResult.get("id_intrebare")));
            model.addAttribute("request_data", new RequestData());
            return "test_question";
        }
    }
}
