package org.jsp.Spring_boot_project.repository;

import org.jsp.Spring_boot_project.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Merchentrepository extends JpaRepository<Merchant, String> {

}
