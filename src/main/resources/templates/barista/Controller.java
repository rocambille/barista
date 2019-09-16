package #####;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* Import your repository here */
/* Import your entity here */

@Controller
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @PostMapping("/${resource}s")
    public ${Resource} add(@Valid @ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }

    @GetMapping("/${resource}s")
    public List<${Resource}> browse() {
        return ${resource}Repository.findAll();
    }

    @GetMapping("/${resource}s/{id}")
    public ${Resource} read(@PathVariable Long id) {
        return ${resource}Repository.findById(id).get();
    }

    @PutMapping("/${resource}s/{id}")
    public ${Resource} update(@PathVariable Long id, @ModelAttribute ${Resource} ${resource}) {
        ${Resource} ${resource}ToUpdate = ${resource}Repository.findById(id).get();
        if (${resource}.get*****() != null) {
            ${resource}ToUpdate.set*****(
                ${resource}.get*****()
            );
        }
        return ${resource}Repository.save(${resource}ToUpdate);
    }

    @DeleteMapping("/${resource}s/{id}")
    public void destroy(@PathVariable Long id) {
        ${resource}Repository.delete(
            ${resource}Repository.findById(id).get()
        ); 
    }
}