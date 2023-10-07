package ru.nabokovsg.dataservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class ObjectsSurveyElementData {

    SurveyObject objectSurvey;
    Map<Long, Element> elements;
    Map<Long, SubElement> subElements;
}
