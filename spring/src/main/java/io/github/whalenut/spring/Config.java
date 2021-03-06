package io.github.whalenut.spring;

import io.github.whalenut.notes.core.NoteService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public NoteService noteService() {
        return new NoteService();
    }

}
