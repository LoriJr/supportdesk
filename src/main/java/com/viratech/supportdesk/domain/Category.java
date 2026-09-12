package com.viratech.supportdesk.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
