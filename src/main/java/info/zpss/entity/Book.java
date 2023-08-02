package info.zpss.entity;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String title;
    private String desc;
    private Double price;
}
