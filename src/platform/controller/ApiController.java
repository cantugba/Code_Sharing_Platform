package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.service.CodeService;
import platform.util.Util;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {

    private CodeService service;

    public ApiController() {
    }

    @Autowired
    public ApiController(CodeService service) {
        this.service = service;
    }

    // GET /api/code/N should return JSON with the N-th uploaded code snippet.
    @GetMapping(path = "/api/code/{id}", produces = "application/json;charset=UTF-8")
    public Code getApiCode(@PathVariable("id") int id) {
        return service.getCodeFromStorage(id);
    }

    //GET /api/code/latest should return a JSON array with 10 most recently uploaded code snippets sorted from the newest to the oldest.
    @GetMapping(path = "/api/code/latest", produces = "application/json;charset=UTF-8")
    public Object[] getApiLatestCode() {
        List<Code> responseCode = new ArrayList<>();

        for (int i = service.lastIdRepository(); i >= service.outputLimitId(); i--) {
            Code eachCode = service.getCodeFromStorage(i);
            responseCode.add(eachCode);
        }
        return responseCode.toArray();
    }

    // POST /api/code/new should take a JSON object with a single field code, use it as the current code snippet, and return JSON with a single field id. ID is the unique number of the code snippet that can help you access it via the endpoint GET /code/N
    @PostMapping(path = "/api/code/new", produces = "application/json;charset=UTF-8")
    public String setApiCode(@RequestBody Code newCode) {
        Code responseCode = new Code();
        responseCode.setCode(newCode.getCode());
        responseCode.setTitle("Code");
        responseCode.setDate(Util.getCurrentDateTime());
        service.addCodeToStorage(responseCode);
        String response = "{ \"id\" : \"" + responseCode.getId() + "\" }";
        return response;
    }

}