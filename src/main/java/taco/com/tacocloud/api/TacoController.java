package taco.com.tacocloud.api;

import org.springframework.web.bind.annotation.RestController;

import taco.com.tacocloud.models.Taco;
import taco.com.tacocloud.repositories.TacoRepository;

import org.apache.el.stream.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(path="/api/tacos",produces="application.json")
@CrossOrigin(origins="http://tacocloud:8080")
public abstract class TacoController {
    private TacoRepository tacoRepo;

    public TacoController(TacoRepository tacoRepo){
        this.tacoRepo = tacoRepo;
    }

    @GetMapping(params="recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12,Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }


    @GetMapping("/{id}")
    public Optional<Taco> tacoById(@PathVariable("id") Long id) {
      return tacoRepo.findById(id);
    }

    
}
