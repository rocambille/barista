package #####;

import java.util.List;

import javax.validation.Valid;

/* Import your ${$Resource} entity here */
/* Import your ${Resource}Repository here */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @GetMapping("/${resourceMapping}")
    public List<${Resource}> browse() {
        return ${resource}Repository.findAll();
    }

    @GetMapping("/${resourceMapping}/{id}")
    public ${Resource} read(@PathVariable Long id) {
        return ${resource}Repository.findById(id).get();
    }

    @PutMapping("/${resourceMapping}/{id}")
    public ${Resource} edit(@Valid @ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }

    @PostMapping("/${resourceMapping}")
    public ${Resource} add(@Valid @ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }

    @DeleteMapping("/${resourceMapping}/{id}")
    public void destroy(@PathVariable Long id) {
        ${resource}Repository.delete(
            ${resource}Repository.findById(id).get()
        ); 
    }
}