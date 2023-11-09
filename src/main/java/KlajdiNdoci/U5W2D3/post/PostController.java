package KlajdiNdoci.U5W2D3.post;

import KlajdiNdoci.U5W2D3.utente.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public Page<Post> getPosts(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(defaultValue = "id") String orderBy){
        return postService.getPosts(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post savePost(@RequestBody Post body) {
        return postService.save(body);
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable long id) {
        return postService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int id) {
        postService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    public Post findByIdAndUpdate(@PathVariable int id, @RequestBody Post body) {
        return postService.findByIdAndUpdate(id, body);
    }
}
