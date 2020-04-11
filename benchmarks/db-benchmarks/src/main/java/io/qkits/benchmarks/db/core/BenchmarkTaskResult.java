package io.qkits.benchmarks.db.core;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class BenchmarkTaskResult {
    private Long startTime;
    private Long endTime;
    private Integer count;
    private String taskName;
    private String categoryName;
    private boolean completed;

    public String toCsv() {
        return categoryName + "," + taskName + "," + startTime + "," + endTime
                + "," + count + "," + getTps();
    }

    private Long getTps() {
        return 1000 * getCount() /
                (getEndTime() - getStartTime());
    }
}