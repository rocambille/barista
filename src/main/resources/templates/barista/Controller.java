package #####;

import org.springframework.stereotype.Controller;

/* Import your repository there */

@Controller
class ${Resource}Controller {
    @Autowired
    private ${Resource}Repository ${resource}Repository;

    @PostMapping("/####")
    public ${Resource} store(@ModelAttribute ${Resource} ${resource}) {
        return ${resource}Repository.save(${resource});
    }
}
