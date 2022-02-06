package html.service.impl;

import html.entity.Text;
import html.repository.TextRepository;
import html.service.TextService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TextServiceImpl implements TextService {

    private final TextRepository textRepository;

    @Override
    public List<Text> saveWords(String url) throws IOException {

        Document page = Jsoup.connect(url).get();

        ArrayList<String> listOfSeparators = new ArrayList<String>();
        listOfSeparators.add(" ");
        listOfSeparators.add(",");
        listOfSeparators.add(".");
        listOfSeparators.add("!");
        listOfSeparators.add("?");
        listOfSeparators.add("(");
        listOfSeparators.add(")");
        listOfSeparators.add("[");
        listOfSeparators.add("]");
        listOfSeparators.add("-");
        listOfSeparators.add(";");
        listOfSeparators.add(":");
        listOfSeparators.add("\n");
        listOfSeparators.add("\r");
        listOfSeparators.add("\t");

        String pageText = page.text();
        String separatorsString = String.join("|\\", listOfSeparators);
        Map<String, Text> countMap = new HashMap<>();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(
                                pageText.getBytes(StandardCharsets.UTF_8))));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.split(separatorsString);
            for (String word : words) {
                if ("".equals(word)) {
                    continue;
                }

                Text wordObj = countMap.get(word);
                if (wordObj == null) {
                    wordObj = new Text();
                    wordObj.setWord(word);
                    wordObj.setValue(0);
                    wordObj.setUrl(url);
                    countMap.put(word, wordObj);
                }

                wordObj.setValue(wordObj.getValue()+1);
            }
        }

        reader.close();

        List<Text> texts = new ArrayList<>(countMap.values());

        return textRepository.saveAll(texts);
    }

    @Override
    public List<Text> getByUrl(String url) {
        return textRepository.getAllByUrl(url);
    }

    @Override
    public List<Text> getAll(Pageable pageable) {
        return textRepository.findAll(pageable).getContent();
    }
}
