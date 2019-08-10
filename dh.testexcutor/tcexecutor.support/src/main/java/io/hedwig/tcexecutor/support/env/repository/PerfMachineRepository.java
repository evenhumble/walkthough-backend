package io.hedwig.tcexecutor.support.env.repository;

import io.hedwig.tcexecutor.support.env.entity.PerfMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfMachineRepository extends JpaRepository<PerfMachine,Long>{
}
