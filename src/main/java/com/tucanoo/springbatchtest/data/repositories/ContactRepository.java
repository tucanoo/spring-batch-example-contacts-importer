package com.tucanoo.springbatchtest.data.repositories;

import com.tucanoo.springbatchtest.data.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
