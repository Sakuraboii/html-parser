package html.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PARSER_TEXT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "value")
    private Integer value;

    @Column(name = "word")
    private String word;

    @Column(name = "url")
    private String url;
}
