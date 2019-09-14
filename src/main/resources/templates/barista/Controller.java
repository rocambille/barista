package #####;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/* Import your repository there */
/* Import your entity there */

@Controller
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @PostMapping("/####")
    public ${Resource} store(@ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }
}
