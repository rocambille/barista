package #####;

import java.util.List;

import javax.validation.Valid;

/* Import your ${UpperName} entity here */
/* Import your ${UpperName}Repository here */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ${UpperName}Controller {
    @Autowired
    private ${UpperName}Repository ${lowerName}Repository;

    @GetMapping("/${slugs}")
    public List<${UpperName}> browse() {
        return ${lowerName}Repository.findAll();
    }

    @GetMapping("/${slugs}/{id}")
    public ${UpperName} read(@PathVariable Long id) {
        return ${lowerName}Repository.getOne(id);
    }

    @PutMapping("/${slugs}/{id}")
    public ${UpperName} edit(@Valid ${UpperName} resource) {
        return ${lowerName}Repository.save(resource);
    }

    @PostMapping("/${slugs}")
    public ${UpperName} add(@Valid ${UpperName} resource) {
        return ${lowerName}Repository.save(resource);
    }

    @DeleteMapping("/${slugs}/{id}")
    public void destroy(@PathVariable Long id) {
        ${lowerName}Repository.deleteById(id);
    }
}
