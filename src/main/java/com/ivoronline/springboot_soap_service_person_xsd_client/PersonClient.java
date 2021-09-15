package com.ivoronline.springboot_soap_service_person_xsd_client;

import com.ivoronline.soap.GetPersonRequest;
import com.ivoronline.soap.GetPersonResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Component
public class PersonClient extends WebServiceGatewaySupport {

  //PROPERTIES
  String generatedClasses   = "com.ivoronline.soap";
  String webServiceEndpoint = "http://localhost:8080/PersonWebService";

  //==========================================================================
  // GET PERSON
  //==========================================================================
  // Input Parameters are only used to create Custom Request Object.
  // Request Object will be marshalled into XML Request.
  // SOAP Envelope, Header and Body will be added around generated XML Request.
  // This is all done by calling marshalSendAndReceive().
  public GetPersonResponse getPerson(Integer id) {

    //CREATE REQUEST OBJECT
    GetPersonRequest getPersonRequest = new GetPersonRequest();
                     getPersonRequest.setId(id);  //Set some Parameters

    //POINT MARSHALLER TO GENERATED CLASSES
    Jaxb2Marshaller  marshaller = new Jaxb2Marshaller();
                     marshaller.setContextPath(generatedClasses);

    //CONFIGURE CLIENT
    setDefaultUri  (webServiceEndpoint);
    setMarshaller  (marshaller);
    setUnmarshaller(marshaller);

    //SEND REQUEST
    WebServiceTemplate template = getWebServiceTemplate();
    GetPersonResponse  response = (GetPersonResponse) template.marshalSendAndReceive(getPersonRequest);

    //RETURN RESPONSE OBJECT
    return response;

  }

}