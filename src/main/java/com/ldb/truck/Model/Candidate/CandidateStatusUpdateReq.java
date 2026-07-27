package com.ldb.truck.Model.Candidate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateStatusUpdateReq {

    private String token;

    @JsonProperty("id")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Long> id;

    @JsonProperty("ids")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Long> ids;

    private String status;
    private String dateInterview;
    private String interviewTime;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public List<Long> getId() { return id; }
    public void setId(List<Long> id) { this.id = id; }

    public List<Long> getIds() { return ids; }
    public void setIds(List<Long> ids) { this.ids = ids; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDateInterview() { return dateInterview; }
    public void setDateInterview(String dateInterview) { this.dateInterview = dateInterview; }

    public String getInterviewTime() { return interviewTime; }
    public void setInterviewTime(String interviewTime) { this.interviewTime = interviewTime; }

    public List<Long> getEffectiveCandidateIds() {
        List<Long> result = new ArrayList<>();
        if (this.id != null) {
            for (Long val : this.id) {
                if (val != null && !result.contains(val)) {
                    result.add(val);
                }
            }
        }
        if (this.ids != null) {
            for (Long val : this.ids) {
                if (val != null && !result.contains(val)) {
                    result.add(val);
                }
            }
        }
        return result;
    }
}
