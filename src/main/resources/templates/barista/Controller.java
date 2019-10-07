package #####;

import java.util.List;

import javax.validation.Valid;

/* Import your ${UpperName} entity here */
/* Import your ${UpperName}Repository here */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
class ${UpperName}Controller {
    @Autowired
    private ${UpperName}Repository ${lowerName}Repository;

    @GetMapping("/${slugs}")
    public String browse(Model model) {
        List<${UpperName}> ${lowerName}List = ${lowerName}Repository.findAll();
        model.addAttribute("${lowerName}List", ${lowerName}List);
        return "${slugs}/browse";
    }

    @GetMapping("/${slugs}/{id}")
    public String read(Model model, Long id) {
        ${UpperName} ${lowerName} = ${lowerName}Repository.getOne(id);
        model.addAttribute("${lowerName}", ${lowerName});
        return "${slugs}/read";
    }

    @GetMapping("/${slugs}/{id}/edit")
    public String edit(Model model, Long id) {
        ${UpperName} ${lowerName} = ${lowerName}Repository.getOne(id);
        model.addAttribute("${lowerName}", ${lowerName});
        return "${slugs}/form";
    }

    @PutMapping("/${slugs}/{id}")
    public String update(@Valid ${UpperName} ${lowerName}) {
        ${lowerName} = ${lowerName}Repository.save(${lowerName});
        return "redirect:/${slugs}/" + ${lowerName}.getId();
    }

    @GetMapping("/${slugs}/create")
    public String add(Model model, ${UpperName} ${lowerName}) {
        model.addAttribute("${lowerName}", ${lowerName});
        return "${slugs}/form";
    }

    @PostMapping("/${slugs}")
    public String store(@Valid ${UpperName} ${lowerName}) {
        ${lowerName} = ${lowerName}Repository.save(${lowerName});
        return "redirect:/${slugs}/" + ${lowerName}.getId();
    }

    @DeleteMapping("/${slugs}/{id}")
    public String destroy(@PathVariable Long id) {
        ${lowerName}Repository.deleteById(id);
        return "redirect:/${slugs}";
    }
}
