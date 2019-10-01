package #####;

import java.util.List;

import javax.validation.Valid;

/* Import your ${UpperName} entity here */
/* Import your ${UpperName}Repository here */

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
class ${UpperName}Controller {
    @Autowired
    private ${UpperName}Repository ${lowerName}Repository;

    @GetMapping("/${slugs}")
    public String browse(Model model) {
        List<${UpperName}> resources = ${lowerName}Repository.findAll();
        model.addAttribute("${slugs}", resources);
        return "${slugs}/browse";
    }

    @GetMapping("/${slugs}/{id}")
    public String read(Model model, @PathVariable("id") Long id) {
        ${UpperName} resource = ${lowerName}Repository.findById(id).get();
        model.addAttribute("${lowerName}", resource);
        return "${slugs}/read";
    }

    @GetMapping("/${slugs}/{id}/edit")
    public String edit(@ModelAttribute ${UpperName} ${lowerName}) {
        return "${slugs}/form";
    }

    @PutMapping("/${slugs}/{id}")
    public String update(@Valid ${UpperName} resource) {
        resource = ${lowerName}Repository.save(resource);
        return "redirect:/${slugs}/" + resource.getId();
    }

    @GetMapping("/${slugs}/create")
    public String add(@ModelAttribute ${UpperName} resource) {
        model.addAttribute("resource", resource);
        return "${slugs}/form";
    }

    @PostMapping("/${slugs}")
    public String store(@Valid ${UpperName} resource) {
        resource = ${lowerName}Repository.save(resource);
        return "redirect:/${slugs}/" + resource.getId();
    }

    @DeleteMapping("/${slugs}/{id}")
    public String destroy(@PathVariable Long id) {
        ${lowerName}Repository.delete(
            ${lowerName}Repository.findById(id).get()
        ); 
        return "redirect:/${slugs}";
    }
}