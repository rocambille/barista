package #####;

import java.util.List;

import javax.validation.Valid;

/* Import your ${$Resource} entity here */
/* Import your ${Resource}Repository here */

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @GetMapping("/${resourceMapping}")
    public String ${resourceMapping}(Model model) {
        List<${Resource}> ${resourceMapping} = ${resource}Repository.findAll();
        model.addAttribute("${resourceMapping}", ${resourceMapping});
        return ""; /* Insert your template name here */
    }

    @GetMapping("/${resourceMapping}/{id}")
    public String read(Model model, @PathVariable("id") Long id) {
        ${Resource} ${resource} = ${resource}Repository.findById(id).get();
        model.addAttribute("${resource}", ${resource});
        return ""; /* Insert your template name here */
    }

    @PutMapping("/${resourceMapping}/{id}")
    public ${Resource} edit(@Valid @ModelAttribute ${Resource} ${resource}) {
        ${resource}Repository.save(${resource});
        return "";
    }

    @PostMapping("/${resourceMapping}")
    public String add(@Valid @ModelAttribute ${Resource} ${resource}) {
        ${resource}Repository.save(${resource});
        return ""; /* Insert your template name here */
    }

    @DeleteMapping("/${resourceMapping}/{id}")
    public void destroy(@PathVariable Long id) {
        ${resource}Repository.delete(
            ${resource}Repository.findById(id).get()
        ); 
    }
}