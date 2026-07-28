package com.ldb.truck.Model.Candidate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateStatusSummary {

    @JsonProperty("countAll")
    private long countAll;

    @JsonProperty("countWait")
    private long countWait;

    @JsonProperty("countInProgress")
    private long countInProgress;

    @JsonProperty("countOk")
    private long countOk;

    @JsonProperty("countBlackList")
    private long countBlackList;

    @JsonProperty("countFail")
    private long countFail;

    public long getCountAll() { return countAll; }
    public void setCountAll(long countAll) { this.countAll = countAll; }

    public long getCountWait() { return countWait; }
    public void setCountWait(long countWait) { this.countWait = countWait; }

    public long getCountInProgress() { return countInProgress; }
    public void setCountInProgress(long countInProgress) { this.countInProgress = countInProgress; }

    public long getCountOk() { return countOk; }
    public void setCountOk(long countOk) { this.countOk = countOk; }

    public long getCountBlackList() { return countBlackList; }
    public void setCountBlackList(long countBlackList) { this.countBlackList = countBlackList; }

    public long getCountFail() { return countFail; }
    public void setCountFail(long countFail) { this.countFail = countFail; }
}
