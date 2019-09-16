package #####;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* Import your repository here */
/* Import your entity here */

@RestController
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @GetMapping("/${resource}s")
    public List<${Resource}> browse() {
        return ${resource}Repository.findAll();
    }

    @GetMapping("/${resource}s/{id}")
    public ${Resource} read(@PathVariable Long id) {
        return ${resource}Repository.findById(id).get();
    }

    @PutMapping("/${resource}s/{id}")
    public ${Resource} edit(@Valid @ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }

    @PostMapping("/${resource}s")
    public ${Resource} add(@Valid @ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }

    @DeleteMapping("/${resource}s/{id}")
    public void destroy(@PathVariable Long id) {
        ${resource}Repository.delete(
            ${resource}Repository.findById(id).get()
        ); 
    }
}