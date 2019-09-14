package #####;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

/* Import your repository there */
/* Import your entity there */

@Controller
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @PostMapping("/#####")
    public ${Resource} store(@ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }

    @GetMapping("/#####")
    public List<${Resource}> browse() {
        return ${Resource}Repository.findAll();
    }
}
