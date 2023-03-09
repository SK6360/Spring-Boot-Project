package org.jsp.banking_system.helper;

import lombok.Data;

@Data
public class ResponseStructure<T> 
{
int code;
String message;
T data;
}
