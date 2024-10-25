package com.ldb.truck.Model.Login.DocumentStorage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultOfSurveyRes {
    private String status;
    private String message;
    private List<ResultOfSurveyModel> data;}
