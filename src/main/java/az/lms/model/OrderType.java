package az.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author Mehman Osmanov on 11.08.23
 * @project LMS
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public enum OrderType implements Serializable {
   ORDERED, RETURNED
}
