package html.repository;

import html.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text,Long> {
    List<Text> getAllByUrl(String url);
}
