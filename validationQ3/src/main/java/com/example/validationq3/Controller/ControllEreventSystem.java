package com.example.validationq3.Controller;

import com.example.validationq3.ApiResponse.ApiResponse;
import com.example.validationq3.Model.ModelEventSystem;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/EventSystem")
public class ControllEreventSystem {

    ArrayList<ModelEventSystem>array=new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity get(){
        return ResponseEntity.ok(array);
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Valid ModelEventSystem model, Errors error){

        if(error.hasErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        array.add(model);

        return  ResponseEntity.status(200).body(new ApiResponse("added"));
    }

    @PutMapping("apdate/{index}")
    public ResponseEntity apdate(@PathVariable int index,@RequestBody @Valid ModelEventSystem model,Errors error){

        if(error.hasErrors()){
            String message=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        array.set(index,model);

        return ResponseEntity.status(200).body(new ApiResponse("apdated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity delete(@PathVariable int index){

        array.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("delete"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity searchById(@PathVariable String id){
        ArrayList<ModelEventSystem>shearched=new ArrayList<>();
        for (ModelEventSystem searchId:array) {
            if (searchId.getID().equals(id))
                shearched.add(searchId);
        }
        return  ResponseEntity.status(200).body(new ApiResponse("searched"));
    }

    @PutMapping("/change/{index}/{Capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index, @PathVariable int Capacity) {


        ModelEventSystem changes = array.get(index);
        if (changes.getCapacity() == Capacity) {
            changes.setCapacity(Capacity);
            array.set(index, changes);
            return ResponseEntity.status(200).body(new ApiResponse("Successfully change"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Sorry I can't change!!"));
    }





}
