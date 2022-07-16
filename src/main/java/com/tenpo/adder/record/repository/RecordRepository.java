package com.tenpo.adder.record.repository;

import com.tenpo.adder.record.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
