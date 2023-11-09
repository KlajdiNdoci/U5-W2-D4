package KlajdiNdoci.U5W2D3.controllers;

import KlajdiNdoci.U5W2D3.entities.Post;
import KlajdiNdoci.U5W2D3.exceptions.BadRequestException;
import KlajdiNdoci.U5W2D3.payloads.posts.NewPostDTO;
import KlajdiNdoci.U5W2D3.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public Post savePost(@RequestBody @Validated NewPostDTO body, BindingResult validation) {
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }else {
            try {
                return postService.save(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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
