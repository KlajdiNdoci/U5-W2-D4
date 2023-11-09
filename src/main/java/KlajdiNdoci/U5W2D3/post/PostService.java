package KlajdiNdoci.U5W2D3.post;

import KlajdiNdoci.U5W2D3.exceptions.NotFoundException;
import KlajdiNdoci.U5W2D3.utente.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Page<Post> getPosts(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return postRepository.findAll(pageable);
    }

    public Post save(Post body) {
        postRepository.save(body);
        return body;
    }

    public Post findById(long id) throws NotFoundException {
        Post found = null;
        for (Post post : postRepository.findAll()) {
            if (post.getId() == id) {
                found = post;
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return found;
        }
    }

    public void findByIdAndDelete(long id) throws NotFoundException {
        Post found = this.findById(id);
        postRepository.delete(found);
    }

    public Post findByIdAndUpdate (int id, Post body) throws NotFoundException{
        Post found = null;

        for (Post post : postRepository.findAll()) {
            if (post.getId() == id) {
                found = post;
                found.setId(id);
                found.setCover(body.getCover());
                found.setCategoria(body.getCategoria());
                found.setTitolo(body.getTitolo());
                found.setTempoDiLettura(body.getTempoDiLettura());
                found.setContenuto(body.getContenuto());
            }
        }
        if (found == null) {
            throw new NotFoundException(id);
        } else {
            return found;
        }
    }

}
