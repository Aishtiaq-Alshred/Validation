package com.example.validationq2.Cntroller;


import com.example.validationq2.ApiResponse.ApiResponse;
import com.example.validationq2.Model.ModelTrackerSystem;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/api/v1/TrackerSystem")
@RestController

public class TrackerSystem {


    ArrayList<ModelTrackerSystem>array=new ArrayList<>();

@GetMapping("/get")
    public ResponseEntity getadd(){
    return ResponseEntity.ok(array);
}


@PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid ModelTrackerSystem model, Errors error){
    if(error.hasErrors()){
        String message=error.getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }

  array.add(model);
    return ResponseEntity.status(200).body(new ApiResponse(" added"));
}


 @PutMapping("/update/{index}")
    public ResponseEntity update(@PathVariable  int index, @RequestBody @Valid ModelTrackerSystem model ,Errors error){
    if(error.hasErrors()){
        String message =error.getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.status(400).body(message);
    }
    array.set(index,model);
    return ResponseEntity.status(200).body(new ApiResponse(" apdate"));

 }

 @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index){
    array.remove(index);
     return ResponseEntity.status(200).body(new ApiResponse(" deleted"));

 }

    @PutMapping("/change/{index}")
    public ResponseEntity changeStatus(@PathVariable int index) {
        if (index < 0 || index >= array.size()) {
            return ResponseEntity.status(404).body(new ApiResponse("Task not found"));
        }
        ModelTrackerSystem task = array.get(index);

        if (task.getStatus().equals("not done")) {
            task.setStatus("done");
        } else {
            task.setStatus("not done");
        }
        array.set(index, task);
        return ResponseEntity.ok(new ApiResponse("Task status updated successfully"));
    }

    @GetMapping("/search/{titles}")
    public ResponseEntity searchTask(@PathVariable String titles) {
        for (ModelTrackerSystem search : array) {
            if (search.getTitle().equals(titles)) {
                return ResponseEntity.ok(new ApiResponse("Task found: " + search));
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Task not found"));
    }

    @GetMapping("/company/{company}")
    public ResponseEntity display(@PathVariable String company) {
        ArrayList<String> projectNames = new ArrayList<>();

        for (ModelTrackerSystem model : array) {
            if (model.getCompanyName().equals(company)) {
                projectNames.add(model.getCompanyName());
            }
        }

        if (projectNames.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponse("No projects found for the specified company."));
        }

        return ResponseEntity.ok(new ApiResponse("All projects for the company: " + projectNames));
    }







}
