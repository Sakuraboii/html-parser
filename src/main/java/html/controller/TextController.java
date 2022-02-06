package html.controller;

import html.entity.Text;
import html.service.TextService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/text")
@RequiredArgsConstructor
public class TextController {

    private final TextService textService;

    @PostMapping
    public List<Text> saveText(String url) throws IOException {
        return textService.saveWords(url);
    }

    @GetMapping("/url")
    public List<Text> getByUrl(@RequestBody String url){
        return textService.getByUrl(url);
    }

    @GetMapping("/all")
    public List<Text> getAll(Pageable pageable){
        return textService.getAll(pageable);
    }
}
