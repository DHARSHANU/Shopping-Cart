package org.jsp.Spring_boot_project.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Data
@Component
@Entity
public class ShoppingOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
 double orderprice;

LocalDateTime dateandtime;


@OneToMany
List<Item> items ;
}
