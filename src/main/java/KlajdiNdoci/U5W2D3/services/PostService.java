package KlajdiNdoci.U5W2D3.services;

import KlajdiNdoci.U5W2D3.entities.Post;
import KlajdiNdoci.U5W2D3.entities.Utente;
import KlajdiNdoci.U5W2D3.exceptions.NotFoundException;
import KlajdiNdoci.U5W2D3.payloads.posts.NewPostDTO;
import KlajdiNdoci.U5W2D3.repositories.PostRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Page<Post> getPosts(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return postRepository.findAll(pageable);
    }

    public Post save(NewPostDTO body) throws IOException {
        Post newPost = new Post();
        newPost.setCategoria(body.categoria());
        newPost.setTitolo(body.titolo());
        newPost.setContenuto(body.contenuto());
        newPost.setCover(body.cover());
        newPost.setTempoDiLettura(Integer.parseInt(body.tempoDiLettura()));
        postRepository.save(newPost);
        return newPost;
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

    public String uploadPicture(MultipartFile file, long id) throws IOException {
        Post post = postRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        String url = (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        post.setCover(url);
        postRepository.save(post);
        return url;
    }

}
