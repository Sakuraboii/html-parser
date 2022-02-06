package html.service;

import html.entity.Text;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface TextService {

    List<Text> saveWords(String url) throws IOException;

    List<Text> getByUrl(String url);

    List<Text> getAll(Pageable pageable);
}
