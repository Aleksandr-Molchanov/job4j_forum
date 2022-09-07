package ru.job4j.forum.control;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Date;
import java.util.Optional;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageAddPost() throws Exception {
        this.mockMvc.perform(get("/formAddPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("addPost"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageDescriptionPost() throws Exception {
        Post post = Post.of(1, "name", "description", new Date(System.currentTimeMillis()));
        when(posts.findById(1)).thenReturn(Optional.of(post));
        mockMvc.perform(get("/descriptionPost/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageEditPost() throws Exception {
        Post post = Post.of(1, "name", "description", new Date(System.currentTimeMillis()));
        when(posts.findById(1)).thenReturn(Optional.of(post));
        mockMvc.perform(get("/formEditPost/{id}", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("editPost"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageDeletePost() throws Exception {
        Post post = Post.of(1, "name", "description", new Date(System.currentTimeMillis()));
        when(posts.findById(1)).thenReturn(Optional.of(post));
        mockMvc.perform(get("/deletePost/{id}", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/index"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageSavePost() throws Exception {
        this.mockMvc.perform(post("/savePost")
                        .param("name", "Куплю ладу-грант. Дорого.")
                        .param("description", "description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).addPost(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("description"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageUpdatePost() throws Exception {
        this.mockMvc.perform(post("/updatePost")
                        .param("name", "Куплю ладу-грант. Дорого.")
                        .param("description", "description"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).update(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("description"));
    }
}