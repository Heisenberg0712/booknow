package com.example.booknow.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties("billList")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String mobile;
    @OneToMany(mappedBy = "user" ,fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Bill> billList;


}
