package platform.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController

public class Controller {

    private final String titleData = "Code";
    private final String codeData =  "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";

    private final Code code = new Code(titleData,codeData,getCurrentTime);

    public Controller() {}

    @GetMapping(path ="/api/code", produces = "application/json;charset=UTF-8")
    public Code getApiCode() {
        return code;
    }

    @GetMapping(path = "/code",produces = "text/html")
    public ResponseEntity<String> getHtmlCode() {
        String sendCode = "<title>" + code.getTitle() + "</title>"
                + "<body>"
                + "<span id=\"load_date\">" + code.getDate() + "</span>"
                + "<pre id=\"code_snippet\">" + code.getCode() + "</pre>"
                + "</body>";

        return ResponseEntity.ok().body(sendCode);
    }

    @PostMapping(value = "/api/code/new", consumes = "application/json; charset=utf-8")
    public String setApiCode(@RequestBody Code recode) {
        code.setCode(recode.getCode());
        code.setTitle("Code");
        code.setDate(getCurrentTime());

        return "{}";
    }

    public String getCurrentTime(){
        LocalDateTime today = new LocalDateTime.now();
        DateTimeFormatter formatter = new DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return today.format(formatter);
    }

}