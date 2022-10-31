package com.vtw.dna.employee.controller;

import com.vtw.dna.employee.Employee;
import com.vtw.dna.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping
    public Page<Employee> list(@RequestParam("page") int page,
                               @RequestParam("size") int size,
                               @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                               @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                               @RequestParam(value = "filter", defaultValue = "") String filter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Employee> employees = repository.findAllByNameContains(pageable, filter);
        return employees;
    }

    @GetMapping("/{id}")
    public Employee find(@PathVariable Long id) {
        Employee employee = repository.findById(id).orElseThrow();
        return employee;
    }

    @PostMapping
    public Employee create(@RequestBody Employee newOne) {
        repository.save(newOne);
        return newOne;
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee newOne) {
        Employee oldOne = repository.findById(id).orElseThrow();
        oldOne.update(newOne);
        repository.save(oldOne);
        return oldOne;
    }

    @DeleteMapping("/{id}")
    public Employee update(@PathVariable Long id) {
        Employee oldOne = repository.findById(id).orElseThrow();
        repository.delete(oldOne);
        return oldOne;
    }

}
