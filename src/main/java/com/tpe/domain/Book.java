package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Provide book title")
    @NotBlank(message = "Book title can not contain Blank")
    private String title;

    @NotBlank(message = "Author can not contain Blank")
    @Column(nullable = false, length = 30)
    @Size(min = 2, max = 30, message = "Author name (${validatedValue}) is between {min} and {max} chars.")
    private String author;

    @Column(nullable = false)
    private String publicationDate;

    @ManyToOne
    @JsonIgnore
    private Owner owner;


    //toString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}
